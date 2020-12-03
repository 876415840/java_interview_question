package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.stephen.protocol.Command.CREATE_GROUP_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/2 7:15 下午
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> usernameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
