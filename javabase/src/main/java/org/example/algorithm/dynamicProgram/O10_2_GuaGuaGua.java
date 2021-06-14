package org.example.algorithm.dynamicProgram;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class O10_2_GuaGuaGua {


    public static void main(String[] args) {
        int total = 7;

        int n1 = 2;
        int n2 = 1;

        for (int i = 3; i <= total ; i++) {
            int tmp = n2;
            n2 = n1;
            n1 = n2 + tmp;
        }

        System.out.println(n1);
    }

}
