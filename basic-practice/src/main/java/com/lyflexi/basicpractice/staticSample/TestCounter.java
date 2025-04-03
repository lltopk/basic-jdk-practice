package com.lyflexi.basicpractice.staticSample;

public class TestCounter {
    public static void main(String[] args) {
        // 创建第一个Counter实例
        Counter obj1 = new Counter();
        Counter.showCount(); // 应输出: Total Count: 1

        // 创建第二个Counter实例
        Counter obj2 = new Counter();
        Counter.showCount(); // count已经被改为1了，所以这里输出: Total Count: 2

        // 修改静态变量count的值
        Counter.count = 5;
        Counter.showCount(); // 应输出: Total Count: 5

        // 创建第三个Counter实例后检查count的值
        Counter obj3 = new Counter();
        Counter.showCount(); // 应输出: Total Count: 6

        // 直接通过obj1和obj2访问count，展示它们是共享同一个静态变量
        System.out.println("Access through obj1: " + obj1.count); // 应输出: Access through obj1: 6
        System.out.println("Access through obj2: " + obj2.count); // 应输出: Access through obj2: 6
    }
}