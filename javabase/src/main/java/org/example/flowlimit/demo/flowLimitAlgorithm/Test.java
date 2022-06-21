package org.example.flowlimit.demo.flowLimitAlgorithm;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//        fixedTest();
//        slideTest();
        leakyBucketTest();
    }

    private static void leakyBucketTest() {
        LeakybucketDemo leakybucketDemo = new LeakybucketDemo(10);
        ExecutorService service = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 999999999; i++) {
            service.submit(
                    () -> {
                        try {
                            if (leakybucketDemo.tryAcquire()) {
                                System.out.println("线程" + Thread.currentThread().getName() + " 执行业务,当前时间" + LocalDateTime.now().toString());
                                // 模拟业务
//                                TimeUnit.MILLISECONDS.sleep(500);
                            }
                        } catch (Exception e) {
//                                System.out.println("执行失败::线程" + Thread.currentThread().getName() + "失败原因：" + e.getMessage());
                        }
                    }
            );
        }
    }

    private static void slideTest() throws InterruptedException {

        SlideWindowDemo slideWindowDemo = new SlideWindowDemo(30, 1000, 5);
        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 999999; i++) {
            service.submit(
                    () -> {
                        while (true) {
                            try {
                                slideWindowDemo.access();
                                System.out.println("线程" + Thread.currentThread().getName() + " 执行业务,当前时间" + LocalDateTime.now().toString());
                                // 模拟业务
//                                TimeUnit.MILLISECONDS.sleep(500);
                                return;
                            } catch (Exception e) {
//                                System.out.println("执行失败::线程" + Thread.currentThread().getName() + "失败原因：" + e.getMessage());
                            }
                        }

                    });
        }

        TimeUnit.SECONDS.sleep(60);
    }

    private static void fixedTest() {
        FixedWindowDemo fixedWindowDemo = new FixedWindowDemo(3, 1000);
        for (int i = 0; i < 5; i++) {
            new Thread(
                    () -> {
                        try {
                            fixedWindowDemo.access();
                            // 模拟业务
                            TimeUnit.MILLISECONDS.sleep(5000);
                        } catch (Exception e) {
                            System.out.println("执行失败::线程" + Thread.currentThread().getName() + "失败原因" + e.getMessage());
                        }
                    }
                    , "Thread" + i)
                    .start();
        }
    }
}
