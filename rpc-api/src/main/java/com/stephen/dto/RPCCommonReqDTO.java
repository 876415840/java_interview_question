package com.stephen.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stephen
 * @date 2020/11/25 2:51 下午
 */
@Data
public class RPCCommonReqDTO implements Serializable {

    private static final long serialVersionUID = 1097207221198333475L;

    private String methodName;
    private String classpath;
    private Object[] args;
}
