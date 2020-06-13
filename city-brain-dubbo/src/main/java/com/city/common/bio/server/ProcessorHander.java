package com.city.common.bio.server;

import com.city.common.bio.proxy.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

//使用线程处理Socket
public class ProcessorHander implements Runnable {

    Socket socket;
    Object server;

    public ProcessorHander(Socket socket, Object server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        //进入到这里,说明当前服务端已经接收到了一个客户端请求
        try{
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            //TODO 下面这段代码实际就是java的序列化机制
            //读取客户端的数据
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

            //收到客户端请求
            Object result = invoke(rpcRequest);
            outputStream.writeObject(result);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //进行反射调用过程
    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = rpcRequest.getParameters();
        Class clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethod(),rpcRequest.getTypes());
        Object object = method.invoke(server,args);
        return object;
    }
}
