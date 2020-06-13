package com.city.rmi;

import java.io.IOException;

public class User {

    private int age;
    private String name;

    public int getAge() throws IOException {
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
}
