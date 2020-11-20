package org.example.io.bytes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: ByteArrayInputStream
 * @Author MengQingHao
 * @Date 2020/7/1 2:56 下午
 */
public class ByteArrayInputStreamHandle {

    public static void main(String[] args) throws IOException {
        // byte类型,占用1字节、8位;可以表示(2的8次方)个数，有符号，所以取值范围 -128 ~ 127
        int size = 1 << 8;
        byte begin =  - 1 << 7;
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = begin++;
            System.out.print(begin + "、");
        }
        System.out.println("\nsize=" + size);
        InputStream in = new ByteArrayInputStream(bytes);
        int readVal;
        // read方法会读取下一个值，跟255做与运算并返回
        while ((readVal = in.read()) != -1) {
            System.out.println(readVal);
        }

        System.out.println("----------------- 我是分割线 -----------------");

        // read方法会按照指定长度读取下一批值(如剩余可读数量不足按真实数量长度)，复制到指定数组(指定下标开始)，返回读取长度
        in.reset();
        byte[] copyVal = new byte[50];
        int len = in.read(copyVal, 5, 10);
        for (int i = 0; i < copyVal.length; i++) {
            System.out.print(copyVal[i] + "、");
        }
        System.out.println("\nlen=" + len);

        in.close();
    }
}
