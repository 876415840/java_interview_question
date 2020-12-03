package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @author stephen
 * @date 2020/12/3 2:45 下午
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
