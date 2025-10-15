package com.lyflexi.collectionpractice.linkedlist;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/15 22:24
 * @description
 */
@Slf4j
public class LinkedListSample {
    public static void main(String[] args) {
//        modifyHeapContent();
        modifyReferenceSelf();
    }

    /**
     * 修改引用指向地址中的内容, 将影响指向同地址的所有引用
     */
    public static void modifyHeapContent() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        log.info("list [{}]",list);

        List<String> newList = list;
        list.remove(0);
        log.info("newList [{}]",newList);
    }

    /**
     * 将引用指向新的地址, 不影响原有复制出来的临时引用的指向以及原地址的内容
     *
     * 1. Node<E> prev = l.prev; 此时prev和l.prev指向同一地址内容
     * prev------------------------------
     *                                  |
     *                                  |
     * l.prev----------------------> content
     *
     *
     * 2. l.prev = null// help GC, 此时不影响原有复制出来的临时引用prev的指向, 依然是content
     * prev----------------------------> content
     *
     *
     * l.prev----------------------> null
     */
    public static void modifyReferenceSelf() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("hello");
        linkedList.add("world");
        log.info("linkedList [{}]",linkedList);
        /**
         *     private E unlinkLast(Node<E> l) {
         *         // assert l == last && l != null;
         *         final E element = l.item;
         *         final Node<E> prev = l.prev;
         *         l.item = null;
         *         l.prev = null; // help GC
         *         last = prev;
         *         if (prev == null)
         *             first = null;
         *         else
         *             prev.next = null;
         *         size--;
         *         modCount++;
         *         return element;
         *     }
         */
        linkedList.removeLast();
        log.info("linkedList [{}]",linkedList);
    }
}
