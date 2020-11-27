package com.stephen.protocol;

/**
 * @author stephen
 * @date 2020/11/26 7:39 下午
 */
public interface Command {

    byte LOGIN_REQUEST = 1;
    byte LOGIN_RESPONSE = 2;
    byte MESSAGE_REQUEST = 3;
    byte MESSAGE_RESPONSE = 4;
}
