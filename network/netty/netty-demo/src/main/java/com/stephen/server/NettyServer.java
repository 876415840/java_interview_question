package com.stephen.server;

import com.stephen.codec.PacketDecoder;
import com.stephen.codec.PacketEncoder;
import com.stephen.server.handler.LifeCyCleTestHandler;
import com.stephen.server.handler.LoginRequestHandler;
import com.stephen.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * netty服务端
 *
 * @author stephen
 * @date 2020/11/26 3:19 下午
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 处理连接线程组，负责创建连接 - accept新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 处理数据线程组，负责读取数据 - 双向读写
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                // 指定IO类型 -- NIO
                .channel(NioServerSocketChannel.class)
                // 服务启动中 -- 需要执行的逻辑（多数情况不用）
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务启动中......");
                    }
                })
                // 数据读写 -- 需要执行的逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // 添加到第一个，查看 ChannelHandler 回调方法执行顺序
                        ch.pipeline().addLast(new LifeCyCleTestHandler());
                        // 当前链接相关的逻辑处理链 -- 责任链模式
                        ch.pipeline()
                                // 基于长度域拆包器 （数据包长度上限，长度值偏移量，长度值字节数）-- 参照自定义通信协议
                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4))
                                // 1、解码 -> 2、处理登录/消息 -> 3、编码(对第二步时的响应对象编码)
                                .addLast(new PacketDecoder())
                                .addLast(new LoginRequestHandler())
                                .addLast(new MessageRequestHandler())
                                // 对channel中写入的数据编码
                                .addLast(new PacketEncoder());

                    }
                })
                // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 表示是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY, true);

        bind(serverBootstrap, 1001);

    }

    /**
     * 从 {@port} 开始向上寻找可用端口
     * @param serverBootstrap
     * @param port
     * @return void
     * @author stephen
     * @date 2020/11/26 4:11 下午
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port)
                // 添加端口监听 -- 绑定端口(bind方法)为异步
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("端口[" + port + "]绑定成功!");
                        } else {
                            System.err.println("端口[" + port + "]绑定失败!");
                            bind(serverBootstrap, port + 1);
                        }
                    }
                });
    }
}
