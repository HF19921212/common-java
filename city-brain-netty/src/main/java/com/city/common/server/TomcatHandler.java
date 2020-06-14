package com.city.common.server;

import com.city.common.http.Request;
import com.city.common.http.Response;
import com.city.common.servlets.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class TomcatHandler extends ChannelInboundHandlerAdapter {

    //读取数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest) msg;
            Request input = new Request(ctx,request);
            Response output = new Response(ctx,request);

            MyServlet myServlet = new MyServlet();
            myServlet.doGet(input,output);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
