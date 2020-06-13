package com.city.common.nio.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;
    public Object getResponse(){
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ctx 向服务端写数据 msg 读取服务端写数据
        response = msg;
    }
}
