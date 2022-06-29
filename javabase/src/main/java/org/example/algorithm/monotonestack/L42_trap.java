package org.example.algorithm.monotonestack;

import java.util.Stack;

public class L42_trap {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        L42_trap trap = new L42_trap();
        System.out.println(trap.trapStack(height));
    }

    /**
     * 暴力破解法（按列求）
     * 时间复杂度：o(n*n)
     * 空间复杂度：o(1)
     */
    public int trapForce(int[] height) {
        if (height.length == 0) {
            return 0;
        }

        int ans = 0;
        // 遍历数组
        for (int i = 0; i < height.length; i++) {
            //左边最大的
            int leftMax = getMax(0, i, height);
            //右边最大的
            int rightMax = getMax(i, height.length - 1, height);
            // 当前列结果
            int high = Math.min(leftMax, rightMax) - height[i];
            // 结果累加
            ans += high;
        }

        return ans;

    }

    private int getMax(int left, int right, int[] height) {
        int max = 0;
        while (left <= right && left < height.length) {
            max = Math.max(height[left], max);
            left++;
        }
        return max;
    }

    /**
     * DP算法->暴力破解法的优化
     * 暴力破解法中，每次都对左右最大值进行遍历。使用dp优化，避免循环多次请求
     */
    public int trapDp(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        // 左DP
        int[] dpLeft = new int[height.length];
        // 右dp
        int[] dpRight = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                dpLeft[i] = height[i];
                continue;
            }
            dpLeft[i] = Math.max(dpLeft[i - 1], height[i]);
        }

        for (int j = height.length - 1; j >= 0; j--) {
            if (j == height.length - 1) {
                dpRight[j] = height[j];
                continue;
            }
            dpRight[j] = Math.max(dpRight[j + 1], height[j]);
        }

        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            //左边最大的
            int leftMax = dpLeft[i];
            //右边最大的
            int rightMax = dpRight[i];
            // 当前列结果
            int high = Math.min(leftMax, rightMax) - height[i];
            // 结果累加
            ans += high;
        }

        return ans;
    }

    public int trapStack(int[] height) {
        if (height.length == 0) {
            return 0;
        }

        // 单调张
        Stack<Integer> stack = new Stack<>();
        // 结果值
        int ans = 0;
        // 当前元素下标
        int curIndex = 0;
        // 遍历数组
        while (curIndex < height.length) {
            // 递增单调栈
            while (!stack.isEmpty()
                    && height[stack.peek()] < height[curIndex]
            ) {
                // 计算当前空隙的雨水需要三个部分
                // 左挡板，空隙，右挡板
                // pop出的元素可作为空隙
                Integer pop = stack.pop();
                // 说明没有左边界,
                if (stack.isEmpty()) {
                    break;
                }
                // 左挡板
                Integer left = stack.peek();
                // 宽度就是左右挡板的长度
                int width = curIndex - left - 1;
                // 宽度为左右挡板 取小 - 当前空隙高度
                int hight = Math.min(height[curIndex], height[left]) - height[pop];

                if (width > 0 && hight > 0) {
                    ans += (width * hight);
                }

            }
            // 入栈当前元素
            stack.push(curIndex);
            curIndex++;
        }

        return ans;
    }

    public int twoPoint(int[] height) {
        return 0;
    }

}
