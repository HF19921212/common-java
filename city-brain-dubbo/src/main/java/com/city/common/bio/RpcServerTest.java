package com.city.common.bio;

import com.city.common.bio.proxy.RpcProxyClient;
import com.city.common.bio.proxy.RpcProxyServer;
import com.city.common.bio.service.HelloServiceImpl;
import com.city.common.bio.service.IHelloService;

public class RpcServerTest {

    //模拟配置文件属性配置
    public static final int port = 8080;

    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        //服务发布
        RpcProxyServer proxyServer = new RpcProxyServer();
        proxyServer.publisher(helloService,port);

        //调用远程服务
        RpcProxyClient client = new RpcProxyClient();
        IHelloService service = client.clientProxy(IHelloService.class,"localhost",8080);
        System.out.println(service.sayHello("hefan"));

    }
}
