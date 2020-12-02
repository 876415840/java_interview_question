package com.stephen.client.handler;

import com.stephen.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author stephen
 * @date 2020/11/30 2:26 下午
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        Integer fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUsername();
        System.out.println("客户端[" + fromUserId + "]用户[" + fromUserName + "]发来消息: " + messageResponsePacket.getMessage());
    }
}
