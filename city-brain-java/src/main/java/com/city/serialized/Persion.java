package com.city.serialized;

import java.io.Serializable;

public class Persion implements Serializable {

    private static final long serialVersionUID = 5435503486441887740L;

    public static int phone = 2;
    public transient int num = 10;
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "num=" + num +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
