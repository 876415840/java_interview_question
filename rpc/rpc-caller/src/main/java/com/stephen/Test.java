package com.stephen;

import com.stephen.api.UserService;
import com.stephen.dto.UserDTO;
import com.stephen.factory.ProxyFactory;

/**
 * @author stephen
 * @date 2020/11/25 3:06 下午
 */
public class Test {

    public static void main(String[] args) {
        UserService userService = ProxyFactory.getProxyInstanceByInterfaceClass(UserService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(11);
        userDTO.setUsername("xiao ming");
        System.out.println("--before--:" + userDTO);
        UserDTO result = userService.addUser(userDTO);
        System.out.println("--after--:" + result);
    }

}
