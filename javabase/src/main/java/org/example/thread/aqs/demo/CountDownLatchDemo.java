package org.example.thread.aqs.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CountDownLatchDemo {

    public static final CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        newThread(1, 1000);
        newThread(2, 2000);
        newThread(3, 500);
        newThread(4, 300);
        newThread(5, 1500);

        latch.await();
        System.out.println("所有任务均已完成,耗时" + (System.currentTimeMillis() - start));

    }

    private static void newThread(int num, long time) {
        new Thread(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(time);
                        System.out.println(Thread.currentThread().getName() + "等了" + time + "毫秒");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                , "第" + num + "个线程"
        ).start();
    }
}


class Sync extends AbstractQueuedSynchronizer{


}