package com.stephen.protocol.response;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.LOGIN_RESPONSE;

/**
 * @author stephen
 * @date 2020/11/27 4:04 下午
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
