package com.lyflexi.basicpractice.protect.abstractFather.anotherPackage;

import com.lyflexi.basicpractice.protect.abstractFather.ConcreteStrategy1;
import com.lyflexi.basicpractice.protect.abstractFather.JobBo1;
import com.lyflexi.basicpractice.protect.abstractFather.ConcreteStrategy2;
import com.lyflexi.basicpractice.protect.abstractFather.JobBo2;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:47
 * @Project: basic-jdk-practice
 */
public class Test {
    public static void main(String[] args) {
        ConcreteStrategy1 concreteStrategy1 = new ConcreteStrategy1();
        // Test is another package to Animal which has protected method makeSound
//        concreteStrategy1.process(new JobBo1("jobBo1")); // Compilation error!
        // Test is another package to Animal which has protected method makeSound
        ConcreteStrategy2 concreteStrategy2 = new ConcreteStrategy2();
//        concreteStrategy2.process(new JobBo2("jobBo2")); // Compilation error!
    }
}
