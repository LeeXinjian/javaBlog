package org.example.algorithm.leetcode;

public class L283_removeZero {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }

        if (nums.length <= 1) {
            return;
        }

        int next = 1;
        for (int i = 0; i < nums.length && next < nums.length; i++, next++) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[next];
                nums[next] = tmp;
            }
        }
    }

}
