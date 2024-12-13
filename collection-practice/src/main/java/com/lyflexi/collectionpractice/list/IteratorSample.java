package com.lyflexi.collectionpractice.list;

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
public class IteratorSample {
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
        test();

    }

    /**
     * 迭代器的remove之后，再循环内依然可以访问到被remove的引用对象
     */
    public static void test() {
        Iterator<Item> iterator = list.iterator();
        while (iterator.hasNext()){
            Item next = iterator.next();
            if (StringUtils.equals(next.getName(), "item1")) {
                iterator.remove();
            }
            System.out.println("inner while after remove : "+next);
        }

        System.out.println(list);
    }



}
