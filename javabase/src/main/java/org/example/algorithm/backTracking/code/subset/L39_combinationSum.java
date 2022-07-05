package org.example.algorithm.backTracking.code.subset;

import java.util.ArrayList;
import java.util.List;

public class L39_combinationSum {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        System.out.println(
                new L39_combinationSum()
                        .combinationSum(nums, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    public void dfs(int[] candidates, int target, int curIndex, List<Integer> curResult, List<List<Integer>> result) {
        // 已经满了就返回
        if (target == 0) {
            result.add(new ArrayList<>(curResult));
            return;
        }
        // 终止条件
        if (target < 0) {
            return;
        }

        for (int i = curIndex; i < candidates.length; i++) {
            // 先处理选
            curResult.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, curResult, result);
            // 回溯
            curResult.remove(curResult.size() - 1);
        }
    }
}
