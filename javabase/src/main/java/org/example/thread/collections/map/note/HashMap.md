#HashMap、ConncurrentHashMap源码详解
##一、HashMap原理概览
HashMap是一个用来储存key,value键值对数据集合。此集合的设计目的为可以根据Key快速获取value。
HashMap的实现方式是一个数组的链表。HashMap中有几个关键的属性。
size表示当前元素总个数，
DEFAULT_INITIAL_CAPACITY(容量)表示数据的长度（一定是2的次方）
LOAD_FACTOR表示扩展因子，
threshold(阈值)=扩展因子*DEFAULT_INITIAL_CAPACITY的大小，
和一个Entry[DEFAULT_INITIAL_CAPACITY]数组。
Entry中存放了key,value,key的Hash值,和下个元素的指针。
HashMap的hash算法为对Key取hashCode进行重Hash，
重Hash算法会根据Key的hashCode的高16位和低16位进行取与运算获得最终的Hash值，得到的Hash值与数据长度-1进行与运算。
这样相当于截取了hash值的可以获得Hash长度尾数的尾部截取，来保证尽可能减少hash冲突。
当执行put()时获取到数组的坐标后放入值。如果Hash冲突则维护一个尾插链表进行维护，如果链表中元素个数>8且整个哈希表元素大于64（不足和优先扩容）则会转为红黑树进行存储。
如果放入元素后，元素size大小大于阈值，则会进行扩容。需要注意的是，HashMap采用懒加载来减少内存占用。即只有在第一次放入元素时候才会初始化Map.

##二、HashMap方法解析
###1.HashMap重要属性
HashMap中会有一个数组存储对应的元素，有一个控制扩容的负载因子（load_factory）,有个SIze存储了对象中存储了多少实例，具体关键属性如何使用，可以看put放方法中的逻辑。
```java
  /* ---------------- Fields -------------- */

    /**
     * table，使用到才初始化，必要的时候会扩容。长度会被赋值为2次幂
     * The table, initialized on first use, and resized as necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow bootstrapping mechanics that are currently not needed.)
     */
    transient Node<K,V>[] table;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     */
    transient Set<Map.Entry<K,V>> entrySet;

    /**
     * The number of key-value mappings contained in this map.
     */
    transient int size;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    transient int modCount;

    /**
     * The next size value at which to resize (capacity * load factor).
     *
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;

    /**
     * The load factor for the hash table.
     *
     * @serial
     */
    final float loadFactor;
```
###2.HashMap.put()方法解析
```java

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    
    
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 如果对应桶没有数据，直接往里面放置元素
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        // hash冲突的处理
        else {
            Node<K,V> e; K k;
            // 获取桶的第一个元素判断他是否是重复key
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 是否已经转为红黑树，如果是用红黑树的put方法
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 遍历链表，确认当前元素不重复
                for (int binCount = 0; ; ++binCount) {
                    // 遍历到最后都不重复了，新建结点。如果链表长度>8走红黑树判断逻辑
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            // 注意此处doubleCheck了一下是不是 < 64 , 小于64则有限扩容
                            treeifyBin(tab, hash);
                        break;
                    }
                    // key重复，标记一下重复的key,
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // 对重复的key赋新的值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 扩容逻辑
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```
###3.HashMap.resize()扩容方法解析
```java

    /**
     * 初始化或者扩容
     * Initializes or doubles table size.  
     * 
     * 如果为null,根据阈值中的初始容量目标进行分配。
     * If null, allocates in accord with initial capacity target held in field threshold.
     * 
     * 否则，我们扩大为当前的两倍，当前桶中的元素会仍然放在当前桶中，或者直接两倍扩容到当前下标两倍的桶中
     * Otherwise, because we are using power-of-two expansion, the elements from each bin must either stay at same index, or move with a power of two offset in the new table.
     *
     * @return the table
     */
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        // 根据情况来计算新的容量和新的扩容门槛
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        // 分配一个数组空间
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        // oldTab不为空。即迁移的逻辑
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```
###4.HashMap.get()方法解析

##三、Hashmap线程不安全原因和解决方法
线程不安全的根本原因是多线程对公共资源的抢占。可以看到，HashMap在对Entry数组的操作、put、扩容时对链表的操作，完全没有进行资源锁定。因此可能会出现线程问题。
我们可以对所有方法进行加锁来实现只能有一个线程对HashMap操作。这便是HashTable的解决方案。但是这个方案的缺点也很明显。
因为HashMap中Hash算法几乎可以保证了仅有很小的几率可以发生Hash冲突，也就是意味着如果多个线程对Entry数组中不同下标的资源操作时并不会产生问题。
因此有了ConncurentHashMap。ConncurentHashMap在1.7以前，使用分段来控制锁定Entry范围。在1.8以后使用CAS+synchronized来实现锁操作，锁的范围主要是同一个数组下标内的元素,
以此实现的最小粒度的锁定。

##四、ConncurentHashMap原理解析
###1.ConncurentHashMap原理解析重要属性与Hash算法
###2.ConncurentHashMap原理解析.put()方法解析


```java

    /** Implementation for put and putIfAbsent */
    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
```

HashMap中扩容方法和初始化方法是分开的,毕竟初始化方法只能一个线程做，初始化方法通过CAS来保证线程安全，如果其他方法判断检测到当前正在执行初始化，则让出时间片Thread.yield();
```java

    /**
     * Initializes table, using the size recorded in sizeCtl.
     */
    private final Node<K,V>[] initTable() {
        Node<K,V>[] tab; int sc;
        while ((tab = table) == null || tab.length == 0) {
            if ((sc = sizeCtl) < 0)
                Thread.yield(); // lost initialization race; just spin
            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                try {
                    if ((tab = table) == null || tab.length == 0) {
                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                        @SuppressWarnings("unchecked")
                        Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                        table = tab = nt;
                        sc = n - (n >>> 2);
                    }
                } finally {
                    sizeCtl = sc;
                }
                break;
            }
        }
        return tab;
    }
```

其他方法跟HashMap基本一致，需要注意特殊注意的方法主要是，可以从中吸取多线程的问题处理思想，解决自己的问题。
* 累加方法 addCount(1L, binCount);
* 扩容方法 helpTransfer(tab, f);



累加方法 addCount(1L, binCount) ：
```java


    /**
     * Adds to count, and if table is too small and not already
     * resizing, initiates transfer. If already resizing, helps
     * perform transfer if work is available.  Rechecks occupancy
     * after a transfer to see if another resize is already needed
     * because resizings are lagging additions.
     *
     * @param x the count to add
     * @param check if <0, don't check resize, if <= 1 only check if uncontended
     */
    private final void addCount(long x, int check) {
        CounterCell[] as; long b, s;
        // 在并发put元素的时候，为了保证效率，如果没有竞争则直接加元素值。
        // 如果有竞争，使用了counterCells模式去进行元素累加，其会从线程中取随机数，放到counterCells数组中，在实际调用size()方法时，
        // 才会取BASECOUNT + counterCells中存储的中的值进行累加返回。
        // 所以concurrentHashMap的加和并不是实时的。
        // 如果counterCells 不为空则直接进行counterCells模式计数
        if ((as = counterCells) != null ||
            // 如果counterCells为null使用CAS尝试累加，累加失败说明有竞争，有竞争则使用counterCells模式进行累加
            !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) {
            
            CounterCell a; long v; int m;
            boolean uncontended = true;
            
            // counterCells没有初始化
            if (as == null || (m = as.length - 1) < 0 ||
                // 有初始化单当前槽内无值
                (a = as[ThreadLocalRandom.getProbe() & m]) == null ||
                // 直接将当前槽数据++ , 失败走总的fullAddCount方法累加。
                !(uncontended =
                  U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
                  fullAddCount(x, uncontended);
                  return;
            }
            
            if (check <= 1)
                return;
            
            s = sumCount();
        }
      
        ...... 省略扩容逻辑
    }
```
###3.ConncurentHashMap原理解析.resize()扩容方法解析
###4.ConncurentHashMap原理解析.get()方法解析

##五、总结