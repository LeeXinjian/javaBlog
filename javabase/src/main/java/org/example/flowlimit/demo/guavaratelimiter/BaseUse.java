package org.example.flowlimit.demo.guavaratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseUse {

    public static void main(String[] args) throws InterruptedException {
        // 限流使用示例
        limit();
        // 预热
//        warmupLimit();
    }

    private static void warmupLimit() {
        RateLimiter r = RateLimiter.create(10, 5, TimeUnit.SECONDS);
        while (true) {
            r.acquire(1);
            System.out.println("get 1 tokens: " + LocalDateTime.now().toString() + "s");
        }
    }

    private static void limit() {
        AtomicInteger count = new AtomicInteger(0);
        //新建一个每秒限制10个的令牌桶
        RateLimiter rateLimiter = RateLimiter.create(10.0);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000000; i++) {
            executor.execute(
                    () -> {
                        //获取令牌桶中一个令牌，最多等待1秒
                        if (rateLimiter.tryAcquire(1, 10, TimeUnit.MINUTES))
                            System.out.println(
                                    Thread.currentThread().getName() +
                                            " " + LocalDateTime.now().toString()
                                            + " " + count.incrementAndGet()
                            );
                    });
        }

        executor.shutdown();
    }

}
