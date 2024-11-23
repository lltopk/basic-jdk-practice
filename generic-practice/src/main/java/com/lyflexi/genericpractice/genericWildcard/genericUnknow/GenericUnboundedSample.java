package com.lyflexi.genericpractice.genericWildcard.genericUnknow;

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
 * 无界通配符
 */
public class GenericUnboundedSample {

    public static void main(String[] args) {
        List<Dog> animalsDog = new ArrayList<Dog>();
        Dog dog1 = new Dog();
        dog1.setName("dog1");
        Dog dog2 = new Dog();
        dog2.setName("dog2");
        animalsDog.add(dog1);
        animalsDog.add(dog2);
        //解决了泛型集合的赋值问题
        confusingMethod(animalsDog);
    }


    /**
     *
     * 通配符类型：List<?> 使用了无界通配符（unbounded wildcard），表示这个方法可以接受任何类型的List。这里的?代表未知类型，意味着你可以传入List<String>、List<Integer>等任何类型的列表。
     * 限制：由于类型是未知的，你不能向list添加元素（除了null）。因为编译器不知道你添加的元素是否符合list的实际类型要求。
     * 但是，你可以安全地从list中读取元素，不过读取出的元素类型只能被看作是Object，因为你无法确定列表中元素的确切类型。
     * @param list
     */
    public static void confusingMethod(List<?> list) {
//        list.add(new Dog()); // 报错，因为你不能向list添加元素
        Object o = list.get(0);
        System.out.println(o);//你可以安全地从list中读取元素，不过读取出的元素类型只能被看作是Object，因为你无法确定列表中元素的确切类型。

    }

    /**
     * 所以
     * 如果只读取，用extends
     * 如果只写入，用super
     * 如果既要读又要写，别用通配符！
     * @param list
     */
    public <T> void clearMethod(List<T> list) {

    }
}
