package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.stephen.protocol.Command.MESSAGE_REQUEST;

/**
 * @author stephen
 * @date 2020/11/27 4:38 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private Integer toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
