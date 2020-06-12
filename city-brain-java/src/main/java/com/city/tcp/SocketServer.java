package com.city.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try{
            //启动一个服务
            serverSocket = new ServerSocket(8888);
            //阻塞等待请求
            Socket socket = serverSocket.accept();
            //读取数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                serverSocket.close();
            }
        }
    }
}
