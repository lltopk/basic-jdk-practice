package com.lyflexi.genericpractice.genericErasure;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author hasee
 * @description V1.0  泛型擦除与类型记忆CHECKCAST
 * @create 2025/9/24 17:08
 */
public class ErasureGoal {
    public static void main(String[] args) {
        erasureImpl();
        bypassErasure();
//        byReflectPassErasure();
    }

    /**
     * 以ArrayList为例, PriorityQueue同理
     *
     * 源代码用于编译 (你写的)                   编译后的字节码 (擦除后)                运行时 (JVM 执行)
     * ──────────────────────────   ───────────────────────────────   ──────────────────────────
     * List<String> list =           List list =                      [内存里其实是个 ArrayList]
     *     new ArrayList<>();            new ArrayList();                  元素存在 Object[] 中
     *
     * list.add("hello");             list.add("hello");               queue[0] = (Object)"hello"
     *
     * String s = list.get(0);        String s = (String) list.get(0); Object tmp = queue[0];
     *                                                                s = (String) tmp;
     *
     * 1. 编译阶段: 编译器会检查 list 的类型参数是 String，因此只允许你 add(String)来保证安全
     * 2. 字节码阶段: 泛型信息被擦除成原始类型（List 而不是 List<String>）。编译器会给get()插入CHECKCAST, 也即是具体的 (String) 强转。
     * 3. 运行时存储结构就是raw type原始类型Object[](jdk集合源码成员变量也是Object[]), 取值时 (E) 强转，这里的E实际对应字节码里的 CHECKCAST 指令。这里是String, 如果类型不对，会 ClassCastException。
     *
     * jdk集合源码的读取操作上都会用 @SuppressWarnings("unchecked")来屏蔽掉强转警告 , 因为编译器知道这里存在 unchecked cast，但在逻辑上是这里安全的。
     */
    private static void erasureImpl(){
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.offer("hello");
        String s = pq.poll();
        System.out.println("erasureImpl:"+ s);
    }

    /**
     * 用户不指定泛型, 导致JDK底层无法识别CHECKCAST,  由用户手动强转取值的时候可能不安全
     */
    private static void bypassErasure(){
        PriorityQueue raw = new PriorityQueue(); // 原始类型
        raw.offer(123); // 可以绕过编译检查
        raw.offer(456); // 可以绕过编译检查
        raw.offer(789); // 可以绕过编译检查
        Object poll = raw.poll();//取出的只能是原始Object, JDK无法识别CHECKCAST给我们强转了
        System.out.println("bypassErasure:"+ poll);
        Integer v = ((PriorityQueue<Integer>) raw).poll(); //只能交给用户强转
        System.out.println("bypassErasure:"+ v);
        String s = ((PriorityQueue<String>) raw).poll(); //只能交给用户强转, 但转的类型和存的类型不一致, 就会造成不安全
        // 运行时抛异常. Java 的强制类型转换只适用于存在继承关系的类型（如父类和子类之间）。由于 Integer 和 String 没有这种关系，因此以下代码会编译报错：
        System.out.println("bypassErasure exception:"+ s);
    }

    /**
     * 通过反射恶意绕过了编译时检查. 就会造成不安全不安全的
     *
     * 但是编译器给字节码的CHECKCAST依然是用户声明的Integer类型, 因此当从集合取出String类型的时候强转失败
     */
    private static void byReflectPassErasure(){
        List<Integer> list = new ArrayList<>();
        list.add(12);

        Class<? extends List> clazz = null;
        try {
            clazz = list.getClass();
            Method add = clazz.getDeclaredMethod("add", Object.class);
            //因为在运行期间所有的泛型信息都会被擦掉, 所以在运行期间可以添加任何类型的元素, 正好反射生效于运行时于是就绕过了编译检查
            //JVM 在 运行时 根据字符串 "add" 在类的方法表里去找 add，再反射调用它。
            add.invoke(list, "kl");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }

        System.out.println("byReflectPassErasure:"+list);
        System.out.println("byReflectPassErasure:"+list.get(1));
        Integer x = list.get(1);
        System.out.println("byReflectPassErasure exception:"+x);
    }
}
