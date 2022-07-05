package org.example.algorithm.backTracking.code.subset;

import java.util.ArrayList;
import java.util.List;

public class L78_subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(
                new L78_subsets()
                        .subsets(nums)
        );
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, nums, result, new ArrayList<>());
        return result;
    }

    private void dfs(int curIndex, int[] nums, List<List<Integer>> result, ArrayList<Integer> curResult) {
        // 不选当前元素
        result.add(new ArrayList<>(curResult));
        // 选择当前元素,从当前元素往下深度优先
        for (int i = curIndex; i < nums.length; i++) {
            // 选择当前元素
            curResult.add(nums[i]);
            // 对下一个元素进行选择
            dfs(i + 1, nums, result, curResult);
            // 回溯
            curResult.remove(curResult.size() - 1);
        }
    }
}
