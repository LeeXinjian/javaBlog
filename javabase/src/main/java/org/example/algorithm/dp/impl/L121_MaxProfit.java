package org.example.algorithm.dp.impl;

import org.example.algorithm.dp.DynamicProgram;

/**
 * 股票的最大收益
 */
public class L121_MaxProfit implements DynamicProgram {

    /**
     * 暴力破解法
     */
    public int maxProfitForce(int[] prices) {
        return 0;
    }

    /**
     * 一次遍历法
     */
    public int maxProfitOnceFor(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        // 当前遇见的最低点
        int min = prices[0];
        // 当前最大利润
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 当前这一天能获得的最大利润(当前天-当前天之前的最低点)
            int curProfix = prices[i] - min;
            // 跟之前算出来的最大利润取高
            maxProfit = Math.max(curProfix, maxProfit);
            // 位移当前天之间遇见的最低点。
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }


    @Override
    public void buttomUp() {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfitButtomUp(prices));
    }

    /**
     * 动态规划法 - 从底至顶
     * o(2N) -> o(N)
     */
    public int maxProfitButtomUp(int[] prices) {

        // 利润差集
        int[] sub = new int[prices.length];
        // 初始化第一天
        sub[0] = prices[0];

        for (int i = 1; i < prices.length; i++) {
            sub[i] = prices[i] - prices[i - 1];
        }

        //计算最大子串和
        int sum = Integer.MIN_VALUE;
        int prevDayDp = 0;

        for (int i = 1; i < sub.length; i++) {
            // 动态规划值
            int dpi = prevDayDp + sub[i];
            // 本轮最大值
            int curValue = Math.max(sub[i], dpi);
            // 结果最大值
            sum = Math.max(curValue, sum);
            // 缓存上轮动态规划结果
            prevDayDp = curValue;
        }

        return Math.max(sum, 0);
    }

    /**
     * 动态规划法 - 从底至顶（优化）
     * o(N)
     */
    public int maxProfitButtomUpBetter(int[] prices) {

        //计算最大子串和
        int sum = Integer.MIN_VALUE;
        int prevDayDp = 0;

        for (int i = 1; i < prices.length; i++) {
            int profixSub = prices[i] - prices[i - 1];
            // 动态规划值
            int dpi = prevDayDp + profixSub;
            // 本轮最大值
            int curValue = Math.max(profixSub, dpi);
            // 结果最大值
            sum = Math.max(curValue, sum);
            // 缓存上轮动态规划结果
            prevDayDp = curValue;
        }

        return Math.max(sum, 0);
    }

    /**
     * 动态规划法 - 从顶至底
     */
    @Override
    public void topDown() {

    }
}
