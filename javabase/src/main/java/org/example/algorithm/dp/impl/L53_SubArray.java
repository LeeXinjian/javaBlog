package org.example.algorithm.dp.impl;

import org.example.algorithm.dp.DynamicProgram;
import org.example.algorithm.greedy.GreedyAlgorithm;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/submissions/
 */
public class L53_SubArray implements DynamicProgram, GreedyAlgorithm {

    public static void main(String[] args) {
        new L53_SubArray().buttomUp();
    }


    @Override
    public void buttomUp() {
        int[] params = {1, 23, 4 - 23, 123, 124, -21, -233};
        System.out.println(buttomUpKadane(params));
    }

    /**
     * Kadane算法
     */
    private int buttomUpKadane(int[] params) {
        if (params.length <= 0) {
            return 0;
        }

        int dpi1 = 0;
        int max = Integer.MIN_VALUE, curmax;
        for (int param : params) {
            curmax = Math.max(dpi1 + param, param);
            max = Math.max(curmax, max);
            dpi1 = curmax;
        }

        return max;
    }

    @Override
    public void topDown() {

    }


    /**
     * GreedyAlgorithm
     */
    @Override
    public void greedyAlgorithm() {
        int[] params = {1, 23, 4 - 23, 123, 124, -21, -233};
        System.out.println(greedyAlgorithm(params));
    }

    private int greedyAlgorithm(int[] params) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int param : params) {
            sum = sum < 0 ? param : (sum + param);
            max = Math.max(sum, max);
        }

        return max;
    }


}
