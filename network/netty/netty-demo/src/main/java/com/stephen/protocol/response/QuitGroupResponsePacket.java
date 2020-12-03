package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.QUIT_GROUP_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/3 2:32 下午
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
