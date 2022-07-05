package org.example.algorithm.backTracking.code.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L90_subsetsWithDup {

    public static void main(String[] args) {
        int[] ints = {1, 2, 2};
        System.out.println(
                new L90_subsetsWithDup()
                        .subsetsWithDup(ints)
        );
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        nums = Arrays.stream(nums).sorted().toArray();
        dfs(0, nums, result, new ArrayList<>());
        return result;
    }

    public void dfs(int curIndex, int[] nums, List<List<Integer>> result, List<Integer> curResult) {
        // 不选
        result.add(new ArrayList<>(curResult));
        // 选, 则可以选之后的任意一个元素
        for (int i = curIndex; i < nums.length; i++) {
            // 折枝
            if (i > curIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            // 推进和回溯
            curResult.add(nums[i]);
            dfs(i + 1, nums, result, curResult);
            curResult.remove(curResult.size() - 1);
        }
    }
}
