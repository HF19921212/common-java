package com.city.common.bio;

import com.city.common.bio.proxy.RpcProxyServer;
import com.city.common.interfaces.IHelloService;
import com.city.common.nio.bean.HelloServiceImpl;

public class RpcServerTest {

    //模拟配置文件属性配置
    public static final int port = 8080;

    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        //服务发布
        RpcProxyServer proxyServer = new RpcProxyServer();
        proxyServer.publisher(helloService,port);

    }
}
