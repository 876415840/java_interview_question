package com.stephen.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.stephen.serialize.Serializer;
import com.stephen.serialize.SerializerAlgorithm;

/**
 * @author stephen
 * @date 2020/11/26 7:50 下午
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
