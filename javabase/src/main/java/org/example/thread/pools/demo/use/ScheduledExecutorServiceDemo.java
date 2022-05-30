package org.example.thread.pools.demo.use;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledExecutorServiceDemo {

    /**
     * Java默认的调度线程池
     */
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) {

    }
}
