package com.stephen.client.handler;

import com.stephen.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author stephen
 * @date 2020/12/3 2:34 下午
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println("退出群聊[" + quitGroupResponsePacket.getGroupId() + "]成功!");
        } else {
            System.err.println("退出群聊[" + quitGroupResponsePacket.getGroupId() + "]失败，原因为：" + quitGroupResponsePacket.getReason());
        }
    }
}
