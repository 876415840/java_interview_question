package org.example.io.character;

import java.io.*;

/**
 * 文件字符流读取or写入
 *
 * @author stephen
 * @date 2020/11/17 5:57 下午
 */
public class FileReaderAndWriter {

    public static final String FILE_PATH = "/Users/finup/Desktop/阿里架构面试题1.docx";
    public static final int FILE_TYPE_INDEX = FILE_PATH.indexOf(".docx");
    public static final int READ_SIZE = 4096;

    public static void main(String[] args) throws Exception {
        read();
        readToChars();
    }

    /**
     * 指定字符数组的read方法，指定字符数组的write方法
     * 每次读取指定字符（char[]）大小数据，并放入 char[]
     * 内部默认每次读取8KB数据，基于FileInputStream、FileOutputStream
     *
     * @return int类型，读取的字符个数
     * @author stephen
     * @date 2020/11/19 12:00 下午
     */
    private static void readToChars() throws IOException {
        String newFilePath = FILE_PATH.substring(0, FILE_TYPE_INDEX) + "-readToChars" + FILE_PATH.substring(FILE_TYPE_INDEX);
        Reader reader = new FileReader(FILE_PATH);
        Writer writer = new FileWriter(newFilePath);
        char[] chars = new char[READ_SIZE];
        int r;
        while ((r = reader.read(chars)) != -1) {
            System.out.println("readToChars: " + r);
            writer.write(chars);
            chars = new char[READ_SIZE];
        }
        reader.close();
        writer.close();
    }

    /**
     * read方法每次读取1字符(char[1])
     * write方法每次写入1字符(char[1])
     *
     * @return int类型,没有可读内容时返回-1,有可读内容时返回每个字符值(char类型)
     * @author stephen
     * @date 2020/11/19 11:09 上午
     */
    private static void read() throws IOException {
        String newFilePath = FILE_PATH.substring(0, FILE_TYPE_INDEX) + "-read" + FILE_PATH.substring(FILE_TYPE_INDEX);
        Reader reader = new FileReader(FILE_PATH);
        Writer writer = new FileWriter(newFilePath);
        int r;
        while ((r = reader.read()) != -1) {
            System.out.println("read: " + new String(new char[]{(char)r}));
            writer.write(r);
        }
        reader.close();
        writer.close();
    }

}
