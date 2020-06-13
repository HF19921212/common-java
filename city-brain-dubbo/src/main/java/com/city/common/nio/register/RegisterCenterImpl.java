package com.city.common.nio.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

//服务发现实现类
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    //连接zookeeper
    public RegisterCenterImpl(){
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZKConfig.CONNECTION_STR).sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,10)).build();
        curatorFramework.start();
    }

    //注册服务
    @Override
    public void register(String serverName, String serviceAddress) {
        //registers/com.city.common.interfaces/sayHello
        String servicePath = ZKConfig.ZK_REGISTER + "/" + serverName;

        try {
            //创建持久化节点PERSISTENT
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
            }
            //创建临时节点CreateMode.EPHEMERAL
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).
                    forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功："+rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
