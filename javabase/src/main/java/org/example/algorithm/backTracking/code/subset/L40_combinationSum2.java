package org.example.algorithm.backTracking.code.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L40_combinationSum2 {


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        candidates = Arrays.stream(candidates).sorted().toArray();
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
            // 业务判断 剪枝
            if (candidates[i] > target) {
                continue;
            }
//            //  做头 剪枝
//            if (curIndex == i
//                    && i >= 1
//                    && candidates[i] == candidates[i - 1]
//                    && curResult.size() == 0) {
//                continue;
//            }
//            // 不做头
//            if (i > curIndex && candidates[i] == candidates[i - 1]) {
//                continue;
//            }

            if (i > curIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }


            // 先处理选
            curResult.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, curResult, result);
            // 回溯
            curResult.remove(curResult.size() - 1);
        }
    }
}
