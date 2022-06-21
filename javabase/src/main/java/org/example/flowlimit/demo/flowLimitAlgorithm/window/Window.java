package org.example.flowlimit.demo.flowLimitAlgorithm.window;

import lombok.Data;

@Data
public abstract class Window {
    /**
     * 当前窗口统计的时间范围左边界
     */
    private long windowLeft;
    /**
     * 时间间隔(ms)
     */
    private long timeInterval;

    Window(long windowLeft, long timeInterval) {
        this.windowLeft = windowLeft;
        this.timeInterval = timeInterval;
    }

    /**
     * 重置窗口边界
     */
    public void resetWindow() {
        doResetWindow();
    }

    /**
     * 具体重置窗口边界的方法
     */
    protected abstract void doResetWindow();

    /**
     * 获取当前窗口请求数量
     */
    public int getSuccessCount() {
        return doGetSuccessCount();
    }

    /**
     * 具体重置窗口边界的方法
     */
    protected abstract int doGetSuccessCount();

    /**
     * 获取当前窗口请求数量
     */
    public void addCount() {
        doAddCount();
    }

    /**
     * 具体重置窗口边界的方法
     */
    protected abstract void doAddCount();
}
