package org.example.distributedlock.demo.zk;

import java.util.concurrent.atomic.AtomicBoolean;

public class FakeLimitedResource {
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

