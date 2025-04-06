package com.lyflexi.collectionpractice.handsonhashmap.v1;


import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyanoutsee@outlook.com
 *
 * 第一版手写不涉及哈希操作，也就是说定位目标元素的方式纯通过比较元素的key,而不是通过hashCode来定位
 *
 * 没有利用到数组随机访问的特性，因此性能极低
 **/
public class HmHashMapV1<K, V> {

    private List<Node<K, V>> table = new ArrayList<Node<K,V>>();

    /**
     * 如果存在，则覆盖，同时返回老值
     * 如果不存在，则添加
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        for (Node<K, V> kvNode : table) {
            //如果存在，则覆盖，同时返回老值
            if (kvNode.key.equals(key)) {
                V oldValue = kvNode.value;
                kvNode.value = value;
                return oldValue;
            }
        }
        //如果不存在，则添加
        table.add(new Node<>(key, value));
        return null;
    }

    public V get(K key) {
        for (Node<K, V> kvNode : table) {
            if (kvNode.key.equals(key)) {
                return kvNode.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        for (int i = 0;i<table.size();i++) {
            Node<K, V> kvNode = table.get(i);
            if (kvNode.key.equals(key)) {
                Node<K, V> removedValue = table.remove(i);
                return removedValue.value;
            }
        }
        return null;
    }

    public int size() {
        return this.table.size();
    }


    class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}
