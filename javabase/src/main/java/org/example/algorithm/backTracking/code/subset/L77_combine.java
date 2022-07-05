package org.example.algorithm.backTracking.code.subset;

import java.util.ArrayList;
import java.util.List;

public class L77_combine {

    public static void main(String[] args) {
        System.out.println(
                new L77_combine().combine(4,2)
        );
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    public void dfs(int n, int k, int curVal, List<Integer> curResult, List<List<Integer>> result) {
        // 已经满了就返回
        if (curResult.size() == k) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        for (int i = curVal; i <= n; i++) {
            // 先处理选
            curResult.add(i);
            dfs(n, k, i + 1, curResult, result);
            // 回溯
            curResult.remove(curResult.size() - 1);
        }
    }

}
