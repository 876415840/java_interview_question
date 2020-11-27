package com.stephen.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author stephen
 * @date 2020/11/26 7:33 下午
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * @return 指令
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();

}
