package com.lyflexi.basicpractice.protect.generalFather;

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

        // This is fine because Test is the same package to Animal which has protected method makeSound
        GeneralFather generalFather = new GeneralFather();
        generalFather.makeSound();
        Son1 mySon1 = new Son1();
        mySon1.makeSound();
        Son2 mySon2 = new Son2();
        mySon2.makeSound();

    }
}