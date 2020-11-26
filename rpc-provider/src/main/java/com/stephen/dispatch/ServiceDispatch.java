package com.stephen.dispatch;

import com.stephen.dto.RPCCommonReqDTO;

import java.lang.reflect.Method;

/**
 * @author stephen
 * @date 2020/11/25 2:35 下午
 */
public class ServiceDispatch {

    /**
     * 服务的分发
     *
     * @param reqObj
     * @return java.lang.Object
     * @author stephen
     * @date 2020/11/25 2:38 下午
     */
    public static Object dispatch(Object reqObj) {

        // 网络中过来的对象 判断调用哪个接口实现的哪个方法
        // 基于反射调用，避免判断

        RPCCommonReqDTO rpcCommonReqDTO = (RPCCommonReqDTO) reqObj;
        String classPath = rpcCommonReqDTO.getClasspath();
        String methodName = rpcCommonReqDTO.getMethodName();
        // 方法形参列表
        Class[] types = null;
        // 方法实际参数
        Object[] args = rpcCommonReqDTO.getArgs();
        if (args != null) {
            types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
        }

        Object respObject = null;

        try {
            Class<?> clazz = Class.forName(classPath);
            Method method = clazz.getDeclaredMethod(methodName, types);
            respObject = method.invoke(clazz.newInstance(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respObject;
    }
}
