package com.stephen.api.impl;

import com.stephen.api.UserService;
import com.stephen.dto.UserDTO;

import java.util.Random;

/**
 * @author stephen
 * @date 2020/11/25 2:17 下午
 */
public class UserServiceImpl implements UserService {

    public UserDTO addUser(UserDTO userDTO) {
        System.out.println("收到 add user:" + userDTO);
        userDTO.setUserId(new Random().nextInt(10000) + "");
        System.out.println("返回 user:" + userDTO);
        return userDTO;
    }
}
