package org.example.algorithm.monotonestack;

import java.util.LinkedList;

public class L84_largestRectangleArea {

    public static void main(String[] args) {
        int[] nums = {2,1,5,6,2,3};
        System.out.println(
                new L84_largestRectangleArea()
                        .largestRectangleAreaMonotoneStack(nums)
        );
    }

    /**
     * 单调栈解法
     */
    public int largestRectangleAreaMonotoneStack(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        int[] newInt = new int[heights.length + 1];
        System.arraycopy(heights, 0, newInt, 0, heights.length);
        newInt[newInt.length - 1] = 0;

        heights = newInt;

        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length; i++) {
            // 单调递增栈
            int curValue = heights[i];
            while (!stack.isEmpty() && heights[stack.getLast()] > curValue) {
                // 遇到单调递减,则把栈顶出栈作为当前元素
                // 则此时,栈顶元素为左边的最小值,heights[i]为右边的最小值
                Integer curIndex = stack.removeLast();

                int width = i;
                //  求得宽
                //  特殊处理为null
                if (!stack.isEmpty()) {
                    width = i - stack.getLast() - 1;
                }

                // 求得高
                int high = heights[curIndex];
                // 求结果
                ans = Math.max(ans, high * width);
            }


            stack.addLast(i);
        }
        return ans;
    }

    /**
     * 暴力解法
     */
    public int largestRectangleAreaForce(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            // 左右分别扩散
            // 如果左右比当前元素大，则可以当前元素为高计算面积
            // 所以要取到第一个比当前小的停下
            int firstLeftSmallThanIndex = getFirstLeftSmallThan(i, heights);
            int firstRightSamllThanIndex = getFirstRightBiggerThan(i, heights);

            int len = firstRightSamllThanIndex - firstLeftSmallThanIndex - 1;
            int high = heights[i];

            ans = Math.max(ans, len * high);
        }

        return ans;
    }

    private int getFirstLeftSmallThan(int end, int[] heights) {
        int begin = 0;
        int minIndex = end;
        int cur = heights[end];

        while (begin <= end) {
            if (heights[end] < cur) {
                minIndex = end;
                break;
            }
            end--;
        }

        if (begin > end) {
            return end;
        }
        return minIndex;
    }

    private int getFirstRightBiggerThan(int begin, int[] heights) {
        int end = heights.length - 1;
        int minIndex = begin;
        int cur = heights[begin];
        while (begin <= end) {
            if (cur > heights[begin]) {
                minIndex = begin;
                break;
            }
            begin++;
        }

        if (begin > end) {
            return begin;
        }
        return minIndex;
    }


}
