package com.city.common.service.impl;

import com.city.common.pojo.ItdragonResult;
import com.city.common.pojo.User;
import com.city.common.service.UserService;
import com.city.common.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:resource.properties")
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_DOMAIN_BASE_USRL}")
    public String SSO_DOMAIN_BASE_USRL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;


    @Override
    public User getUserByToken(String token) {
        try {
            // 调用sso系统的服务，根据token取用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            System.out.println("json : " + json);
            // 把json转换成ItdragonResult
            ItdragonResult result = ItdragonResult.formatToPojo(json, User.class);
            if (null != result && result.getStatus() == 200) {
                User user = (User) result.getData();
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}