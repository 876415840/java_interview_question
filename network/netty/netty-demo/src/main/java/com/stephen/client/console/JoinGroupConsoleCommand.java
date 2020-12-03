package com.stephen.client.console;

import com.stephen.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author stephen
 * @date 2020/12/3 2:21 下午
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
