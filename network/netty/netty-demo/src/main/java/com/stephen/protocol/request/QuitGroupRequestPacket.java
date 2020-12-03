package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.QUIT_GROUP_REQUEST;

/**
 * @author stephen
 * @date 2020/12/3 2:22 下午
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
