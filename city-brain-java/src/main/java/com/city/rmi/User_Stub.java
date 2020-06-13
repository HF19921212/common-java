package com.city.rmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//存根：屏蔽通信细节
public class User_Stub extends User {

    private Socket socket;

    public User_Stub() throws IOException {
        socket = new Socket("localhost",8888);
    }

    public int getAge() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        outputStream.writeObject("age");
        outputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readInt();
    }

}
