package org.example.flowlimit.demo.flowLimitAlgorithm.condition;

import org.example.flowlimit.demo.flowLimitAlgorithm.window.Window;

public class MaxCountLimitCondition extends LimitCondition {

    /**
     * 可访问的最大次数
     */
    private Integer maxCount;

    public MaxCountLimitCondition(Integer maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    protected boolean doCheckCondition(Window window) {
        return window.getSuccessCount() < maxCount;
    }
}
