package com.city.common.nio;

import com.city.common.interfaces.IHelloService;
import com.city.common.nio.proxy.RpcClientProxy;
import com.city.common.nio.register.IServiceDiscovery;
import com.city.common.nio.register.ServiceDiscoveryImpl;

public class ClientTest {
        public static void main(String[] args) {
        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl();

        String url = serviceDiscovery.discover("com.city.common.spi.IOrderService.IHelloService");

        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);

        IHelloService helloService = rpcClientProxy.create(IHelloService.class);

        System.out.println(helloService.sayHello("Jack"));
    }
}
