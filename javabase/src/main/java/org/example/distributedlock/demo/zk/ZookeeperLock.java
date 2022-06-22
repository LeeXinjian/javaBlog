package org.example.distributedlock.demo.zk;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.example.zookeeper.ZookeeperUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ZookeeperLock {

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



