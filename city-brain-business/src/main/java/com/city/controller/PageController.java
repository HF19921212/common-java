package com.city.controller;

import com.city.common.ItdragonResult;
import com.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @param redirect
     * @param model
     * @return
     */
    @RequestMapping("/brain-sso/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }

    /**
     * 获取登录用户信息
     * @param token
     * @return
     */
    @RequestMapping("/brain-sso/user/token")
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