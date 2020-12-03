package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.stephen.protocol.Command.CREATE_GROUP_REQUEST;

/**
 * @author stephen
 * @date 2020/12/2 5:42 下午
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
