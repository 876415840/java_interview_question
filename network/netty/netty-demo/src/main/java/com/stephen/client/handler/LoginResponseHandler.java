package com.stephen.client.handler;

import com.stephen.protocol.request.LoginRequestPacket;
import com.stephen.protocol.response.LoginResponsePacket;
import com.stephen.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Random;

/**
 * @author stephen
 * @date 2020/11/30 2:23 下午
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 覆盖了 channelActive()方法，这个方法会在客户端连接建立成功之后被调用

        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录数据对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(new Random().nextInt(10000));
        loginRequestPacket.setUsername("root");
        loginRequestPacket.setPassword("root");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        // 处理登录
        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
