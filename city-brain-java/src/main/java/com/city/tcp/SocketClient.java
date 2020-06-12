package com.city.tcp;

import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",8888);
            //写完后清空缓冲区
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println("发送一段诗句");
            writer.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
