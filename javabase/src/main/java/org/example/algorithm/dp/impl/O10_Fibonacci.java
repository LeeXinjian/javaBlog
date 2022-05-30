package org.example.algorithm.dp.impl;

import com.google.common.collect.Maps;
import org.example.algorithm.dp.DynamicProgram;

import java.util.HashMap;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 */
public class O10_Fibonacci implements DynamicProgram {

    public static long fibDownToTop(int n) {
        long fn2 = 0;
        long fn1 = 1;

        if (n == 0) {
            return fn2;
        }

        if (n == 1) {
            return fn1;
        }

        long fn = 0;
        for (int i = 2; i <= n; i++) {
            // 先算出当前的
            fn = fn1 + fn2;
            // 更新fn2
            fn2 = fn1;
            // 更新fn1
            fn1 = fn;
        }

        return fn;
    }

    public static long fibTopToDown(int n, HashMap<Integer, Long> noteBook) {
        if (n == 0) {
            noteBook.put(0, 0L);
            return 0;
        }

        if (n == 1) {
            noteBook.put(1, 1L);
            return 1;
        }

        if (noteBook.get(n - 1) == null) {
            noteBook.put(n - 1, fibTopToDown(n - 1, noteBook));
        }

        if (noteBook.get(n - 2) == null) {
            noteBook.put(n - 2, fibTopToDown(n - 2, noteBook));
        }

        return noteBook.get(n - 1) + noteBook.get(n - 2);
    }

    @Override
    public void buttomUp() {
        System.out.println(fibDownToTop(45));
    }

    @Override
    public void topDown() {
        System.out.println(fibTopToDown(45, Maps.newHashMap()));
    }
}
