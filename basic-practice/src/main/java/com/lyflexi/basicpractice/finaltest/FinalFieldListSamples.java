package com.lyflexi.basicpractice.finaltest;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/11 16:52
 */

import java.util.ArrayList;

/**
 * 属性包括数组属性用 final 修饰，仅仅意味着这个变量一旦被初始化后，其引用就不能再指向另一个对象。
 * 一旦你想要重新设置final属性的引用将编译不通过。这样外部引用不会干预内部引用，以防止某一天外部引用的数据修改，对内部引用造成混乱
 */
public class FinalFieldListSamples {
    public static void main(String[] args) {
        final ArrayList<String> list = new ArrayList<>();// 用final修饰集合
        // 修改数组内部的元素是允许的
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        // 修改数组内部的元素是允许的
        list.remove(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));

        }
        // 但尝试改变数组引用是不允许的，编译会报错
//        list = new ArrayList<>(); // 编译错误：不能为final变量list重新设置引用
    }
}