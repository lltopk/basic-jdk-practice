package com.lyflexi.basicpractice.protect.generalFather.anotherPackage;

import com.lyflexi.basicpractice.protect.generalFather.GeneralFather;
import com.lyflexi.basicpractice.protect.generalFather.Son1;
import com.lyflexi.basicpractice.protect.generalFather.Son2;

/**
 * @Author: hm
 * @Date: 2025/1/9 21:31
 * @Project: basic-jdk-practice
 */
public class Test {
    public static void main(String[] args) {

        Son1 son1 = new Son1();
        Son2 son2 = new Son2();

        // This is fine because bark() and meow() are public
        son1.say();
        son2.say();

        // However, Test is another package to Animal which has protected method makeSound
//        GeneralFather animal = new GeneralFather();
//        animal.makeSound();// Compilation error!
//        Son1 mySon1 = new Son1();
//        mySon1.makeSound(); // Compilation error!
//        Son2 mySon2 = new Son2();
//        mySon2.makeSound(); // Compilation error!
    }
}