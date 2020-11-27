package com.stephen.serialize;

import com.stephen.serialize.impl.JSONSerializer;

/**
 * @author stephen
 * @date 2020/11/26 7:42 下午
 */
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return byte
     * @author stephen
     * @date 2020/11/26 7:45 下午
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转二进制
     *
     * @param object
     * @return byte[]
     * @author stephen
     * @date 2020/11/26 7:47 下午
     */
    byte[] serialize(Object object);

    /**
     * 二进制转java对象
     *
     * @param clazz
     * @param bytes
     * @return T
     * @author stephen
     * @date 2020/11/26 7:48 下午
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
