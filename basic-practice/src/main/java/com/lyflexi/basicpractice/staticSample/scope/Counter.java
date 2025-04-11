package com.lyflexi.basicpractice.staticSample.scope;

/**
 * 内存分配：静态变量在类加载时由JVM初始化，并且只会在方法区（对于HotSpot虚拟机，就是永久代或元空间）中分配一次内存。
 *
 * 共享性：所有类的实例（对象）共享同一个静态变量副本。也就是说，无论你创建了多少个该类的对象，静态变量的修改会体现在所有对象实例中
 */
public class Counter {
    public static int count = 0; // 静态变量，所有实例共享

    public Counter() {
        count++; // 每次创建对象时增加计数
    }

    public static void showCount() {
        System.out.println("Total Count: " + count);
    }
}