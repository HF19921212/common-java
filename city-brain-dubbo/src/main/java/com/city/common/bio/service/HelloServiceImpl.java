package com.city.common.bio.service;

import com.city.common.interfaces.IHelloService;

public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String str) {
        return "调用远程服务："+str;
    }
}
