package com.city.common.curator;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateOperatorDemo{

    //集群地址
    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";

    private static ZkClient getInstance(){
        return new ZkClient(CONNECTSTRING,5000);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZkClient zkClient = getInstance();

        //创建临时节点
        zkClient.createEphemeral("/zkclient");
        //递归创建持久化节点
        zkClient.createPersistent("/hefan/hefan-1",true);

        //删除节点
        zkClient.delete("/zkclient");
        //递归删除
        zkClient.deleteRecursive("/hefan/hefan-1");

        //获取子节点
        List<String> list = zkClient.getChildren("/hefan");
        System.out.println(list);

        //watcher
        zkClient.subscribeDataChanges("hefan", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点名称："+s+" -> "+"节点修改后的值："+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });
        zkClient.writeData("/hefan","hefan");
        TimeUnit.SECONDS.sleep(1);

        zkClient.subscribeChildChanges("hefan", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {

            }
        });
    }



}
