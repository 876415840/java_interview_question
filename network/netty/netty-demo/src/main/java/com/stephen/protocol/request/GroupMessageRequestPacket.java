package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.stephen.protocol.Command.GROUP_MESSAGE_REQUEST;
import static com.stephen.protocol.Command.MESSAGE_REQUEST;

/**
 * @author stephen
 * @date 2020/11/27 4:38 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
