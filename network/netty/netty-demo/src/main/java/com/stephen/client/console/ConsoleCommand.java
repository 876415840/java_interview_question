package com.stephen.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令
 *
 * @author stephen
 * @date 2020/12/2 5:38 下午
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
