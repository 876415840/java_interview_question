package com.stephen.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stephen
 * @date 2020/11/25 2:08 下午
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 939910570120653690L;

    private String userId;
    private String username;
    private Integer age;
}
