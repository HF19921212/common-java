package com.city.service;

import com.city.common.ItdragonResult;
import com.city.model.SysUsers;
import com.city.repository.SysUsersMapper;
import com.city.utils.CookieUtils;
import com.city.utils.HttpClientUtil;
import com.city.utils.ItdragonUtils;
import com.city.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@Transactional
@PropertySource(value = "classpath:redis.properties")
public class UserService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Autowired
    private JedisClient jedisClient;

    //登录用户的session_key
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    //登录用户的缓存超时时间
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    /**
     * 注册系统账号
     * @param user
     * @return
     */
    public ItdragonResult registerUser(SysUsers user) {
        // 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
        if (null != sysUsersMapper.findByAccount(user.getName())) {
            return ItdragonResult.build(400, "");
        }
        sysUsersMapper.insert(user);
        // TODO 注册成功后选择发送邮件激活。现在一般都是短信验证码
        return ItdragonResult.build(200, "");
    }

    /**
     * 账户登录
     * @param account
     * @param password
     * @param request
     * @param response
     * @return
     */
    public ItdragonResult userLogin(String account, String password,
                                    HttpServletRequest request, HttpServletResponse response) {
        // 判断账号密码是否正确
        SysUsers user = sysUsersMapper.findByAccount(account);
        if (!ItdragonUtils.decryptPassword(user, password)) {
            return ItdragonResult.build(400, "账号名或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 清空密码和盐避免泄漏
        String userPassword = user.getPassword();
        String userSalt = user.getSalt();
        user.setPassword(null);
        user.setSalt(null);
        // 把用户信息写入 redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        // 设置 session 的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
        CookieUtils.setCookie(request, response, "USER_TOKEN", token);
        // 返回token
        return ItdragonResult.ok(token);
    }

    /**
     * 登出
     * @param token
     */
    public void logout(String token) {
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
    }

    /**
     * 通过token获取登录者信息
     * @param token
     * @return
     */
    public ItdragonResult queryUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(json)) {
            //json : {"status":400,"msg":"此session已经过期，请重新登录"}
            return ItdragonResult.build(400, "此session已经过期，请重新登录");
        }
        // 更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return ItdragonResult.ok(JsonUtils.jsonToPojo(json, SysUsers.class));
    }

    /************************************* 客户端实现 *************************************/
    //服务端域名地址
    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    //服务端获取token地址
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    /**
     * 用户登录用 client
     * @param token
     * @return
     */
    public SysUsers getUserByToken(String token){
        try {
            // 调用sso系统的服务，根据token取用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            System.out.println("json : " + json);
            // 把json转换成ItdragonResult
            ItdragonResult result = ItdragonResult.formatToPojo(json, SysUsers.class);
            if (null != result && result.getStatus() == 200) {
                SysUsers user = (SysUsers) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
