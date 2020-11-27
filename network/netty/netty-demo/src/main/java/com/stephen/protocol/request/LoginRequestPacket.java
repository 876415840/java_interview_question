package com.stephen.protocol.request;

import com.stephen.protocol.Packet;
import lombok.Data;

import static com.stephen.protocol.Command.LOGIN_REQUEST;

/**
 * @author stephen
 * @date 2020/11/26 7:40 下午
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
