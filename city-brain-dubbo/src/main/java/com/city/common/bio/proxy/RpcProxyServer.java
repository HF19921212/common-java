package com.city.common.bio.proxy;

import com.city.common.bio.server.ProcessorHander;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {

    public void publisher(Object server,int port){
        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            //监听8080端口
            serverSocket = new ServerSocket(8080);
            //处理多个客户端链接
            while (true){
                //接收客户端请求,此时处于阻塞状态
                Socket socket = serverSocket.accept();
                //BIO
                //socket.getInetAddress();//获得一个输入流,来自于客户端的输出 导致阻塞
                //所以我们使用线程进行同时处理多个请求
                executorService.execute(new ProcessorHander(socket,server));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
