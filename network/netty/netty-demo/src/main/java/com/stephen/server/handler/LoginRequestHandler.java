package com.stephen.server.handler;

import com.stephen.protocol.request.LoginRequestPacket;
import com.stephen.protocol.response.LoginResponsePacket;
import com.stephen.session.Session;
import com.stephen.util.LoginUtil;
import com.stephen.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Random;

/**
 * 处理登录
 *
 * @author stephen
 * @date 2020/11/30 11:54 上午
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        // 处理登录
        LoginResponsePacket loginResponsePacket = login(ctx.channel(), loginRequestPacket);
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private LoginResponsePacket login(Channel channel, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(channel);
            Integer userId = new Random().nextInt(10000);
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUsername(loginRequestPacket.getUsername());
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channel);
            System.out.println("[" + loginRequestPacket.getUsername() + "]: 登录成功!");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        return loginResponsePacket;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 连接断开后回调此方法
        // 用户断线之后取消绑定
        SessionUtil.unBindSession(ctx.channel());
    }

}
