## 基本描述

* LinkedHashMap继承HashMap，大部分方法直接使用HashMap及更上层AbstractMap方法
* 内部维护一个双向链表，每次put时在链表末尾追加节点
* accessOrder 默认false(操作不影响排序)
  * true: get/put 操作影响排序，每次get/put的节点都调整位置到链表末尾
      
        一个先进先出的LRU算法，最新的操作都在最后面