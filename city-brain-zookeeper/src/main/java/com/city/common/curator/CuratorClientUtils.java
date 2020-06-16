package com.city.common.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClientUtils {

    //集群地址
    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";

    public static CuratorFramework getInstance(){
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                build();
        return curatorFramework;
    }


}
