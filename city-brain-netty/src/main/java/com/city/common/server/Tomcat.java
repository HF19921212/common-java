package com.city.common.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class Tomcat {
    public void start(int port) throws Exception{
        //ServerSocketChannel channel = ServerSocketChannel.open();
        //channel.bind();
        //主从模型
        //BOSS线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //启动Netty服务,接收客户端请求
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workerGroup)
                    //主线程处理类
                    .channel(NioServerSocketChannel.class)
                    //子线程处理,Handler
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {
                        //客户端处理
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            //无锁化串行编程

                            //业务逻辑链路
                            client.pipeline().addLast(new HttpResponseEncoder());//编码器
                            client.pipeline().addLast(new HttpRequestDecoder());//解码器
                            client.pipeline().addLast(new TomcatHandler());//处理程序
                        }
                    })
                    //配置信息
                    .option(ChannelOption.SO_BACKLOG,128)//针对主线程的配置 128分配线程的最大数量
                    .childOption(ChannelOption.SO_KEEPALIVE,true);//针对子线程的配置  表示客户端过来,是一个长连接的服务

            //等待,主线程阻塞
            ChannelFuture future = server.bind(port).sync();
            System.out.println("Tomcat服务已经启动...");
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new Tomcat().start(8080);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
