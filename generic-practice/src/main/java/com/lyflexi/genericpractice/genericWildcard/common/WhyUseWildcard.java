package com.lyflexi.genericpractice.genericWildcard.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/23 17:41
 */
public class WhyUseWildcard {
    // 假设我们在写一个动物园系统
    List<Dog> dogs = new ArrayList<>();
//    List<Animal> animals = dogs; // ❌ 编译报错！但我们明明知道狗是动物啊，为啥不能这样写？
}
