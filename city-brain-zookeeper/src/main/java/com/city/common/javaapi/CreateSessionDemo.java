package com.city.common.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateSessionDemo {

    //集群地址
    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //如果当前的连接状态时连接成功的,那么通过计数器控制
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    //-1
                    countDownLatch.countDown();
                    //SyncConnected
                    System.out.println(watchedEvent.getState());
                }
            }
        });
        //阻塞
        countDownLatch.await();
        //CONNECTED
        System.out.println(zooKeeper.getState());
    }
}
