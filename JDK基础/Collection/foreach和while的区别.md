## 区别
* 如果*while*是通过Iterator.hashNext判断的话，那么和*foreach*编译后的代码一样。（test4、test1方法）都是通过Iterator hashNext判断，next取值
* 如果*while*是通过下标递增取值，那么和*for*编译后的代码一样。（test3、test2方法）都是通过i自增，判断i小于list.size，通过list.get(i)取值



## java代码

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    List<Integer> list = new ArrayList<>();
    public void test1() {
        // foreach
        for (Integer i : list) {
            System.out.println(i);
        }
    }
    public void test2() {
        // for
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public void test3() {
        // while
        int i = 0;
        while (i < list.size()) {
            System.out.println(list.get(i));
            i++;
        }
    }
    public void test4() {
        // while
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }
    }
}
```
## 编译后
### test1()
```java
  public void test1();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: getfield      #4                  // Field list:Ljava/util/List;
         4: invokeinterface #5,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
         9: astore_1
        10: aload_1
        11: invokeinterface #6,  1            // InterfaceMethod java/util/Iterator.hasNext:()Z
        16: ifeq          39
        19: aload_1
        20: invokeinterface #7,  1            // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
        25: checkcast     #8                  // class java/lang/Integer
        28: astore_2
        29: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        32: aload_2
        33: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        36: goto          10
        39: return
      LineNumberTable:
        line 9: 0
        line 10: 29
        line 11: 36
        line 12: 39
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 10
          locals = [ class java/util/Iterator ]
        frame_type = 250 /* chop */
          offset_delta = 28
```

### test2()
```java
  public void test2();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=2, args_size=1
         0: iconst_0
         1: istore_1
         2: iload_1
         3: aload_0
         4: getfield      #4                  // Field list:Ljava/util/List;
         7: invokeinterface #11,  1           // InterfaceMethod java/util/List.size:()I
        12: if_icmpge     37
        15: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        18: aload_0
        19: getfield      #4                  // Field list:Ljava/util/List;
        22: iload_1
        23: invokeinterface #12,  2           // InterfaceMethod java/util/List.get:(I)Ljava/lang/Object;
        28: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        31: iinc          1, 1
        34: goto          2
        37: return
      LineNumberTable:
        line 15: 0
        line 16: 15
        line 15: 31
        line 18: 37
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 34
```

### test3()
```java
  public void test3();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=2, args_size=1
         0: iconst_0
         1: istore_1
         2: iload_1
         3: aload_0
         4: getfield      #4                  // Field list:Ljava/util/List;
         7: invokeinterface #11,  1           // InterfaceMethod java/util/List.size:()I
        12: if_icmpge     37
        15: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        18: aload_0
        19: getfield      #4                  // Field list:Ljava/util/List;
        22: iload_1
        23: invokeinterface #12,  2           // InterfaceMethod java/util/List.get:(I)Ljava/lang/Object;
        28: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        31: iinc          1, 1
        34: goto          2
        37: return
      LineNumberTable:
        line 21: 0
        line 22: 2
        line 23: 15
        line 24: 31
        line 26: 37
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 34 /* same */
```

### test4()
```java
  public void test4();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: getfield      #4                  // Field list:Ljava/util/List;
         4: invokeinterface #5,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
         9: astore_1
        10: aload_1
        11: invokeinterface #6,  1            // InterfaceMethod java/util/Iterator.hasNext:()Z
        16: ifeq          39
        19: aload_1
        20: invokeinterface #7,  1            // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
        25: checkcast     #8                  // class java/lang/Integer
        28: astore_2
        29: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        32: aload_2
        33: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        36: goto          10
        39: return
      LineNumberTable:
        line 29: 0
        line 30: 10
        line 31: 19
        line 32: 29
        line 33: 36
        line 34: 39
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 10
          locals = [ class java/util/Iterator ]
        frame_type = 28 /* same */
```