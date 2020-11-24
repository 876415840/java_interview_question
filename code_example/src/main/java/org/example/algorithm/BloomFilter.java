package org.example.algorithm;

import org.springframework.util.Assert;

/**
 * 布隆过滤器简单实现
 *
 * @author stephen
 * @date 2020/11/20 6:37 下午
 */
public class BloomFilter {

    /**
     * bit分组
     */
    private byte[] bitGroup;
    /**
     * hash函数个数
     */
    private Integer hashFunctionSize;

    /**
     *
     * @param datas
     * @param errorRate 错误率
     * @author stephen
     * @date 2020/11/24 5:50 下午
     */
    public BloomFilter(Object[] datas, double errorRate) {
        Assert.notNull(datas, "过滤对象不能为空");
        double ln2 = Math.log(2)/Math.log(Math.E);
        // bit数组大小计算公式：m = dataSize * [-ln(errorRate)/ln(2)^2]
        // bitRate = -ln(errorRate)/ln(2)^2
        double bitRate = Math.ceil(- Math.log(errorRate)/Math.log(Math.E) / Math.pow(ln2, 2));
        int bitSize = datas.length * (int) bitRate;
        // 每个byte表示8个bit位 除8 右移3 >>3
        this.bitGroup = new byte[bitSize>>3 + 1];
        // hash函数个数计算公式：k = ln2 * (bitSize/dataSize)
        this.hashFunctionSize = (int) Math.ceil(ln2 * bitRate);

        int groupSize = this.bitGroup.length;
        for (Object o : datas) {
            int h = o.hashCode();
            for (int index = 1; index <= this.hashFunctionSize; index++) {
                int hash = hash(h, index);
                int groupIndex = hash % groupSize;
                // 跟7与运算 == 跟8取模，取值范围 0~7
                int val = hash & 7;
                // byte类型占8位，对应的值分别为 1<<0 ~ 1<<7
                this.bitGroup[groupIndex] |= 1 << val;
            }
        }
    }

    /**
     * 验证数据是否存在
     *
     * @param obj 需要验证的数据
     * @author stephen
     * @date 2020/11/24 2:52 下午
     */
    public boolean exist(Object obj) {
        int groupSize = this.bitGroup.length;
        int h = obj.hashCode();
        for (int index = 1; index <= this.hashFunctionSize; index++) {
            int hash = hash(h, index);
            int groupIndex = hash % groupSize;
            // 跟7与运算 == 跟8取模，取值范围 0~7
            int val = hash & 7;
            // byte类型占8位，对应的值分别为 1<<0 ~ 1<<7
            int bitVal = 1 << val;
            if (bitVal != (this.bitGroup[groupIndex] & bitVal)) {
                return false;
            }
        }
        return true;
    }

    private static int hash(int h, int hashFunctions) {
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << hashFunctions);
        return h ^ (h >> 16);
    }


    public static void main(String[] args) {
        String[] datas = {"sdfs@163.com", "32wesd@163.com", "34erghfg@163.com", "0oihjb3@163.com", "sj-3dnch3@163.com", "0-1234-dsf3=x@163.com",
                "mqh-zs@163.com", "67hgdc@163.com", "cmgds@163.com", "bcxhihfsdf8sj3kds3@163.com", "khjic93ddcdvc@163.com", "jjsdex343f@163.com"};

        BloomFilter bloomFilter = new BloomFilter(datas, 0.0001);
        System.out.println(bloomFilter.exist("mqh-zs@163.com"));
        System.out.println(bloomFilter.exist("mqh-12zs@163.com"));

    }

}
