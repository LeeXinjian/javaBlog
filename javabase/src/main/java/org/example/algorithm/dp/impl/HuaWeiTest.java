package org.example.algorithm.dp.impl;

import java.util.HashMap;

/**
 * 假设一个楼梯有 N 阶台阶，每人每次最多可以跨 M 阶，求总共的爬楼梯方案数。例如楼梯总共有3个台阶，每人每次最多跨2个台阶，也就是说人每次可以走1个，也可以走2个，但最多不会超过2个，那么楼梯总共有这么几种走法：111，12，21
 * 输入描述:
 * n（台阶总数）, m（一次最多可以跨的阶数）,n、m必须大于0，并且m小于等于n；
 * 为了程序运行时间考虑，要求n小于等于30
 */
public class HuaWeiTest {

    public static void main(String[] args) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        int n = 5;
        int m = 3;
        System.out.println(
                enter(n, m, cache)
        );
    }

    public static Integer enter(int n, int m, HashMap<Integer, Integer> cache) {
        if (m <= 0 || n <= 0) {
            return -1;
        }
        if (n > 30 || m > n) {
            return -1;
        }
        return dp(m, n, cache);
    }

    public static Integer dp(int m, int curIndex, HashMap<Integer, Integer> cache) {
        if (curIndex == 0) {
            cache.put(curIndex, 0);
            return 0;
        }
        if (curIndex == 1) {
            cache.put(curIndex, 1);
            return 1;
        }
        if (cache.get(curIndex) == null) {
            // 在m步以内,当前走法是dp(0)+dp(1）+dp(2)+...dp(curIndex) + 1
            if (curIndex <= m) {
                int curValue = 1;
                for (int i = 0; i < curIndex; i++) {
                    curValue = curValue + dp(m, i, cache);
                }
                cache.put(curIndex, curValue);
            } else {
                int curVaule = 0;
                for (int i = curIndex - m; i < curIndex; i++) {
                    curVaule += dp(m, i, cache);
                }
                cache.put(curIndex, curVaule);
            }
        }
        return cache.get(curIndex);
    }


}
