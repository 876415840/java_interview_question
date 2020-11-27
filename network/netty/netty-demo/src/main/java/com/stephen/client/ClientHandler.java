package com.stephen.client;

import com.stephen.protocol.request.LoginRequestPacket;
import com.stephen.protocol.response.LoginResponsePacket;
import com.stephen.protocol.Packet;
import com.stephen.protocol.PacketCodeC;
import com.stephen.protocol.response.MessageResponsePacket;
import com.stephen.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.Random;

/**
 * @author stephen
 * @date 2020/11/26 5:25 下午
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 覆盖了 channelActive()方法，这个方法会在客户端连接建立成功之后被调用

        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录数据对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(new Random().nextInt(10000));
        loginRequestPacket.setUsername("root");
        loginRequestPacket.setPassword("root");

        // 编码 -- ctx.alloc() 获取的就是与当前连接相关的 ByteBuf 分配器
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 这里覆盖的方法是 channelRead()，这个方法在接收到服务端发来的数据之后被回调
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            // 处理登录
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            // 处理消息
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }

}
