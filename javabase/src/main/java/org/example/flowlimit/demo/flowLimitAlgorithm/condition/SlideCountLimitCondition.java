package org.example.flowlimit.demo.flowLimitAlgorithm.condition;

import org.example.flowlimit.demo.flowLimitAlgorithm.window.SlideWindow;
import org.example.flowlimit.demo.flowLimitAlgorithm.window.Window;

public class SlideCountLimitCondition extends LimitCondition {

    /**
     * 可访问的最大次数
     */
    private Integer maxCount;

    /**
     * 每个格子的最大访问次数
     */
    private int liteWindowMaxCount;

    public SlideCountLimitCondition(Integer maxCount, int splitCount) {
        this.maxCount = maxCount;
        this.liteWindowMaxCount = maxCount / splitCount;
    }

    @Override
    protected boolean doCheckCondition(Window window) {
        SlideWindow slideWindow = (SlideWindow) window;
        return window.getSuccessCount() < maxCount
                && slideWindow.getLiteWindowCount() < liteWindowMaxCount;
    }

}
