package com.lyflexi.basicpractice.finaltest;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/11 16:52
 */

/**
 * 属性包括数组属性用 final 修饰，仅仅意味着这个变量一旦被初始化后，其引用就不能再指向另一个对象。
 * 一旦你想要重新设置final属性的引用将编译不通过。这样外部引用不会干预内部引用，以防止某一天外部引用的数据修改，对内部引用造成混乱
 */
public class FinalArraySamples {
    public static void main(String[] args) {
        final int[] numbers = {1, 2, 3}; // 用final修饰数组

        // 修改数组内部的元素是允许的
        numbers[0] = 4;
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);

        }
        // 但尝试改变数组引用是不允许的，编译会报错
//         numbers = new int[]{4, 5, 6}; // 编译错误：不能为final变量numbers赋值
    }
}