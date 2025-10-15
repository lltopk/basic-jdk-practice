package com.lyflexi.collectionpractice.arraylist.modCount;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/4/12 21:27
 * @Project: basic-jdk-practice
 * @Version: 1.0.0
 * @Description:
 */
public class ForbidFor {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }};
        // forbidFor
//        forbidFor(list);
        // forceAdd
        forceAdd(list);

    }

    /**
     * 禁止在迭代期间新增元素，这将会修改modCount，但没有更新expectedModCount
     *
     * ConcurrentModificationException
     * @param list
     *
     *
     *     private class Itr implements Iterator<E> {
     *         int cursor;       // index of next element to return
     *         int lastRet = -1; // index of last element returned; -1 if no such
     *         int expectedModCount = modCount;
     *
     *         // prevent creating a synthetic constructor
     *         Itr() {}
     *
     *         public boolean hasNext() {
     *             return cursor != size;
     *         }
     *
     *         @SuppressWarnings("unchecked")
     *         public E next() {
     *             checkForComodification();
     *             int i = cursor;
     *             if (i >= size)
     *                 throw new NoSuchElementException();
     *             Object[] elementData = ArrayList.this.elementData;
     *             if (i >= elementData.length)
     *                 throw new ConcurrentModificationException();
     *             cursor = i + 1;
     *             return (E) elementData[lastRet = i];
     *         }
     *
     *         final void checkForComodification() {
     *             if (modCount != expectedModCount)
     *                 throw new ConcurrentModificationException();
     *         }
     */
    private static void forbidFor(List<Integer> list) {
        for (Integer i : list) {
            System.out.println(i);
            /**
             *     public boolean add(E e) {
             *         modCount++;
             *         add(e, elementData, size);
             *         return true;
             *     }
             */
            list.add(i);// java.util.ConcurrentModificationException
        }
    }

    private static void forceAdd(List<Integer> list) {
        for (Integer i : list) {
            list.add(i);
            //直接退出，避免下次next java.util.ConcurrentModificationException
            break;
        }
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
