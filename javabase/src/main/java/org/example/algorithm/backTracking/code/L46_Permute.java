package org.example.algorithm.backTracking.code;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 */
public class L46_Permute {

    public static void main(String[] args) {
        new L46_Permute()
                .permute(new int[]{1, 2, 3})
                .forEach(
                        entity -> {
                            entity.forEach(System.out::print);
                            System.out.println();
                        });
    }

    public List<List<Integer>> permute(int[] nums) {
        return dfs(0, nums, new ArrayList<>());
    }

    public List<List<Integer>> dfs(int index, int[] nums, List<List<Integer>> result) {
        // 位于最后一个元素
        if (index == nums.length - 1) {
            // 把当前元素加入到结果集合当中
            return addToResult(nums, result);
        }

        // 循环
        for (int i = index; i < nums.length; i++) {
            // 把index 和 i位置上的元素交换
            swap(nums, i, index);
            // 递归
            dfs(index + 1, nums, result);
            // 换回来
            swap(nums, i, index);
        }

        return result;
    }

    private void swap(int[] nums, int i, int index) {
        int tmp = nums[i];
        nums[i] = nums[index];
        nums[index] = tmp;
    }


    /**
     * 把当前组合加入到结果集中
     */
    private List<List<Integer>> addToResult(int[] nums, List<List<Integer>> result) {
        List<Integer> curResult = new ArrayList<>();
        for (int num : nums) {
            curResult.add(num);
        }

        result.add(curResult);
        return result;
    }
}
