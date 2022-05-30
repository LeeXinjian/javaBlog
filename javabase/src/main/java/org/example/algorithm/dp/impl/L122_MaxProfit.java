package org.example.algorithm.dp.impl;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class L122_MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices.length <= 0 ){
            return 0;
        }

        int maxProfix = 0 ;
        for (int i = 1; i < prices.length; i++) {
            // 当天利润
            int dayProfix = prices[i] - prices[i-1];
            if (dayProfix >0) {
                maxProfix += dayProfix;
            }
        }

        return maxProfix;
    }
}
