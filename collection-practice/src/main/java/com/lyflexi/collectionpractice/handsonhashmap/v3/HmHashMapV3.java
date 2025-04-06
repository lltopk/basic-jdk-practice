package com.lyflexi.collectionpractice.handsonhashmap.v3;


/**
 * @author liuyanoutsee@outlook.com
 * 仅仅有上一版的哈希函数还不够，当哈希冲突加剧，势必链表会边长，因此性能又退化为了ON
 *
 * 手写扩容函数，头插法性能更好，因为头插法扩容期间无需遍历新链表的节点
 *
 * 扩容比较耗时，因此扩容期间很有可能业务上碰巧有并发操作，头插法会导致并发扩容场景下的死循环问题，所以JDK8之后改为了尾插法扩容
 **/
public class HmHashMapV3<K, V> {

    private Node<K, V>[] table = new Node[16];

    private int size = 0;

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            size++;
            resizeIfNecessary();
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
                resizeIfNecessary();
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


    private void resizeIfNecessary() {
        if (this.size < table.length * 0.75) {
            return;
        }
        Node<K, V>[] newTable = new Node[this.table.length * 2];
        for (Node<K, V> head : this.table) {
            if (head == null) {
                continue;
            }
            //current是旧链表节点
            Node<K, V> current = head;
            while (current != null) {
                int newIndex = current.key.hashCode() & (newTable.length - 1);
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    Node<K, V> next = current.next;
                    current.next = null;
                    current = next;
                } else {
                    Node<K, V> next = current.next;
                    //这行代码，头插法会导致并发扩容场景下的死循环问题
                    //eg. 线程1：current节点正在头插
                    //eg. 线程2：已经完成了current节点的头插
                    //end. 结果就是导致了current节点的next指向了自己， 后续总有一天用户会get到这个成环的节点，就会造成CPU 100%
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                    current = next;
                }
            }
        }
        this.table = newTable;
        System.out.println("扩容了，扩容到" + this.table.length);
    }

    public int size() {
        return size;
    }

    private int indexOf(Object key) {
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
