package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.JOIN_GROUP_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/3 2:32 下午
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
