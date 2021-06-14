package org.example.algorithm.dynamicProgram;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 */
public class O10_Fibonacci {

    public static void main(String[] args) {
        int n = 5;
        int i1value = 0;
        int i2value = 0;

        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                continue;
            }

            if (i == 1) {
                i1value = 1;
                i2value = 0;
                continue;
            }

            int tmp = i2value;
            i2value = i1value;
            i1value = tmp + i2value;
        }

        System.out.println(i1value);
    }
}
