package com.city.adapter;

//适配器
public class Adapter extends Adaptee implements Target{

    /** 对象适配器 方式 **/
    /*private Adaptee adaptee;

    public  Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }*/

    @Override
    public void createNode(int node) {
        /*if(node % 2 == 0){
            adaptee.createParallelNode();
        }else{
            adaptee.createSerialNode();
        }*/
        /** 类适配器模式 **/
        if(node % 2 == 0){
            createParallelNode();
        }else{
            createSerialNode();
        }
    }

}
