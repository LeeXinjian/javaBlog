package org.example.redis;


import org.redisson.api.RBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RedisLock {

    public static void main(String[] args) throws IOException {
        RedissonClient redissonClient = RedissionUtil.getRedissonClient("redis.yml");
    }

    /**
     * 可重入锁的实现
     */
    public void reentrantLock(RedissonClient redisson) throws InterruptedException {
        RLock reetrantLock = redisson.getLock("lock");
        reetrantLock.lock();
    }

    /**
     * 公平锁的实现
     */
    public void fairLock(RedissonClient redisson) throws InterruptedException {
        RLock fairLock = redisson.getFairLock("lock");
        fairLock.lock();
    }

    public void blockingQueue(RedissonClient redisson) throws InterruptedException {
        RBlockingQueue<String> queue = redisson.getBlockingQueue("queue");
        queue.offer("123");
        String obj = queue.peek();
        String someObj = queue.poll();
        String ob = queue.poll(10, TimeUnit.MINUTES);
    }
}
