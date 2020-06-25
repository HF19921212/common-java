package com.city.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.city.common.pojo.User;
import com.city.common.service.UserService;
import com.city.common.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UserLoginHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "USER_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        User user = this.userService.getUserByToken(token);
        if (StringUtils.isEmpty(token) || null == user) {
            // 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect("http://localhost:8888/login?redirect=" + request.getRequestURL());
            // 返回false
            return false;
        }
        // 把用户信息放入Request
        request.setAttribute("user", user);
        // 返回值决定handler是否执行。true：执行，false：不执行。
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }

}