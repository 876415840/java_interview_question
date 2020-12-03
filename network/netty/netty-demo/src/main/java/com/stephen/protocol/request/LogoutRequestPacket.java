package com.stephen.protocol.request;

import com.stephen.protocol.Packet;

import static com.stephen.protocol.Command.LOGOUT_REQUEST;

/**
 * @author stephen
 * @date 2020/12/2 5:47 下午
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
