package com.city.common.nio.register;

public interface IRegisterCenter {

    //服务名称和ip地址注册到zk上
    void register(String serverName,String serviceAddress);
}
