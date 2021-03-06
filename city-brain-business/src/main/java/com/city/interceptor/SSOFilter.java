package com.city.interceptor;

import com.city.model.SysUsers;
import com.city.service.UserService;
import com.city.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SSOFilter implements Filter {

    public static final String COOKIE_NAME = "USER_TOKEN";

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/city-brain-business/user/login","/brain-sso/login","/brain-sso/user/token","/city-brain-business/user/logout"};

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SSOFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);

        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);

        //不需要过滤直接传给下一个过滤器
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
            //需要过滤器
        } else {
            SysUsers user = this.userService.getUserByToken(token);
            if (StringUtils.isEmpty(token) || null == user) {
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    //重定向到登录页
                    response.sendRedirect(request.getContextPath()+"/brain-sso/login");
                }
                return;
            }
            // 把用户信息放入Request
            request.setAttribute("user", user);
        }
    }

    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void destroy() {
        System.out.println("SSOFilter 销毁...");
    }
}