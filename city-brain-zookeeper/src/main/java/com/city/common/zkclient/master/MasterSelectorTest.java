package com.city.common.zkclient.master;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MasterSelectorTest {
    public static void main(String[] args) throws InterruptedException, IOException {

        List<UserCenter> centerList = new ArrayList<>();
        List<ZkClient> zkClientList = new ArrayList<>();

        for(int i=0;i<10;i++){
            UserCenter center = new UserCenter();
            center.setPcId(i+1);
            center.setPcName("center_"+i+1);
            centerList.add(center);
        }

        for(int i=0;i<10;i++){
            ZkClient ZkClient = new ZkClient("192.168.56.1:2181",5000,5000,new SerializableSerializer());
            zkClientList.add(ZkClient);
        }

        for(int i=0;i<10;i++){
            MasterSelector masterSelector = new MasterSelector(zkClientList.get(i),centerList.get(i));
            //触发选举
            masterSelector.start();
        }

        for(int i=0;i<10;i++){
            MasterSelector masterSelector = new MasterSelector(zkClientList.get(i),centerList.get(i));
            //触发选举
            masterSelector.start();
        }

        System.in.read();

    }
}
