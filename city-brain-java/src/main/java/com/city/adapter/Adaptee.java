package com.city.adapter;

//适配者
public class Adaptee {

    //创建一个并行节点
    void createParallelNode(){
        System.out.println("创建并行节点");
    }

    //创建一个串行节点
    void createSerialNode(){
        System.out.println("创建串行节点");
    }

}
