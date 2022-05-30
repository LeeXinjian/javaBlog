package org.example.thread.pools.demo.monitor;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadPoolMoniotor extends ThreadPoolExecutor {

    public ThreadPoolMoniotor(
            int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public void doMoniotor(TimeUnit unit, int time) {
        while (true) {
            System.out.println(
                    "core：" + this.getCorePoolSize());
            System.out.println(
                    "最大线程数：" + this.getMaximumPoolSize());

            System.out.println(
                    "当前活动线程数：" + this.getActiveCount());

            System.out.println(
                    "队列已用容量：" + this.getQueue().size());
            System.out.println(
                    "队列剩余容量：" + this.getQueue().remainingCapacity());

            try {
                unit.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
