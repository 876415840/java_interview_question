package com.stephen.api;

import com.stephen.dto.UserDTO;

/**
 * @author stephen
 * @date 2020/11/25 11:58 上午
 */
public interface UserService {

    /**
     * add user
     *
     * @param userDTO
     * @return com.stephen.dto.UserDTO
     * @author stephen
     * @date 2020/11/25 2:13 下午
     */
    UserDTO addUser(UserDTO userDTO);
}
