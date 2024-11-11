package com.lyflexi.basicpractice.finaltest;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/11 16:52
 */

/**
 * String是不可变的：
 * 1. 将str2指向str1
 * 2. 改变str2内容
 * 3. 不影响str1原有的值
 * 
 * 究其原因是String内部实现用的是final
 */
public class FinalStringFieldSamples {
    public static void main(String[] args) {
        String str1 = new String("Hello World");

        String str2 = str1;

        str2 = "Hello lyflexi";

        System.out.println(str2);
        System.out.println(str1);
    }
}