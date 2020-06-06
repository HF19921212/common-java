package com.city.aop;

public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("UserServiceImpl add method");
    }
}
