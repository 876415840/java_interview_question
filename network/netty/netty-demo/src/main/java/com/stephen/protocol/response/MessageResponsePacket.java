package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.MESSAGE_RESPONSE;

/**
 * @author stephen
 * @date 2020/11/27 4:40 下午
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
