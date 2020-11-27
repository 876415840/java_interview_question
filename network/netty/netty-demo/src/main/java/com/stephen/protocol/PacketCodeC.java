package com.stephen.protocol;

import com.stephen.protocol.request.LoginRequestPacket;
import com.stephen.protocol.request.MessageRequestPacket;
import com.stephen.protocol.response.LoginResponsePacket;
import com.stephen.protocol.response.MessageResponsePacket;
import com.stephen.serialize.Serializer;
import com.stephen.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.stephen.protocol.Command.*;

/**
 * 数据编码解码
 * 首位开始依次是 4个字节的魔数，1个字节的版本号，1个字节的序列化算法，1个字节的指令，4个字节的数据长度，N个字节的数据内容
 *
 * @author stephen
 * @date 2020/11/27 10:39 上午
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0X12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    /**
     * 对数据对象进行编码 -- 转为二进制数据
     *
     * @param packet
     * @return io.netty.buffer.ByteBuf
     * @author stephen
     * @date 2020/11/27 10:45 上午
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 对二进制数据进行解码 -- 转为对象
     *
     * @param byteBuf
     * @return com.stephen.protocol.Packet
     * @author stephen
     * @date 2020/11/27 10:49 上午
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
