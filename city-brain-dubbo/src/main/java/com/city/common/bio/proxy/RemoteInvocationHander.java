package com.city.common.bio.proxy;

import com.city.common.bio.transport.RpcNetTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//远程调用方式处理程序
public class RemoteInvocationHander implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHander(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 网络通信逻辑
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是远程服务代理方法："+method.getName());
        RpcRequest rpcRequest = new RpcRequest();
        //设置接口名称
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        //设置接口名称
        rpcRequest.setMethod(method.getName());
        //设置参数
        rpcRequest.setParameters(args);
        //设置参数类型
        rpcRequest.setTypes(method.getParameterTypes());

        RpcNetTransport transport = new RpcNetTransport(host,port);
        return  transport.sendRequest(rpcRequest);
    }

}
