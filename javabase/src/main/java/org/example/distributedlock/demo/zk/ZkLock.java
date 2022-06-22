package org.example.distributedlock.demo.zk;

import lombok.Data;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

@Data
public class ZkLock {
    /**
     * 锁实现
     */
    private final InterProcessMutex lock;
    /**
     * 获取锁后操作
     */
    private final FakeLimitedResource resource;
    /**
     * 客户端名称
     */
    private final String clientName;

    // 创建这个排它锁.
    public ZkLock(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        // 这就是 curator提供的 排它锁.
        lock = new InterProcessMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        // 这里就是去获取锁, curator的排它锁必须设置超时时间. 根据业务需求设置.
        if (!lock.acquire(time, unit)) {
            // 超时抛出异常.
            throw new IllegalStateException(clientName + " could not acquire the lock");
        }
        try {
            // 使用资源.
            System.out.println(clientName + " has the lock");
            resource.use();
        } finally {
            // 释放锁.
            System.out.println(clientName + " releasing the lock");
            lock.release(); // always release the lock in a finally block
        }
    }

}
