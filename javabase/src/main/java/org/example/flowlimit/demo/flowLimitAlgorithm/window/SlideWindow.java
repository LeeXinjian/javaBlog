package org.example.flowlimit.demo.flowLimitAlgorithm.window;


import java.util.LinkedList;

public class SlideWindow extends Window {

    private final long liteWindowTimeInterval;
    private final int splitCount;
    private LinkedList<FixCountWindow> windows;

    /**
     * @param windowLeft   windowLeft
     * @param timeInterval 统计的时间周期
     * @param splitCount   分为几个滑动窗口
     */
    public SlideWindow(long windowLeft, long timeInterval, int splitCount) {
        super(windowLeft, timeInterval);
        this.splitCount = splitCount;
        this.liteWindowTimeInterval = timeInterval / splitCount;
        this.windows = new LinkedList<>();
        initWindows(System.currentTimeMillis());
    }

    @Override
    protected void doResetWindow() {
        long currentTimeMillis = System.currentTimeMillis();
        // 计算偏移多少个格子
        long count = (currentTimeMillis - super.getWindowLeft()) / liteWindowTimeInterval;
        // 需要移动多少个格子
        long moveConut = count - splitCount + 1;
        // 滑动数量超过配置大小
        if (moveConut >= splitCount) {
            // 重置
            super.setWindowLeft(currentTimeMillis);
            initWindows(currentTimeMillis);
        } else {
            // 重置固定个数的window
            resetWindows(moveConut);
        }

        super.setWindowLeft(windows.getFirst().getWindowLeft());
    }

    @Override
    protected int doGetSuccessCount() {
        return new Long(
                windows.stream()
                        .map(FixCountWindow::doGetSuccessCount)
                        .reduce(0, Integer::sum)
        ).intValue();
    }

    @Override
    protected void doAddCount() {
        windows.get(
                new Long(
                        (System.currentTimeMillis() - super.getWindowLeft()) / liteWindowTimeInterval)
                        .intValue()
        ).doAddCount();
    }

    public int getLiteWindowCount() {
        return windows.get(
                new Long(
                        (System.currentTimeMillis() - super.getWindowLeft()) / liteWindowTimeInterval)
                        .intValue()
        ).getSuccessCount();
    }

    private void resetWindows(long moveCount) {
        for (int i = 0; i < moveCount; i++) {
            windows.removeFirst();
        }

        int remainSize = windows.size();
        long windowLeft = windows.getFirst().getWindowLeft();

        for (int i = 0; i < moveCount; i++) {
            windows.addLast(
                    new FixCountWindow(
                            windowLeft + (i + remainSize) * liteWindowTimeInterval,
                            liteWindowTimeInterval));
        }
    }

    private void initWindows(long currentTimeMillis) {
        windows = new LinkedList<>();
        windows.add(new FixCountWindow(currentTimeMillis, liteWindowTimeInterval));
        for (int i = 1; i < splitCount; i++) {
            windows.add(
                    new FixCountWindow(
                            currentTimeMillis + i * liteWindowTimeInterval,
                            liteWindowTimeInterval));
        }
    }
}
