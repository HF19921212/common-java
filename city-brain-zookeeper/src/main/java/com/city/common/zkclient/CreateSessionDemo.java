package com.city.common.zkclient;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;

public class CreateSessionDemo {

    //集群地址
    private final static String CONNECTSTRING = "192.168.0.100:2181,192.168.0.103:2181,192.168.0.104:2181";

    public static void main(String[] args) throws IOException, InterruptedException {
        //创建链接
        ZkClient zkClient = new ZkClient(CONNECTSTRING,5000);
        System.out.println(zkClient+" -> success");


    }
}
