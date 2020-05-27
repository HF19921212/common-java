package com.city.adapter;

//适配器
public class Adapter implements Target{

    private Adaptee adaptee;

    public  Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void createNode(int node) {
        if(node % 2 == 0){
            adaptee.createParallelNode();
        }else{
            adaptee.createSerialNode();
        }
    }

}
