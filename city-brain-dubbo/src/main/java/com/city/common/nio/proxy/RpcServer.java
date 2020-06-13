package com.city.common.nio.proxy;

import com.city.common.nio.annotation.RpcAnnotation;
import com.city.common.nio.register.IRegisterCenter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.HashMap;
import java.util.Map;

public class RpcServer {

    private IRegisterCenter registerCenter;
    private String serviceAddress;
    private Map<String,Object> handlerMap = new HashMap<>();

    public  RpcServer(IRegisterCenter registerCenter,String serviceAddress){
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    public void publisher(){
        for(String serviceName : handlerMap.keySet()){
            registerCenter.register(serviceName,serviceAddress);
        }
        try {
            //boss 线程
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //work 线程
            EventLoopGroup workGroup = new NioEventLoopGroup();
            //netty 启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            //将上述两个线程加入到所谓的启动类中
            bootstrap.group(bossGroup,workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                    pipeline.addLast("frameEncoder",new LengthFieldPrepender(4));
                    pipeline.addLast("encoder",new ObjectEncoder());
                    pipeline.addLast("decoder",new io.netty.handler.codec.serialization.ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                    //IO 数据交互 实际上这里 Handler 很类似于spring MVC Hander
                    pipeline.addLast(new RpcServerHandler(handlerMap));
                }
            }).option(ChannelOption.SO_BACKLOG,128).childOption(ChannelOption.SO_KEEPALIVE,true);
            String[] addrs = serviceAddress.split(":");
            String ip = addrs[0];
            int port = Integer.parseInt(addrs[1]);
            ChannelFuture future = bootstrap.bind(ip,port).sync();
            System.out.println("netty服务端启动成功,等待客户端连接：");
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void bind(Object... services) {
        // 这里为了获取对应服务的类名，我们这里定义了一个注解来实现 代码请看下面
        for(Object service:services){
            RpcAnnotation annotation=service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName=annotation.value().getName();
            //绑定服务接口名称对应的服务
            handlerMap.put(serviceName,service);
        }
    }
}
