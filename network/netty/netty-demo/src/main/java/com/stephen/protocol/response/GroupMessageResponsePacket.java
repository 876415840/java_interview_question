package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import com.stephen.session.Session;
import lombok.Data;

import static com.stephen.protocol.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/3 3:55 下午
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
