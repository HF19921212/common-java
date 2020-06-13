package com.city.common.nio.bean;

import com.city.common.interfaces.IHelloService;
import com.city.common.nio.annotation.RpcAnnotation;

@RpcAnnotation(IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String str) {
        return "调用远程服务："+str;
    }
}
