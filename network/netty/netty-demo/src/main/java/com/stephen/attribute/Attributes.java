package com.stephen.attribute;

import com.stephen.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author stephen
 * @date 2020/11/27 4:43 下午
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
