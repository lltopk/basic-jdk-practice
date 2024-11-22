# ConcurrentModificationException源码分析

ConcurrentModificationException通常在尝试以非线程安全的方式修改集合时抛出。

下面从 Java 集合框架中的 ArrayList 和其 Iterator 的实现入手。说明这一异常是如何产生的。

在 ArrayList 类中，有一个名为 modCount 的成员变量，用于记录对列表结构进行修改的次数。每当列表发生变化（例如添加或删除元素）时，这个计数器就会增加。
```java
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    
    // 记录ArrayList被修改的次数
    protected transient int modCount = 0;

    // 添加元素时调用
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    // 删除元素时调用
    public E remove(int index) {
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);
        
        // 元素移动逻辑...
        
        return oldValue;
    }
}
```
ArrayList 的 Iterator 实现中包含了一个 expectedModCount 变量，它初始化为 ArrayList 的 modCount 值。

每次调用 next() 方法时，都会检查 expectedModCount 是否与 modCount 匹配。

如果不匹配，则意味着在迭代过程中有其他线程或代码块修改了列表，此时会抛出 ConcurrentModificationException 异常。
```java
private class Itr implements Iterator<E> {
    int cursor;       // 下一个要返回的元素的索引
    int lastRet = -1; // 上一次返回的元素的索引
    int expectedModCount = modCount;

    public boolean hasNext() {
        return cursor != size;
    }

    public E next() {
        checkForComodification();
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}
```
如何避免出现该异常：

- 并发集合类：对于多线程环境下的集合操作，可以考虑使用 ConcurrentHashMap 或 CopyOnWriteArrayList 等并发安全的集合类。
- 同步机制：如果必须使用非线程安全的集合，并且在多线程环境中操作，可以通过外部同步来保证线程安全。

# CopyOnWriteArrayList

> `CopyOnWriteArrayList` 的设计允许读操作看到老副本的值，这是其写时(add/remove)复制策略的一部分。

尽管`CopyOnWriteArrayList`将数组添加了volatile声明
```java
private transient volatile Object[] array;
```
但由于COW，依然存在遍历操作（读）看到不是最新状态的数据的情况（当并发线程正在添加数据的时候）
```java
    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        synchronized (lock) {
            Object[] es = getArray();
            int len = es.length;
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException(outOfBounds(index, len));
            Object[] newElements;
            int numMoved = len - index;
            if (numMoved == 0)
                newElements = Arrays.copyOf(es, len + 1);
            else {
                newElements = new Object[len + 1];
                System.arraycopy(es, 0, newElements, 0, index);
                System.arraycopy(es, index, newElements, index + 1,
                                 numMoved);
            }
            newElements[index] = element;
            setArray(newElements);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        return elementAt(getArray(), index);
    }
```
同样CopyOnWriteArrayList#remove也是线程安全的COW
```java
    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).  Returns the element that was removed from the list.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        synchronized (lock) {
            Object[] es = getArray();
            int len = es.length;
            E oldValue = elementAt(es, index);
            int numMoved = len - index - 1;
            Object[] newElements;
            if (numMoved == 0)
                newElements = Arrays.copyOf(es, len - 1);
            else {
                newElements = new Object[len - 1];
                System.arraycopy(es, 0, newElements, 0, index);
                System.arraycopy(es, index + 1, newElements, index,
                                 numMoved);
            }
            setArray(newElements);
            return oldValue;
        }
    }
```
但这种设计确保了读操作的高性能和线程安全性。对于大多数读多写少的场景，这种行为是可以接受的。

如果需要严格的读取一致性，可以考虑其他线程安全的集合实现，如 `Vector` 或 `Collections.synchronizedList`，但这些实现的性能通常不如 `CopyOnWriteArrayList`。

# SynchronizedList

`Collections.synchronizedList(new ArrayList<>());`源码简单粗暴，全加synchronized

```java

  static class SynchronizedList<E>
        extends SynchronizedCollection<E>
        implements List<E> {
        private static final long serialVersionUID = -7754090372962971524L;

        final List<E> list;

        SynchronizedList(List<E> list) {
            super(list);
            this.list = list;
        }
        SynchronizedList(List<E> list, Object mutex) {
            super(list, mutex);
            this.list = list;
        }

        public boolean equals(Object o) {
            if (this == o)
                return true;
            synchronized (mutex) {return list.equals(o);}
        }
        public int hashCode() {
            synchronized (mutex) {return list.hashCode();}
        }

        public E get(int index) {
            synchronized (mutex) {return list.get(index);}
        }
        public E set(int index, E element) {
            synchronized (mutex) {return list.set(index, element);}
        }
        public void add(int index, E element) {
            synchronized (mutex) {list.add(index, element);}
        }
        public E remove(int index) {
            synchronized (mutex) {return list.remove(index);}
        }

        public int indexOf(Object o) {
            synchronized (mutex) {return list.indexOf(o);}
        }
        public int lastIndexOf(Object o) {
            synchronized (mutex) {return list.lastIndexOf(o);}
        }

        public boolean addAll(int index, Collection<? extends E> c) {
            synchronized (mutex) {return list.addAll(index, c);}
        }

        public ListIterator<E> listIterator() {
            return list.listIterator(); // Must be manually synched by user
        }

        public ListIterator<E> listIterator(int index) {
            return list.listIterator(index); // Must be manually synched by user
        }

        public List<E> subList(int fromIndex, int toIndex) {
            synchronized (mutex) {
                return new SynchronizedList<>(list.subList(fromIndex, toIndex),
                                            mutex);
            }
        }
```

但是需要特别注意synchronizedList的迭代器不是安全的：

如果使用到了synchronizedList的迭代器，你需要在使用迭代器时手动加锁，确保迭代器在并发读写场景下的线程安全!!!

```java

    /**
     * synchronizedList依然并发异常问题是因为 Collections.synchronizedList 虽然确保了单个方法调用的线程安全，例如 add、remove、get 等。
     * 但它并不能防止在多线程环境中对集合的迭代器进行并发修改。具体来说，Collections.synchronizedList 返回的列表的迭代器并不是线程安全的。
     *        public Iterator<E> iterator() {
     *             return c.iterator(); // Must be manually synched by user!
     *         }
     * 因此在多线程环境中使用迭代器时，可能会抛出 ConcurrentModificationException。
     * 解决方案：
     * 你需要在使用迭代器时手动加锁，确保迭代器的遍历操作是线程安全的。
     */
    public static void currentReadWrite() {
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
```