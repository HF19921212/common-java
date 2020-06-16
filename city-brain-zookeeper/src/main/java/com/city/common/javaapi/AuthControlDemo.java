package com.city.common.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AuthControlDemo implements Watcher {
    //集群地址
    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";
    private static ZooKeeper zooKeeper;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat = new Stat();
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(CONNECTSTRING, 5000, new AuthControlDemo());
        countDownLatch.await();
        //CONNECTED
        System.out.println(zooKeeper.getState());

        zooKeeper.addAuthInfo("digest","root:root".getBytes());
        zooKeeper.create("/auth","123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        ZooKeeper zooKeeper2 = new ZooKeeper(CONNECTSTRING,5000,new AuthControlDemo());
        zooKeeper2.delete("/auth",-1);
        //权限模式 ip/Digest(username:password)

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        //如果当前的连接状态时连接成功的,那么通过计数器控制
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                //-1
                countDownLatch.countDown();
                //SyncConnected
                System.out.println(watchedEvent.getState());
            }
        }
    }
}
