## 基本描述

* concurrentHashMap是线程安全的，且性能高于hashTable

* 线程安全是因为使用了 分段锁 + cas + volatile修饰全局变量

  `分段锁`：node数组的每个node在写操作时使用synchronized同步

* 性能高于hashTable，是因为hashTable读写方法都被synchronized修饰，使整个方法同步；而concurrentHashMap只有写方法使用了synchronized且使用的同步块

## 分段锁/cas

### initTable

* 构造函数并不初始化容器(以Map初始化除外)，当调用第一次put方法时通过initTable方法初始化容器

* 通过cas操作将sizeCtl的偏移地址赋值为-1，以自旋方式争抢初始化过程(如有其它线程)

### put

#### 数组内置起始位置指定偏移地址为空

* 通过支持volatile load的cas操作取值，取值为空
* 通过cas操作将指定偏移地址赋值新的node对象

####数组内置起始位置指定偏移地址不为空 

* 当前节点正在扩容，自旋cas争抢扩容

* node单向连表长度到8时扩容，node数组长度达到64时单向连表转红黑树

  > put、remove、扩容等涉及写操作的地方都使用分段锁锁住单个节点或是cas自旋竞争执行
  >
  > 读操作直接取值，变量被volatile修饰保证可见性