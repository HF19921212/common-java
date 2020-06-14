package com.city.common.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

public class Response {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public Response(ChannelHandlerContext ctx, HttpRequest request){
        this.ctx = ctx;
        this.request = request;
    }

    public void write(String out, int i) throws UnsupportedEncodingException {
        try{
            if(out == null){
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
            response.headers().set("Content-Type","text/json");
            response.headers().set("Content-Length",response.content().readableBytes());
            response.headers().set("expires",0);
            if(HttpHeaders.isKeepAlive(request)){
                response.headers().set("Connection", HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(response);
        }finally {
            ctx.flush();
        }
    }
}
