package com.stephen.factory;

import com.stephen.dto.RPCCommonReqDTO;
import com.stephen.net.NetClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author stephen
 * @date 2020/11/25 3:14 下午
 */
public class RPCInvocationHandler implements InvocationHandler {


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RPCCommonReqDTO rpcCommonReqDTO = new RPCCommonReqDTO();
        rpcCommonReqDTO.setMethodName(method.getName());
        rpcCommonReqDTO.setArgs(args);
        rpcCommonReqDTO.setClasspath("com.stephen.api.impl.UserServiceImpl");

        // 远程调用服务端
        Object respObject  = NetClient.callRemoteService("127.0.0.1", 9999, rpcCommonReqDTO);

        return respObject;
    }
}
