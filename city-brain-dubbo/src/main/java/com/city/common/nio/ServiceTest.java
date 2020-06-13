package com.city.common.nio;

import com.city.common.interfaces.IHelloService;
import com.city.common.nio.bean.HelloServiceImpl;
import com.city.common.nio.proxy.RpcServer;
import com.city.common.nio.register.IRegisterCenter;
import com.city.common.nio.register.RegisterCenterImpl;

public class ServiceTest {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer = new RpcServer(registerCenter,"127.0.0.1:8888");
        rpcServer.bind(helloService);
        rpcServer.publisher();
    }
}
