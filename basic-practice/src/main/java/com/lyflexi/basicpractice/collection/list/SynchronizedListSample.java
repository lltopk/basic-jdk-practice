package com.lyflexi.basicpractice.collection.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 16:41
 */
public class SynchronizedListSample {


    /**
     * 解决了
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
//        currentWrite();
        currentReadWrite();
//        currentRemove();

    }

    /**
     * 解决了
     */
    public static void currentWrite(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * synchronizedList依然并发异常问题是因为 Collections.synchronizedList 虽然确保了单个方法调用的线程安全，
     * 但它并不能防止在多线程环境中对集合的迭代器进行并发修改。具体来说，Collections.synchronizedList 返回的列表的迭代器并不是线程安全的。
     *
     * 问题分析
     * 迭代器的线程安全性：
     * Collections.synchronizedList 只确保了单个方法调用的线程安全，例如 add、remove、get 等。
     * 但是，迭代器的 hasNext 和 next 方法并没有被同步，因此在多线程环境中使用迭代器时，可能会抛出 ConcurrentModificationException。
     * 解决方案：
     * 你需要在使用迭代器时手动加锁，确保迭代器的遍历操作是线程安全的。
     */
    public static void currentReadWrite(){
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        // 初始化列表
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        // 创建一个读线程
        Thread reader = new Thread(() -> {
            synchronized (list) {
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Integer value = iterator.next();
                    // 模拟读操作的延迟
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Reader: " + value);
                }
            }
        });

        // 创建一个写线程
        Thread writer = new Thread(() -> {
            synchronized (list) {
                for (int i = 10000; i < 20000; i++) {
                    list.add(i);
                    // 模拟写操作的延迟
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        reader.start();
        writer.start();

        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
        }

    }
    /**
     * 解决了
     */
    public static void currentRemove()  {
         List<Integer> list = Collections.synchronizedList(new ArrayList<>());

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
