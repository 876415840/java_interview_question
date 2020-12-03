package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.LOGOUT_RESPONSE;

/**
 * @author stephen
 * @date 2020/12/2 7:28 下午
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
