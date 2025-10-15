package com.lyflexi.collectionpractice.arraylist;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 20:51
 */


/**
 * list内部会自动修改索引，元素前移同时长度减少，因此需要注意避免漏删的问题
 *
 * 另外，如果使用是get(i)，相当于用的是拷贝后的item，而不是直接使用当前元素的引用，
 * ，因此在循环读取的同时删除元素并不会抛出下面的异常：java.util.ConcurrentModificationException
 * ，只是会出现漏删问题而已
 *
 * 但是如果使用的是迭代器或者foreach直接取的元素引用，再进行删除，则会抛出异常：java.util.ConcurrentModificationException
 */
public class HowToRemoveIndex {
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        errRemove();
//        errRemoveException();
//        testRemove1();
//        testRemove2();
//        testRemove3Recommend();
    }

    /**
     * [a, b, c]
     * 不符合我们的预期，原因是每次remove之后，会自动将后续元素前移：
     * Suspicious 'List. remove()' in loop
     *
     * list.remove(i); 确实会修改 ArrayList 的内部状态，并且也会更新 modCount。
     * 然而，这个代码片段并没有直接使用迭代器来遍历列表，而是通过索引进行遍历和删除操作。
     * 因此，它不会抛出 ConcurrentModificationException
     */
    public static void errRemove() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list.size()"+list.size());
            if (StringUtils.equals(list.get(i), "a")) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }


    /**
     *
     */
    public static void errRemoveException() {
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if (StringUtils.equals(next, "a")) {
                list.remove(next);
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
