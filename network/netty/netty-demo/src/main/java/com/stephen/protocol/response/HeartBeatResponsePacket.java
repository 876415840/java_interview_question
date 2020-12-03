package com.stephen.protocol.response;

import com.stephen.protocol.Packet;

import static com.stephen.protocol.Command.HEARTBEAT_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/3 5:38 下午
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
