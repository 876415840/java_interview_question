package com.stephen.client;

import com.stephen.client.handler.LoginResponseHandler;
import com.stephen.client.handler.MessageResponseHandler;
import com.stephen.codec.PacketDecoder;
import com.stephen.codec.PacketEncoder;
import com.stephen.protocol.request.MessageRequestPacket;
import com.stephen.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * netty客户端
 *
 * @author stephen
 * @date 2020/11/26 3:25 下午
 */
public class NettyClient {

    private static final int MAX_RETRY = 4;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------------- 客户端启动 --------------------");
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                // 1、指定线程模型
                .group(group)
                // 2、指定IO类型 -- NIO
                .channel(NioSocketChannel.class)
                // 3、IO处理逻辑
                .handler(new ChannelInitializer<Channel>() {
                    // 指定连接数据读写逻辑
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        // 当前链接相关的逻辑处理链 -- 责任链模式
                        ch.pipeline()
                                // 1、解码 -> 2、处理登录/消息 -> 3、编码(对第2步时的响应对象编码)
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                // 对channel中写入的数据编码
                                .addLast(new PacketEncoder());
                    }
                })
                // 表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // 表示是否开启 TCP 底层心跳机制，true 为开启
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 表示是否开始 Nagle 算法，true 表示关闭，false 表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启
                .option(ChannelOption.TCP_NODELAY, true);

        connect(bootstrap, "127.0.0.1", 1001, MAX_RETRY);

    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port)
                // 添加连接监听，连接(connect方法)为异步
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功!");
                        Channel channel = ((ChannelFuture) future).channel();
                        // 连接成功之后，启动控制台线程
                        startConsoleThread(channel);
                    } else if (retry <= 0){
                        System.err.println("重试次数已用完，放弃连接！");
                    } else {
                        // 第几次重连
                        int order = (MAX_RETRY - retry) + 1;
                        // 每次重连的间隔(秒)，从2开始每次乘2递增
                        int delay = 1 << order;
                        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");

                        bootstrap
                                // 配置参数
                                .config()
                                // 获取线程模型对象
                                .group()
                                // 定时任务
                                .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
                    }
                });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                // 登录成功后可录入消息
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    channel.writeAndFlush(packet);
                }
            }
        }).start();
    }
}
