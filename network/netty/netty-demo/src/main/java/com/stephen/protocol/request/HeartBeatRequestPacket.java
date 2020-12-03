package com.stephen.protocol.request;

import com.stephen.protocol.Packet;

import static com.stephen.protocol.Command.HEARTBEAT_REQUEST;

/**
 * @author stephen
 * @date 2020/12/3 5:33 下午
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
