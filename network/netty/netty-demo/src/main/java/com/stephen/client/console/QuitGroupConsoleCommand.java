package com.stephen.client.console;

import com.stephen.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author stephen
 * @date 2020/12/3 2:21 下午
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
