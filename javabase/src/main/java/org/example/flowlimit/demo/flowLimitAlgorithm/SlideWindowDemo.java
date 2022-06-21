package org.example.flowlimit.demo.flowLimitAlgorithm;

import org.example.flowlimit.demo.flowLimitAlgorithm.condition.LimitCondition;
import org.example.flowlimit.demo.flowLimitAlgorithm.condition.SlideCountLimitCondition;
import org.example.flowlimit.demo.flowLimitAlgorithm.window.SlideWindow;
import org.example.flowlimit.demo.flowLimitAlgorithm.window.Window;

/**
 * 固定窗口限流
 */
public class SlideWindowDemo {
    /**
     * 限流条件
     */
    private final LimitCondition condition;

    /**
     * 窗口实体
     */
    private Window window;

    public SlideWindowDemo(int limitCount, int timeInterval, int splitCount) {
        condition = new SlideCountLimitCondition(limitCount, splitCount);
        window = new SlideWindow(System.currentTimeMillis(), timeInterval, splitCount);
    }

    /**
     * 模拟访问
     */
    public void access() {

        // 不在时间窗口内
        if (!inTimeWindow()) {
            resetWindow();
        }

        // 不可通过限流
        if (!condition.isPass(window)) {
            throw new RuntimeException("不通过限流");
        }

    }

    private boolean inTimeWindow() {
        return System.currentTimeMillis() - window.getWindowLeft() < window.getTimeInterval();
    }

    private synchronized void resetWindow() {
        window.resetWindow();
    }
}

