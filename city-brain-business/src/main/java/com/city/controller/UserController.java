package com.city.controller;

import com.city.common.ItdragonResult;
import com.city.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/city-brain-business/user")
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
    @ApiOperation(value = "登录", notes = "")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name="username",value = "账户",required = true),
            @ApiImplicitParam(name="password",value = "密码",required = true)
    })
    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public ItdragonResult userLogin(String username,String password,
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
    @ApiOperation(value = "登出", notes = "")
    @RequestMapping(value="/logout", method= RequestMethod.GET)
    @ResponseBody
    public ItdragonResult logout(@ApiParam(value = "token", required = true) String token) {
        userService.logout(token); // 思路是从Redis中删除key，实际情况请和业务逻辑结合
        return ItdragonResult.ok();
    }

}
