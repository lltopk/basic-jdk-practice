package com.lyflexi.basicpractice.protect.abstractFather;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:47
 * @Project: basic-jdk-practice
 */
public class Test {
    public static void main(String[] args) {
        ConcreteStrategy1 concreteStrategy1 = new ConcreteStrategy1();
        concreteStrategy1.process(new JobBo1("jobBo1"));

        ConcreteStrategy2 concreteStrategy2 = new ConcreteStrategy2();
        concreteStrategy2.process(new JobBo2("jobBo2"));
    }
}
