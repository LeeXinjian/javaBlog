package org.example.flowlimit.demo.flowLimitAlgorithm.window;

import java.util.concurrent.atomic.AtomicInteger;

public class FixCountWindow extends Window {

    /**
     * 成功次数
     */
    private AtomicInteger successCount = new AtomicInteger(0);

    public FixCountWindow(long timeInterval) {
        super(System.currentTimeMillis(), timeInterval);
    }

    public FixCountWindow(long leftTime, long timeInterval) {
        super(leftTime, timeInterval);
    }

    @Override
    protected void doResetWindow() {
        super.setWindowLeft(System.currentTimeMillis());
        successCount.set(0);
    }

    @Override
    protected int doGetSuccessCount() {
        return successCount.get();
    }

    @Override
    protected void doAddCount() {
        successCount.incrementAndGet();
    }
}
