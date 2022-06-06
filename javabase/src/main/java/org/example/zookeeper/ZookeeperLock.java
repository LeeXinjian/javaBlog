package org.example.zookeeper;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class LockingExample {

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;

    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {

        try (CuratorFramework client = new ZookeeperUtils().getCuratorFramework("zk.yml")) {
            // 启动
            client.start();
            // 我们去创建锁.
            ZkLock lock = new ZkLock(client, PATH, new FakeLimitedResource(), "Client");
            // 执行
            for (int i = 0; i < QTY; ++i) {
                new Thread(
                        () -> multiTask(lock)
                ).start();
            }
        }
    }

    @SneakyThrows
    private static void multiTask(ZkLock lock) {
        lock.doWork(10, TimeUnit.MINUTES);
    }

}


class FakeLimitedResource {
    private final AtomicBoolean inUse = new AtomicBoolean(false);

    public void use() throws InterruptedException {
        // in a real application this would be accessing/manipulating a shared resource
        if (!inUse.compareAndSet(false, true)) {
            throw new IllegalStateException("Needs to be used by one client at a time");
        }

        try {
            // 模拟真实操作时长.
            Thread.sleep((long) (100 * Math.random()));
        } finally {
            // 最后我们重置状态量.
            inUse.set(false);
        }
    }
}


