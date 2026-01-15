package com.lyflexi.genericpractice.genericWildcard.genericExtend;

import com.lyflexi.genericpractice.genericWildcard.common.IAnimalAction;
import com.lyflexi.genericpractice.genericWildcard.common.Dog;
import com.lyflexi.genericpractice.genericWildcard.common.Husky;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/23 17:43
 */


/**
 * 无下界通配符
 */
public class GenericExtendSample {

    public static void main(String[] args) {
        printAnimal(Dog.class, "my name is dog");
        printAnimal(Husky.class, "my name is husky");
    }


    /**
     * 使用? extend实现类似于面向接口编程的效果
     * @param aClass
     */
    public static void printAnimal(Class<? extends IAnimalAction> aClass, String key) {
        try {
            IAnimalAction action = aClass.newInstance();
            try {
                Field name = aClass.getField("name");
                name.set(action,key);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            action.print();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
