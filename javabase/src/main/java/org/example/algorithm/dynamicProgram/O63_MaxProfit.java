package org.example.algorithm.dynamicProgram;

public class O63_MaxProfit {

    public static void main(String[] args) {
        int[] trend = initTrendArr();
        int maxProfit = getMaxProfit(trend);
        System.out.println(maxProfit);
    }

    private static int getMaxProfit(int[] trend) {
        int maxValue = 0;
        int curMin = trend[0];

        for (int i : trend) {
            // 当前
            int curMax = i - curMin;
            maxValue = Math.max(curMax, maxValue);
            curMin = Math.min(i, curMin);
        }

        return maxValue;
    }

    private static int[] initTrendArr() {
        return new int[]{5, 15, 2, 10, 4, 15};
    }


}
