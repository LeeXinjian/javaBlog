package org.example.thread.pools.demo;


import java.util.concurrent.*;

/**
 *
 *  public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler)
 *
 * ThreadPoolExecutor参数详解
 * 1. coolPoolSize 核心线程数 即线程池的基本大小
 *    当提交一个任务到线程池时，线程池会创建一个线程来执行任务。即使其他空闲的基本线程能够执行新任务也会创建线程。等到需要执行的任务数大于线程池的基本大小时就不在创建。
 *    如果调用了prestartAllCoreThreads()方法，线程池会提前创建病启动所有的基本线程。
 *
 * 2. maximumPoolSize 最大线程数
 *    线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数大小于最大线程数，则线程会再创建新的线程执行任务。
 *
 * 3. keepAliveTime
 *    线程等待终止时间
 *
 * 4. unit
 *    时间单位
 *
 * 5. BlockingQueue<Runnable> workQueue 阻塞队列
 *    用于保存等待执行的阻塞队列
 *    5.1 ArrayBlockingQueue 基于数据结构的有界阻塞队列，队列按FIFO原则对元素进行排序
 *    5.2 LinkedBlockingQueue 基于链表的阻塞队列，按FIFO排序。默认大小为Integer.MAX_VALUE
 *    5.3 SynchronousQueue 不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作会一直阻塞。
 *    5.4 PriorityBlockingQueue 具有优先级的无限阻塞队列
 *
 * 6. ThreadFactory threadFactory 创建线程工厂
 *    用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。
 *
 * 7. RejectedExecutionHandler handler 拒绝策略
 *    当队列和线程池都满了，说明线程池饱和，那么必须采取一种策略处理新提交的新任务。
 *
 *    7.1 AbrortPolicy: 直接抛出异常
 *    7.2 CallerRunsPolicy: 只用调用者所在线程来运行任务
 *    7.3 DiscardOldestPolicy: 丢弃队列里最近的一个任务，并执行当前任务。
 *    7.4 DiscardPolicy: 不处理，直接丢掉。
 */
public class ThreadPoolDemo {

    /**
     * CachedThreadPool
     *   return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     */
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * singleThreadPool
     */
    private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    /**
     * Fixed线程池
     * 1.构造方法  return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     *
     */
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    /**
     * 自己自定义的线程池
     */
    private static ExecutorService selfThreaPool = new ThreadPoolExecutor(
            10,
            10,
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10));


    public static void main(String[] args) {
//        int count = 0;
//        point:
//        for(int i = 0 ;  i < 2 ; i++){
//            for (int j=  0; j < 5 ; j++){
//                if(j == 2){
//                    continue point;
//                }
//                System.out.println(count++);
//            }
//        }

        fixedThreadPool.execute(()->{
            System.out.println(Thread.currentThread().getName()+"---我开始了---");
        });

        Future<String> submit = fixedThreadPool.submit(() -> "没想到吧，我又回来了");
        try {
            String s = submit.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        fixedThreadPool.shutdown();

    }
}
