package com.stephen.server.handler;

import com.stephen.protocol.request.HeartBeatRequestPacket;
import com.stephen.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author stephen
 * @date 2020/12/3 5:37 下午
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        System.out.println("收到心跳......");
        // 这里直接输出
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
