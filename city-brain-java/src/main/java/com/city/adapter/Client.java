package com.city.adapter;

public class Client {
    public static void main(String[] args) {
        //Adaptee adaptee = new Adaptee();
        //Target target = new Adapter(adaptee);
        Target target = new Adapter();
        target.createNode(1);
    }
}
