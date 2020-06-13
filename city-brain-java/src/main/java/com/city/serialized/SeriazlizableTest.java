package com.city.serialized;

import java.io.*;

public class SeriazlizableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化操作
        serializePersion();
        Persion.phone = 5;
        //反序列化操作
        DeSerializePersion();
        System.out.println(Persion.phone);
    }

    private static void serializePersion() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("persion")));
        Persion persion = new Persion();
        persion.setAge(18);
        persion.setName("he.fan");
        outputStream.writeObject(persion);
        outputStream.close();
    }

    private static void DeSerializePersion() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("persion")));
        Persion persion = (Persion) inputStream.readObject();
        System.out.println(persion);
    }

}
