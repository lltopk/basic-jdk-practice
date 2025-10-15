package com.lyflexi.collectionpractice.arraylist;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 20:51
 */
public class HowToRemoveObject {
    static ArrayList<Item> list = new ArrayList<>();

    @Data
    static class Item{
        String name;
    }
    public static void main(String[] args) {
        Item item1 = new Item();
        item1.setName("item1");
        Item item2 = new Item();
        item2.setName("item2");
        Item item3 = new Item();
        item3.setName("item3");
        list.add(item1);
        list.add(item2);
        list.add(item3);
//        errForeachRemove();
        errIteratorRemove();
//        uglyRemove();
//        recommendRemove();
    }
    /**
     *
     * ConcurrentModificationException
     *
     * foreach 写法实际上是对的 Iterable、hasNext、next方法的简写。
     *
     * 因此从List.iterator()源码着手分析，跟踪iterator()方法，该方法返回了 Itr 迭代器对象。
     */
    public static void errForeachRemove() {
        for(Item item:list){
            if (StringUtils.equals(item.getName(), "item1")) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    /**
     * ConcurrentModificationException
     */
    public static void errIteratorRemove() {
        Iterator<Item> iterator = list.iterator();
        while (iterator.hasNext()){
            Item next = iterator.next();
            if (StringUtils.equals(next.getName(), "item1")) {
                list.remove(next);
            }
        }
        System.out.println(list);
    }
    /**
     * 就地复制，不报错，但是很丑陋
     */
    public static void uglyRemove() {
        for (int i = 0; i < list.size(); i++) {
            //就地复制
            Item item = list.get(i);
            if (StringUtils.equals(item.getName(), "item1")) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    /**
     * 调用迭代器的remove
     */
    public static void recommendRemove() {
        Iterator<Item> iterator = list.iterator();
        while (iterator.hasNext()){
            Item next = iterator.next();
            if (StringUtils.equals(next.getName(), "item1")) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }




}
