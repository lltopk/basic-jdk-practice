package com.lyflexi.genericpractice.genericWildcard.genericSuper;

import com.lyflexi.genericpractice.genericWildcard.common.AbstractAnimal;
import com.lyflexi.genericpractice.genericWildcard.common.IAnimalAction;
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
        List<IAnimalAction> animalsDog = new ArrayList<>();
        //解决了泛型集合无法添加元素的问题
        addDogs(animalsDog);
    }

    /**
     * super解决了泛型限定符集合无法添加元素的问题
     *
     * 但局限性是接收后的集合，在读取元素的时候不能完全读取（只能读出Object）
     * @param animals
     */
    public static void addDogs(List<? super IAnimalAction> animals) {
        animals.add(new Dog());
        animals.add(new Husky());
        System.out.println(animals.size());

        for (Object o : animals) { // 使用具体类型读取元素的时候编译报错，因为只能读出Object
            System.out.println(o);
        }
    }

}
