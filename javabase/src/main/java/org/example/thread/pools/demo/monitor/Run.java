package org.example.thread.pools.demo.monitor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Run {

    public static void main(String[] args) throws Exception {

        ThreadPoolMoniotor threadPool = new ThreadPoolMoniotor(5, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        new Thread(
                () -> threadPool.doMoniotor(TimeUnit.MILLISECONDS, 100)
        ).start();

        for (int i = 0; i < 100000; i++) {
            threadPool.execute(() -> {
                System.out.print(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
