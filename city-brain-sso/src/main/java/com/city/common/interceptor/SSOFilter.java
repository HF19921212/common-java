package com.city.common.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SSOFilter implements Filter {

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/brain-sso/login","/brain-sso/user/login","/brain-sso/user/login","/brain-sso/user/token","/brain-sso/user/logout"};

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

        //不需要过滤直接传给下一个过滤器
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
            //需要过滤器
        } else {
            // session中包含user对象,则是登录状态
            if(session!=null && session.getAttribute("user") != null){
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    //重定向到登录页
                    response.sendRedirect(request.getContextPath()+"/login");
                }
                return;
            }
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