package org.example.algorithm.dp.impl;

import com.google.common.collect.Maps;
import org.example.algorithm.dp.DynamicProgram;

import java.util.HashMap;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class O10_2_GuaGuaGua implements DynamicProgram {

    @Override
    public void buttomUp() {
        System.out.println(guaGuaGuaDownToTop(10));
    }

    @Override
    public void topDown() {
        System.out.println(guaGuaGuaTopToDown(10, Maps.newHashMap()));
    }


    public int guaGuaGuaDownToTop(int n) {
        int n1 = 1;
        int n2 = 0;

        for (int i = 1; i <= n; i++) {
            int value = n1 + n2;
            n2 = n1;
            n1 = value;
        }

        return n1;
    }

    public int guaGuaGuaTopToDown(int n, HashMap<Integer, Integer> noteBock) {

        if (n == 1) {
            noteBock.put(1, 1);
            return noteBock.get(1);
        }

        if (n == 2) {
            noteBock.put(2, 2);
            return noteBock.get(2);
        }

        if (noteBock.get(n - 1) == null) {
            noteBock.put(n - 1, guaGuaGuaTopToDown(n - 1, noteBock));
        }

        if (noteBock.get(n - 2) == null) {
            noteBock.put(n - 2, guaGuaGuaTopToDown(n - 2, noteBock));
        }

        return noteBock.get(n - 1) + noteBock.get(n - 2);

    }
}
