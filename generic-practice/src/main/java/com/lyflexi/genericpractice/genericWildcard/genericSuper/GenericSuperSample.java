package com.lyflexi.genericpractice.genericWildcard.genericSuper;

import com.lyflexi.genericpractice.genericWildcard.common.Animal;
import com.lyflexi.genericpractice.genericWildcard.common.Dog;
import com.lyflexi.genericpractice.genericWildcard.common.Husky;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/23 17:43
 */

/**
 * 无上界通配符
 */
public class GenericSuperSample {

    public static void main(String[] args) {
        List<Dog> animalsDog = new ArrayList<Dog>();
        Dog dog1 = new Dog();
        dog1.setName("dog1");
        Dog dog2 = new Dog();
        dog2.setName("dog2");
        animalsDog.add(dog1);
        animalsDog.add(dog2);
        //解决了泛型集合的赋值问题
        addDogs(animalsDog);
    }

    /**
     * ？ super 的特性
     */
    public static void test(){
        List<? super Dog> dogs = new ArrayList<Animal>();  //   可以
        dogs.add(new Dog()); // ✅ 可以添加Dog
        dogs.add(new Husky()); // ✅ 可以添加Dog的子类
//      Dog dog = dogs.get(0); // ❌ 编译报错
        Object obj = dogs.get(0); // ✅ 只能以Object接收



        List<? super Dog> dogsObject = new ArrayList<Object>();  //   也可以
        dogsObject.add(new Dog()); // ✅ 可以添加Dog
        dogsObject.add(new Husky()); // ✅ 可以添加Dog的子类
        //Dog dog = dogsObject.get(0); // ❌ 编译报错
        Object dog = dogsObject.get(0); // ✅ 只能以Object接收

    }

    /**
     * 使用确定类型读取元素的时候编译报错.
     *
     * 编译器："这个盒子可能是装Animal的，也可能是装Object的"
     * "我只能保证取出的东西是个Object"
     * @param animals
     */
//    public static void unPrintAnimals(List<? super Animal> animals) {
//        for (Animal a : animals) { // 使用确定类型读取元素的时候编译报错，因为只能读出Object
//            System.out.println(a.getName());
//        }
//    }

    /**
     * 解决了泛型限定符集合无法添加元素的问题
     *
     * 但是接收后的集合，在读取元素的时候不能完全读取（只能读出Object）
     * @param dogs
     */
    public static void addDogs(List<? super Dog> dogs) {
        dogs.add(new Dog()); // 安全，因为容器至少能装Dog
        dogs.add(new Husky()); // Husky是Dog的子类，也没问题
        System.out.println(dogs.size());
    }

}
