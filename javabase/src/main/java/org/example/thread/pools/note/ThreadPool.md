#线程池详解
## 一、使用线程池showCase
```java
public class ThreadPoolDemo {

  
    /**
     * Fixed线程池
     * 1.构造方法  return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     *
     */
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        // 无返回值提交任务
        fixedThreadPool.execute(()->{
            System.out.println(Thread.currentThread().getName()+"---我开始了---");
        });

        // 有返回值提交任务
        Future<String> submit = fixedThreadPool.submit(() -> "没想到吧，我又回来了");
        try {
            String s = submit.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
```

##二、线程池构造参数分析
```java
public ThreadPoolExecutor(int corePoolSize,
                               int maximumPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               BlockingQueue<Runnable> workQueue,
                               ThreadFactory threadFactory,
                               RejectedExecutionHandler handler)
```
ThreadPoolExecutor参数详解

 1. coolPoolSize 核心线程数 即线程池的基本大小<br />
     当提交一个任务到线程池时，线程池会创建一个线程来执行任务。即使其他空闲的基本线程能够执行新任务也会创建线程。等到需要执行的任务数大于线程池的基本大小时就不在创建。
     如果调用了prestartAllCoreThreads()方法，线程池会提前创建病启动所有的基本线程。
    <br />
 2. maximumPoolSize 最大线程数<br />
   线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数大小于最大线程数，则线程会再创建新的线程执行任务。
    <br />
 3. keepAliveTime
    线程等待终止时间
    <br />
 4. unit
   时间单位
 5. BlockingQueue<Runnable> workQueue 阻塞队列<br />
    用于保存等待执行的阻塞队列
    * ArrayBlockingQueue 基于数据结构的有界阻塞队列，队列按FIFO原则对元素进行排序
    * LinkedBlockingQueue 基于链表的阻塞队列，按FIFO排序。默认大小为Integer.MAX_VALUE
    * SynchronousQueue 不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作会一直阻塞。
    * PriorityBlockingQueue 具有优先级的无限阻塞队列    
 6. ThreadFactory threadFactory 创建线程工厂<br />
    用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。
 7. RejectedExecutionHandler handler 拒绝策略<br />
    当队列和线程池都满了，说明线程池饱和，那么必须采取一种策略处理新提交的新任务。<br />
    7.1 AbrortPolicy: 直接抛出异常<br />
    7.2 CallerRunsPolicy: 只用调用者所在线程来运行任务<br />
    7.3 DiscardOldestPolicy: 丢弃队列里最近的一个任务，并执行当前任务。<br />
    7.4 DiscardPolicy: 不处理，直接丢掉。<br />
##三、JDK默认三种ThreadPoolExecutor线程池参数
* fixedThreadPool  
```java
/**
    * Fixed线程池
    * 1.构造方法  return new ThreadPoolExecutor(nThreads, nThreads,
    *                                       0L, TimeUnit.MILLISECONDS,
    *                                       new LinkedBlockingQueue<Runnable>());
    *
   */
   private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
```

* CachedThreadPool
```java
 /**
     * CachedThreadPool
     *   return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     */
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
```

* singleThreadPool
```java
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```

##四、线程池的执行流程概述
###4.1 线程池的执行流程概述


##五、源码分析

线程池解决的问题是统一管理线程的创建和生命周期,我们平常创建线程时,会重新Runnable的Run方法,调用start方法去开启线程。
此时虚拟机会调用一个实际的操作系统线程去处理Runnable中的Run方法的任务。
对于线程池来说,其解耦了任务和线程的关系,因此线程池其由几个重要的问题去解决。
* 线程池创建后如何shutdown?
* 何时创建线程
* 线程池如何重用线程
* 何时销毁线程
###5.1 线程池构造方法和核心字段
###5.2 线程池的ctl字段
```java
/**
 * 使用原子类维护Integer对象,其中高3位表示线程池状态,低29位表示线程数量 
 */
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

/**
 * 线程池的五个状态
 */
// runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

/**
 * 获取线程池状态
 */
// Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
/**
 * 获取线程池线程数量
 */
private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
```
Integer.SIZE为Integer实际位数即32。
在初始化时，会初始化一个AtomicInteger属性进行线程数量和线程池状态的维护。
由Integer.SIZE可知,COUNT_BITS = 29。
在初始化线程池时，先把线程池的状态默认为RUNNNING，即-1 << COUNT_BITS
-1的二进制表示为

1111 1111 1111 1111 1111 1111 1111 1111

则左移29位得到

1110 0000 0000 0000 0000 0000 0000 0000

ctlOf()方法中使用位或操作,与0或时,获取其本身。
即初始化时前三位111表示RUNNING 后29位表示当前线程数为0。同理的获得其他状态的实际值。

|  状态   | 取值  |  状态含义  | 如何触发此状态  |
|  ----  | ----  |----  |----  |
| RUNNING  | 111 |线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。 |线程池的初始化状态是RUNNING。换句话说，线程池被一旦被创建，就处于RUNNING状态，并且线程池中的任务数为0！
| SHUTDOWN  | 000 |线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。|调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。
| STOP  | 001 |线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。|调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。
| TIDYING  | 010 |当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。|当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。
| TERMINATED  | 011 |线程池彻底终止，就变成TERMINATED状态。|线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED。

![线程池状态](D:\project\interview\javabase\src\main\java\org\example\thread\pools\note\img\线程池状态.png)

###5.3 工具方法runStateOf(int c) workerCountOf(int c)
* runStateOf()方法负责获取线程池的执行状态。
  其中~表示取反。CAPACITY为(1 << COUNT_BITS) - 1。即将

0000 0000 0000 0000 0000 0000 0000 0001

右移29位得

0010 0000 0000 0000 0000 0000 0000 0000

再减一得

0001 1111 1111 1111 1111 1111 1111 1111

由此可知~CAPACITY为

1110 0000 0000 0000 0000 0000 0000 0000

即将高三位置1 其他位置0

假设当前线程池状态为Running、线程个数为3,则c =

1110 0000 0000 0000 0000 0000 0000 0011

那么

1110 0000 0000 0000 0000 0000 0000 0000 &

1110 0000 0000 0000 0000 0000 0000 0011  可得

1110 0000 0000 0000 0000 0000 0000 0000  即仅保留高三位的值。即舍去线程数量,只保留线程状态。

* workerCountOf(int c)方法负责获取线程数量。

假设当前线程池状态为Running、线程个数为3,则c =

1110 0000 0000 0000 0000 0000 0000 0011

那么

1110 0000 0000 0000 0000 0000 0000 0011 &

0001 1111 1111 1111 1111 1111 1111 1111 可得

0000 0000 0000 0000 0000 0000 0000 0011

即仅保留低29位的值。即舍去线程池状态,只保留线程数量。

###5.4 submit() / execute()方法解析
###5.5 Worker.run()
###5.6 shouDown()
###5.7 线程池的生命周期
###5.8 总结
