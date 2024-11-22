package com.lyflexi.basicpractice.collection.list;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 20:51
 */
public class HowToRemove {
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
//        errRemove();
//        testRemove1();
//        testRemove2();
        testRemove3Recommend();
    }

    /**
     * [a, b, c]
     * 不符合我们的预期，原因是每次remove之后，会自动将后续元素前移：
     * Suspicious 'List. remove()' in loop
     */
    public static void errRemove() {
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.equals(list.get(i), "a")) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    /**
     * [b, c] 满足预期
     *
     * 解决方案：每次remove之后，手动再i--
     */
    public static void testRemove1() {
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.equals(list.get(i), "a")) {
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }

    /**
     * [b, c] 满足预期
     *
     * 解决方案：反向遍历
     */
    public static void testRemove2() {
        for (int i = list.size()-1; i >=0 ; i--) {
            if (StringUtils.equals(list.get(i), "a")) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    /**
     * [b, c] 满足预期
     *
     * 解决方案：使用迭代器, 最推荐的方式
     */
    public static void testRemove3Recommend() {
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            if (StringUtils.equals(iterator.next(), "a")) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }




}
