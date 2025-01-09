package com.lyflexi.basicpractice.protect.generalFather;

/**
 * @Author: hm
 * @Date: 2025/1/9 21:30
 * @Project: basic-jdk-practice
 */
public class Son2 extends GeneralFather {
    public void say() {
        // Can access protected method from superclass
        makeSound();
        System.out.println("Son2!");
    }
}