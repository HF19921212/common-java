package com.city.common.nio;

import com.city.common.nio.register.IServiceDiscovery;
import com.city.common.nio.register.ServiceDiscoveryImpl;

public class DiscoveryTest {
    public static void main(String[] args) {
        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl();
        System.out.println(serviceDiscovery.discover("com.city.common.interfaces.IHelloService"));
    }
}
