package com.stephen.client.handler;

import com.stephen.protocol.response.LoginResponsePacket;
import com.stephen.session.Session;
import com.stephen.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author stephen
 * @date 2020/11/30 2:23 下午
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 模拟多客户端登录，不在此处发起登录
        super.channelActive(ctx);
        // 覆盖了 channelActive()方法，这个方法会在客户端连接建立成功之后被调用
//        System.out.println(new Date() + ": 客户端开始登录");
//
//        // 创建登录数据对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUsername("root");
//        loginRequestPacket.setPassword("root");
//
//        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        // 处理登录
        Integer userId = loginResponsePacket.getUserId();
        String username = loginResponsePacket.getUsername();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + username + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        } else {
            System.out.println("[" + username + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
