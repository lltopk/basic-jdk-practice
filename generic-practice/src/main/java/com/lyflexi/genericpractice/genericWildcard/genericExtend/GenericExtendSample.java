package com.lyflexi.genericpractice.genericWildcard.genericExtend;

import com.lyflexi.genericpractice.genericWildcard.common.Animal;
import com.lyflexi.genericpractice.genericWildcard.common.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/23 17:43
 */


/**
 * 上界通配符
 */
public class GenericExtendSample {

    public static void main(String[] args) {
        List<Dog> animalsDog = new ArrayList<Dog>();
        Dog dog1 = new Dog();
        dog1.setName("dog1");
        Dog dog2 = new Dog();
        dog2.setName("dog2");
        animalsDog.add(dog1);
        animalsDog.add(dog2);
        //解决了泛型集合的赋值问题
        printAnimals(animalsDog);
    }

    /**
     * ？ extend 的特性
     */
    public static void test(){
        List<? extends Animal> animalsDog = new ArrayList<Dog>();  //   可以
        // 但是！编译器："这个盒子可能是装狗的，也可能是装猫的", 我不能让你往里面放任何东物，因为不知道具体是啥"
        //animalsDog.add(new Dog()); // ❌ 编译报错
        //animalsDog.add(new Animal()); // ❌ 还是报错
        Animal dog = animalsDog.get(0); // ✅ 读取没问题
    }

    /**
     * 解决了泛型集合引用的接收问题
     *
     * 接收后的集合仅仅适用于读取元素
     * @param animals
     */
    public static void printAnimals(List<? extends Animal> animals) {
        for (Animal a : animals) { // 安全，因为知道都是Animal
            System.out.println(a.getName());
        }
    }

}
