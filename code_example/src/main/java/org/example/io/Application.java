package org.example.io;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 入口类
 * @Author MengQingHao
 * @Date 2020/7/1 2:41 下午
 */
public class Application {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        print(map);
        map.remove("b", 2);
        map.put("b", 2);
        print(map);

    }

    /**
     * 字节处理
     * @param
     * @return void
     * @author MengQingHao
     * @date 2020/7/1 2:50 下午
     */
    private static void print(Map<String, Integer> map) {
        System.out.println("map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }
}
