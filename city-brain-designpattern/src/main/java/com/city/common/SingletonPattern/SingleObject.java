package com.city.common.SingletonPattern;

public class SingleObject {
    //创建 SingleObject 的一个对象
    private volatile static SingleObject instance;

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject(){}

    //获取唯一可用的对象
    public static SingleObject getInstance(){
        if (instance == null) {
            synchronized (SingleObject.class) {
                if (instance == null) {
                    instance = new SingleObject();
                }
            }
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
