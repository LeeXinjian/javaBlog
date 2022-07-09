package org.example;

public class NewTest {

    public static void main(String[] args) {
//
//输入: gas = [2,3,4], cost = [3,4,3]
//输出: -1

//
//输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//输出: 3

        int[] gasA = {2, 3, 4};
        int[] costA = {3, 4, 3};

        NewTest newTest = new NewTest();
        System.out.println(
                newTest.checkReturn(gasA, costA));


        int[] gasB = {1, 2, 3, 4, 5};
        int[] costB = {3, 4, 5, 1, 2};
        System.out.println(
                newTest.checkReturn(gasB, costB));
    }

    public int checkReturn(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            int check = check(i, gas, cost);
            if (check != -1) {
                return check;
            }
        }
        return -1;
    }

    public int check(int start, int[] gas, int[] cost) {
        // 剩余的汽油数量
        int gasLeft = 0;
        boolean isFirstStart = true;

        for (int i = start; i < start + gas.length; i++) {
            int curGasIndex = i;
            if (curGasIndex >= gas.length) {
                curGasIndex = curGasIndex - gas.length;
            }

            int curCostIndex = i - 1;
            if (curCostIndex >= gas.length) {
                curCostIndex = curCostIndex - gas.length;
            }

            if (curCostIndex < 0) {
                curCostIndex = curCostIndex + gas.length;
            }

            // 油不够了返回-1
            if (gasLeft < 0) {
                return -1;
            }

            int curGas = gas[curGasIndex];
            int curCost = cost[curCostIndex];
            if (isFirstStart) {
                curCost = 0;
                isFirstStart = false;
            }

            gasLeft = gasLeft + curGas - curCost;
        }

        if (gasLeft > 0) {
            return start;
        }

        return -1;
    }


}
