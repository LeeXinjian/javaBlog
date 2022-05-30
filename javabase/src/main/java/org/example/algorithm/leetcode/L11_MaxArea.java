package org.example.algorithm.leetcode;

public class L11_MaxArea {

    public static void main(String[] args) {
        System.out.println(
                new L11_MaxArea()
                        .maxArea(
                                new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] height) {
        int result = -1;
        int left = 0;
        int right = height.length - 1;

        // 双指针
        while (left < right) {
            // 是否移动左侧
            boolean moveLeft = height[left] < height[right];

            int high = Math.min(height[left], height[right]);
            int width = right - left;

            // 当前结果
            result = Math.max(high * width, result);

            // 位移
            int moveTimes = 1;
            int org = 0;
            // 移动左侧
            if (moveLeft) {
                // 当前左侧值
                org = height[left];
                while (left < right && height[++left] <= (moveTimes + org)) {
                    moveTimes++;
                }

            } else {
                // 移动右侧
                org = height[right];
                while (left < right && height[--right] <= (moveTimes + org)) {
                    moveTimes++;
                }
            }
        }

        return result;
    }

}
