package com.city.utils;

import com.city.model.SysUsers;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * 工具类
 * @author he.fan
 *
 */
public class ItdragonUtils {

    /**
     * 加盐加密的策略非常多,根据实际业务来
     * @param user
     */
    public static void entryptPassword(SysUsers user) {
        String salt = UUID.randomUUID().toString();
        String temPassword = salt + user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        user.setSalt(salt);
        user.setPassword(md5Password);
    }

    /**
     * 账户解密
     * @param sysUser 登录用户
     * @param plainPassword 纯文字密码数据
     * @return
     */
    public static boolean decryptPassword(SysUsers sysUser, String plainPassword) {
        if(sysUser == null){
            return false;
        }
        String temPassword = sysUser.getSalt() + plainPassword;
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        return sysUser.getPassword().equals(md5Password);
    }

    public static String getCurrentDateTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(zone);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}