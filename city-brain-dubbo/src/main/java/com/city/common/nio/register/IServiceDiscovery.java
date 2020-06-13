package com.city.common.nio.register;

public interface IServiceDiscovery {

    //根据服务名称 得到ip地址
    String discover(String serviceName);
}
