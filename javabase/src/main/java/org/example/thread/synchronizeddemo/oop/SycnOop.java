package org.example.thread.synchronizeddemo.oop;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SycnOop {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        System.out.println();
        System.out.println("lixinjian1-=-=-=-=初始标记-=-=-=-=-=" + ClassLayout.parseInstance(lock).toPrintable());

        // A线程去偏向锁
        flag = true;
        new Thread(
                () ->
                {
                    while (true) {
                        if (flag) {
                            synchronized (lock) {
                                System.out.println(
                                        "尝试获取锁 -=-=-=-=-= "
                                                + Thread.currentThread().getName()
                                                + " -=-=-=-=-= "
                                                + ClassLayout.parseInstance(lock).toPrintable());
                            }
                            break;
                        }
                    }
                }
        ).start();

        TimeUnit.SECONDS.sleep(2);
        CountDownLatch latch = new CountDownLatch(2);
        Object lockTemp = new Object();


        // 首先线程抢占锁。此时为偏向锁
        new Thread(
                () -> {
                    synchronized (lockTemp) {
//                        System.out.println(
//                                "尝试获取锁 -=-=-=-=-= "
//                                        + Thread.currentThread().getName()
//                                        + " -=-=-=-=-= "
//                                        + ClassLayout.parseInstance(lockTemp).toPrintable());
                        latch.countDown();
                    }
                }
        ).start();


        //  默认不可重偏向,则此时升级为轻量级锁。Kclass中Epoch字段加1
        synchronized (lockTemp) {
//            System.out.println(
//                    "尝试获取锁 -=-=-=-=-= "
//                            + Thread.currentThread().getName()
//                            + " -=-=-=-=-= "
//                            + ClassLayout.parseInstance(lockTemp).toPrintable());
        }
        latch.countDown();
        latch.await();

        Thread.sleep(2000);


        // A线程死掉 理论上 锁偏向线程ID不会改变 所以此时 应该是A线程的线程ID
        System.out.println("重偏向之前" + ClassLayout.parseInstance(lock).toPrintable());

        // 主线程获取 -- 重偏向主线程
        synchronized (lock) {
            System.out.println(
                    "尝试获取锁 -=-=-=-=-= "
                            + Thread.currentThread().getName()
                            + " -=-=-=-=-= "
                            + ClassLayout.parseInstance(lock).toPrintable());
        }
    }

}
