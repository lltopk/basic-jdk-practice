package com.lyflexi.collectionpractice.juclist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 16:35
 */
public class UnsafeList {
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
//        currentWrite();
        currentReadWrite();
//        currentRemove();
    }

    /**
     * Exception in thread "10" java.util.ConcurrentModificationException
     * 	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
     * 	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
     * 	at java.base/java.util.AbstractCollection.toString(AbstractCollection.java:456)
     * 	at java.base/java.lang.String.valueOf(String.java:4220)
     * 	at java.base/java.io.PrintStream.println(PrintStream.java:1047)
     * 	at com.lyflexi.basicpractice.collection.list.UnsafeList.lambda$currentWrite$0(UnsafeList.java:27)
     * 	at java.base/java.lang.Thread.run(Thread.java:840)
     */
    public static void currentWrite(){

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * 当读线程在遍历列表时，写线程修改了列表，读线程也是可能会抛出 ConcurrentModificationException。
     *
     * 这是因为 ArrayList 的迭代器在检测到列表被修改时会抛出此异常。
     */
    public static void currentReadWrite(){
        List<Integer> list = new ArrayList<>();

        // 初始化列表
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        // 创建一个读线程
        Thread reader = new Thread(() -> {
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
        });

        // 创建一个写线程
        Thread writer = new Thread(() -> {
            for (int i = 10000; i < 20000; i++) {
                list.add(i);
                // 模拟写操作的延迟
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
     *理想情况下，最终列表的大小应该是 0。
     * 然而，由于 ArrayList 不是线程安全的，实际输出的列表大小可能会大于 0，因为删除操作没有得到妥善处理，导致一些删除操作失败或被覆盖。
     */
    public static void currentRemove()  {
        List<Integer> list = new ArrayList<>();

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
