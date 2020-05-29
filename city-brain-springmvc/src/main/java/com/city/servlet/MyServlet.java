package com.city.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet
 * 实例化(Instance) - 初始化(init) - 调用service方法 - 销毁(destroy)
 */
@WebServlet(name ="MyServlet" ,urlPatterns = "/my")
public class MyServlet extends HttpServlet {

    public MyServlet(){
        System.out.println("MyServlet -> Instance");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("MyServlet -> init");
        //测试自定义上下文监听器获取初始化参数
        String initData = (String) getServletContext().getAttribute("mvc_context");
        System.out.println("init data："+initData);
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MyServlet -> service");
        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("MyServlet -> destroy");
    }
}
