package com.lyflexi.basicpractice.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 16:41
 */
public class CopyOnWriteArrayListSample {

    private static List<Integer> list = new CopyOnWriteArrayList<>();

    /**
     * 解决了
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        currentWrite();
//        currentRemove();

    }

    /**
     * 解决了
     */
    public static void currentWrite(){
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }


    /**
     * 解决了
     */
    public static void currentRemove()  {
        // 初始化列表
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                list.remove(0);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                list.remove(0);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
        }

        System.out.println("Expected size: 0, Actual size: " + list.size());
    }
}
