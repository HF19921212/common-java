package com.city.common.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorCreateSessionDemo {

    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";

    public static void main(String[] args) {
        //创建会话两种方式

        //1.normal
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(CONNECTSTRING,5000,5000,new ExponentialBackoffRetry(100,3));
        curatorFramework.start();

        //2.fluent风格
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                namespace("curator").build();

        curatorFramework1.start();
    }

}
