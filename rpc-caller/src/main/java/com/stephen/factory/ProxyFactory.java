package com.stephen.factory;

import java.lang.reflect.Proxy;

/**
 * @author stephen
 * @date 2020/11/25 3:08 下午
 */
public class ProxyFactory {


    /**
     * 根据接口类，返回实现该接口的实例
     *
     * @param interfaceClass
     * @return T 实现类
     * @author stephen
     * @date 2020/11/25 3:11 下午
     */
    public static <T> T getProxyInstanceByInterfaceClass(Class<T> interfaceClass) {

        return (T) Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),
                new Class[]{interfaceClass}, new RPCInvocationHandler());
    }
}
