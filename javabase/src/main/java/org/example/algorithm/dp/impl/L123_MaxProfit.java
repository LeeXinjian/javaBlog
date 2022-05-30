package org.example.algorithm.dp.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class L123_MaxProfit {

    public static void main(String[] args) {
        System.out.println(
                maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})
        );
    }

    /**
     * 错误
     */
    public static int maxProfit(int[] prices) {
        if (prices.length <= 0) {
            return 0;
        }

        // 当前利润
        int curProfix = 0;
        // profixs利润集合
        List<Integer> profixs = new ArrayList<>();

        for (int i = 1; i < prices.length; i++) {

            // 当天利润
            int dayProfix = prices[i] - prices[i - 1];
            // 当前赚的钱
            int newCurProfix = curProfix + dayProfix;

            // 当前利润<0的,累计金额重置,切断连续
            if (newCurProfix < 0) {
                profixs.add(curProfix);
                curProfix = 0;
                continue;
            }

            curProfix = newCurProfix;

        }

        // 循环的最后一次
        if (curProfix > 0) {
            profixs.add(curProfix);
        }

        profixs = profixs.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (profixs.size() >= 2) {
            return profixs.get(0) + profixs.get(1);
        }

        if (profixs.size() == 1) {
            return profixs.get(0);
        }

        return 0;
    }

}
