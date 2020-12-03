package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.JOIN_GROUP_REQUEST;

/**
 * @author stephen
 * @date 2020/12/3 2:22 下午
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
