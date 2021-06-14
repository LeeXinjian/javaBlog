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
当执行put()时获取到数组的坐标后放入值。如果Hash冲突则维护一个尾插链表进行维护，如果链表中元素个数>8则会转为红黑树进行存储。
如果放入元素后，元素size大小大于阈值，则会进行扩容。需要注意的是，HashMap采用懒加载来减少内存占用。即只有在第一次放入元素时候才会初始化Map.

##二、HashMap方法解析
###1.HashMap重要属性与Hash算法
###2.HashMap.put()方法解析
###3.HashMap.resize()扩容方法解析
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
###3.ConncurentHashMap原理解析.resize()扩容方法解析
###4.ConncurentHashMap原理解析.get()方法解析

##五、总结