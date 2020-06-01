package com.city.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethodInvoke {

    public void method(String name){
        System.out.println(name);
    }

    private void privateMethod(String name){
        System.out.println(name);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflectMethodInvoke reflectMethodInvoke = new ReflectMethodInvoke();
        Class clazz = reflectMethodInvoke.getClass();
        Method method = clazz.getDeclaredMethod("method",String.class);
        Method privateMethod = clazz.getDeclaredMethod("privateMethod",String.class);
        method.invoke(reflectMethodInvoke,"输出内容");
        privateMethod.invoke(reflectMethodInvoke,"输出内容");
    }

}
