package com.lyflexi.basicpractice.protect.generalFather;

/**
 * @Author: hm
 * @Date: 2025/1/9 21:31
 * @Project: basic-jdk-practice
 */
public class Son1 extends GeneralFather {
    public void say() {
        // Can access protected method from superclass
        makeSound();
        System.out.println("Son1!");
    }
}