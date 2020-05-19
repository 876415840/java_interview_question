## LRU描述

> Least Recently Used的缩写，即最近最少使用，是一种常用的页面置换算法，选择最近最久未使用的页面予以淘汰。

## 简单代码实现

```java

/**
 * @Description: 手写一个LRU（容器先进先出，可以倒叙取先进后出）
 * @Author MengQingHao
 * @Date 2020/5/19 3:34 下午
 */
public class UseLinkedHashMapLRU<K, V> extends LinkedHashMap<K, V> {

    /**
     * 指定数量大小
     */
    private int size;

    public UseLinkedHashMapLRU(int size) {
        // 按照插入顺序
        // super();
        // 按照访问顺序
        super(16, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        // 超过指定大小，删除链表顶端(先进)数据
        return size()>size;
    }

}

```



