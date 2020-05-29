package com.city.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//springmvc 上下文监听器ServletContextListener使用容器初始化参数
public class MyDataContextListener implements ServletContextListener {

    private ServletContext context = null;

    public MyDataContextListener(){

    }

    //该方法在ServletrContext启动之后被调用,并准备好处理客户端请求
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.context = servletContextEvent.getServletContext();
        //记录属性
        context.setAttribute("mvc_context","init data");
    }

    //这个方法在ServletContext将要被关闭的时候被调用
    @Override
    public void contextDestroyed(ServletContextEvent event){
        this.context = null;
    }

}
