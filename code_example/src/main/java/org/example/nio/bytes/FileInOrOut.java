package org.example.nio.bytes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author stephen
 * @date 2020/11/20 2:52 下午
 */
public class FileInOrOut {

    public static final String FILE_PATH = "/Users/finup/Desktop/阿里架构面试题1.docx";
    public static final int FILE_TYPE_INDEX = FILE_PATH.indexOf(".docx");
    public static final int READ_SIZE = 4096;

    public static void main(String[] args) throws Exception {
        read();
        

    }

    /**
     * 每次从源文件读取一部分数据然后立即写入目标文件
     *
     * @author stephen
     * @date 2020/11/20 3:49 下午
     */
    private static void read() throws IOException {
        FileInputStream in = new FileInputStream(FILE_PATH);
        // 获取读文件-通道
        FileChannel inFc = in.getChannel();

        String newFilePath = FILE_PATH.substring(0, FILE_TYPE_INDEX) + "-read" + FILE_PATH.substring(FILE_TYPE_INDEX);
        FileOutputStream out = new FileOutputStream(newFilePath);
        // 获取写文件-通道
        FileChannel outFc = out.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(READ_SIZE);
        int len;
        // read(buffer) 从(读)通道读取数据到缓冲区(buffer)
        while ((len = inFc.read(buffer)) != -1) {
            // buffer切换为读模式
            buffer.flip();
            System.out.println("-------len: " + len);
            // 读取缓冲区(buffer)数据到(写)通道
            outFc.write(buffer);
            // 重置position、limit、mark
            buffer.clear();
        }

        outFc.close();
        out.close();
        inFc.close();
        in.close();
    }
}
