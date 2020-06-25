package com.city.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.city.common.pojo.ItdragonResult;
import com.city.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/brain-sso/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public ItdragonResult userLogin(String username, String password,
                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            ItdragonResult result = userService.userLogin(username, password, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ItdragonResult.build(500, "");
        }
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @RequestMapping(value="/logout")
    @ResponseBody
    public ItdragonResult logout(String token) {
        userService.logout(token); // 思路是从Redis中删除key，实际情况请和业务逻辑结合
        return ItdragonResult.ok();
    }

    /**
     * 获取登录用户信息
     * @param token
     * @return
     */
    @RequestMapping("/token")
    @ResponseBody
    public Object getUserByToken(String token) {
        ItdragonResult result = null;
        try {
            result = userService.queryUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = ItdragonResult.build(500, "");
        }
        return result;
    }
}