package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import com.stephen.session.Session;
import lombok.Data;

import java.util.List;

import static com.stephen.protocol.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/3 2:50 下午
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
