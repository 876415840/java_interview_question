package org.example.io.bytes;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件字节流读取or写入
 *
 * @author stephen
 * @date 2020/11/16 5:11 下午
 */
public class FileStreamInOrOut {

    public static final String FILE_PATH = "/Users/finup/Desktop/阿里架构面试题1.docx";
    public static final int FILE_TYPE_INDEX = FILE_PATH.indexOf(".docx");
    public static final int READ_SIZE = 4096;

    public static void main(String[] args) throws Exception {
        read();
        readToBytes();
    }

    /**
     * 指定字节数组的read方法，指定字节数组的write方法
     * 每次读取指定字节（byte[]）大小数据，并放入 byte[]
     * 操作系统每次磁盘IO大小4KB
     *
     * @return int类型，读取的字节个数
     * @author stephen
     * @date 2020/11/17 5:35 下午
     */
    private static void readToBytes() throws IOException {
        String newFilePath = FILE_PATH.substring(0, FILE_TYPE_INDEX) + "-readToBytes" + FILE_PATH.substring(FILE_TYPE_INDEX);
        FileInputStream in = new FileInputStream(FILE_PATH);
        FileOutputStream out = new FileOutputStream(newFilePath);
        byte[] bytes = new byte[READ_SIZE];
        int r;
        while ((r = in.read(bytes)) != -1) {
            System.out.println("readToBytes: " + r);
            out.write(bytes);
            bytes = new byte[READ_SIZE];
        }
        in.close();
        out.close();
    }

    /**
     * read方法每次读取1字节
     * write方法每次写入1字节
     *
     * @return int类型,没有可读内容时返回-1,有可读内容时返回每个字节的值
     * @author stephen
     * @date 2020/11/17 5:19 下午
     */
    private static void read() throws IOException {
        String newFilePath = FILE_PATH.substring(0, FILE_TYPE_INDEX) + "-read" + FILE_PATH.substring(FILE_TYPE_INDEX);
        FileInputStream in = new FileInputStream(FILE_PATH);
        FileOutputStream out = new FileOutputStream(newFilePath);
        int r;
        while ((r = in.read()) != -1) {
            out.write(r);
        }
        in.close();
        out.close();
    }
}
