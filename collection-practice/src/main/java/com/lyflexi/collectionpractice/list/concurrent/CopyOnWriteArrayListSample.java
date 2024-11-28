package com.lyflexi.collectionpractice.list.concurrent;

import java.util.Iterator;
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
//        currentWrite();
        currentReadWrite();
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
     * 解决了：
     *
     * CopyOnWriteArrayList 的迭代器是安全的，原因如下：
     *
     * 迭代器访问的是数组快照：
     * 当你调用 iterator() 方法时，CopyOnWriteArrayList 会返回特殊的迭代器COWIterator，这个迭代器依然访问的是当前的数组副本。
     * 由于读操作不需要加锁，迭代器在遍历时不会阻塞写操作。
     *
     * 写操作不影响现有迭代器：
     * 写操作会创建一个新的数组副本，并在新的数组副本上完成写操作。
     * 写操作完成后，array 引用会切换到新的数组，但现有的迭代器仍然访问的是旧的数组副本。
     * 因此，即使在遍历过程中有写操作发生，迭代器也不会抛出 ConcurrentModificationException，也不会看到部分更新的数据。
     */
    public static void currentReadWrite(){
        List<Integer> list = new CopyOnWriteArrayList<>();

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
