package com.city.common.bio;


import com.city.common.bio.proxy.RpcProxyClient;
import com.city.common.bio.service.IHelloService;

public class RpcClient {
    public static void main(String[] args) {
        //客户端调用远程服务方法测试
        RpcProxyClient client = new RpcProxyClient();
        IHelloService helloService = client.clientProxy(IHelloService.class,"localhost",8080);
        System.out.println(helloService.sayHello("hefan"));
    }
}
