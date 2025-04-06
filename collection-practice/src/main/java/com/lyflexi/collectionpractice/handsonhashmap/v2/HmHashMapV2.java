package com.lyflexi.collectionpractice.handsonhashmap.v2;


/**
 * @author liuyanoutsee@outlook.com
 *
 * 第二版手写，设计了哈希函数，不再通过单出的比较key值来定位元素。同时引入了拉链发解决哈希冲突
 *
 * 一上来计算哈希值来定位元素，充分利用到数组随机访问的特性，因此性能增强了
 **/
public class HmHashMapV2<K, V> {

    // 数组长度默认2的整数次幂
    // 迎合哈希函数hashcode&(len-1), 低位全1与任何数&运算之后，能够充分利用下标
    private Node<K, V>[] table = new Node[16];

    //引入了拉链发解决哈希冲突，自然元素总量就不再是数组长度了，而是要单独计算
    //当新增元素的时候，size++;
    //当移除元素的时候size--;
    private int size = 0;

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            size++;
            return null;
        }
        while (true) {
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            if (head.next == null) {
                head.next = new Node<>(key, value);
                size++;
                return null;
            }
            head = head.next;
        }
    }

    public V get(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            table[keyIndex] = head.next;
            size--;
            return head.value;
        }
        Node<K, V> pre = head;
        Node<K, V> current = head.next;
        while (current != null) {
            if (current.key.equals(key)) {
                pre.next = current.next;
                size--;
                return current.value;
            }
            pre = pre.next;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    /**
     *
     * @param key
     * @return
     */
    private int indexOf(Object key) {
//        return key.hashCode() % (table.length);
        // 这里使用&运算符来代替%运算符，性能更高
        return key.hashCode() & (table.length - 1);
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
