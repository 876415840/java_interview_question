package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.MESSAGE_REQUEST;

/**
 * @author stephen
 * @date 2020/11/27 4:38 下午
 */
@Data
public class MessageRequestPacket extends Packet {

    private Integer toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
