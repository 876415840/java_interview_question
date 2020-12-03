package com.stephen.server.handler;

import com.stephen.protocol.request.MessageRequestPacket;
import com.stephen.protocol.response.MessageResponsePacket;
import com.stephen.session.Session;
import com.stephen.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 * @ChannelHandler.Sharable 注解，表明当前handler可以多个channel共享
 *
 * @author stephen
 * @date 2020/11/30 11:58 上午
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUsername(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        System.out.println("客户端[" + session.getUserId() + "]给客户端[" + messageRequestPacket.getToUserId() + "]发送消息：" + messageRequestPacket.getMessage());

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
            // -1 代表系统用户
            messageResponsePacket.setFromUserId(-1);
            messageResponsePacket.setFromUsername("system");
            messageResponsePacket.setMessage("用户[" + messageRequestPacket.getToUserId() + "]不在线，发送失败！");
            // 响应当前客户端
            ctx.writeAndFlush(messageResponsePacket);
        }

    }

}
