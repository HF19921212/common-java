package com.city.common.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CreateOperatorDemo implements Watcher{

    //集群地址
    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(CONNECTSTRING, 5000, new CreateOperatorDemo());
        //阻塞
        countDownLatch.await();
        //CONNECTED
        System.out.println(zooKeeper.getState());

        //创建临时节点
        String result = zooKeeper.create("/hefan","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getData("/hefan",true,stat);
        System.out.println("创建成功："+result);

        zooKeeper.setData("/hefan","456".getBytes(),-1);
        Thread.sleep(2000);

        zooKeeper.setData("/hefan","789".getBytes(),-1);
        Thread.sleep(2000);

        zooKeeper.delete("/hefan",-1);
        Thread.sleep(2000);

        System.out.println("---------------------------------------------------");

        //创建持久化节点
        String path = "/frendB";
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        TimeUnit.SECONDS.sleep(1);

        //添加持久化节点监听
        Stat stat = zooKeeper.exists(path+"/frend-1",true);
        if(stat == null){
            zooKeeper.create(path+"/frend-1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            TimeUnit.SECONDS.sleep(1);
        }

        zooKeeper.setData(path+"/frend-1","456".getBytes(),-1);
        TimeUnit.SECONDS.sleep(1);

        List<String> children = zooKeeper.getChildren("node",true);
        System.out.println(children);
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
            }else if(watchedEvent.getType() == Event.EventType.NodeCreated){//子节点创建时触发
                try {
                    System.out.println("路径："+watchedEvent.getPath()+" -> 节点的值："+ zooKeeper.getData(watchedEvent.getPath(),true,stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(watchedEvent.getType() == Event.EventType.NodeDeleted){//子节点删除时会触发
                System.out.println("路径："+watchedEvent.getPath());
            }else if(watchedEvent.getType() == Event.EventType.NodeDataChanged){
                try {
                    System.out.println("路径："+watchedEvent.getPath()+" -> 改变后的值："+ zooKeeper.getData(watchedEvent.getPath(),true,stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){//子节点变化时触发
                try {
                    System.out.println("子路径："+watchedEvent.getPath()+" -> 改变后的值："+ zooKeeper.getData(watchedEvent.getPath(),true,stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
