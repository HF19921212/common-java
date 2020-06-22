package com.city.common.nio;

import com.city.common.nio.register.IRegisterCenter;
import com.city.common.nio.register.RegisterCenterImpl;

import java.io.IOException;

public class RegisterTest {
    public static void main(String[] args) throws IOException {
        //将服务注册到zookeeper
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        registerCenter.register("com.city.common.spi.IOrderService.IHelloService","127.0.0.1:9090");
        System.in.read();
    }
}
