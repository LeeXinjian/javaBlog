package org.example.algorithm.leetcode;

public class PriorityQueueTest {

    public static void main(String[] args, int[][] grid) {

    }


    public int numTrees(int n) {
        return numsOfTree(n, new int[n + 1]);
    }

    /**
     * 方法返回当前 n个元素做 根节点 所能构成的二叉树数量
     */
    public int numsOfTree(int n, int[] cache) {
        if (cache[n] != 0) {
            return cache[n];
        }

        if (n == 0 || n == 1) {
            cache[n] = n;
            return cache[n];
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            int left = numsOfTree(i - 1, cache);
            int right = numsOfTree(n - i, cache);
            count += left * right;
        }

        cache[n] = count;
        return cache[n];
    }
}