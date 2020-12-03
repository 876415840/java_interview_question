package com.stephen.server.handler;

import com.stephen.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.stephen.protocol.Command.*;

/**
 * 合并多个handler
 * @ChannelHandler.Sharable 注解，表明当前handler可以多个channel共享
 *
 * @author stephen
 * @date 2020/12/3 4:36 下午
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        // 这里业务每次只需处理一个，没有前后关系 可以平行处理
        handlerMap = new HashMap<>();
        // 用户消息请求处理
        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        // 创建群聊请求处理
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        // 加入群请求处理
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        // 退群请求处理
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        // 获取群成员请求处理
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        // 群消息请求处理
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        // 登出请求处理
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
