package org.example.algorithm.backTracking.code;

import java.util.ArrayList;
import java.util.List;

public class L22_GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(result, n, n, "");
        return result;
    }

    public void dfs(List<String> result, int left, int right, String curResult) {
        if (left == 0 && right == 0) {
            result.add(curResult);
            return;
        }

        if (left == right) {
            dfs(result, left - 1, right, curResult + "(");
        } else if (left < right) {
            if (left != 0) {
                dfs(result, left - 1, right, curResult + "(");
            }
            dfs(result, left, right - 1, curResult + ")");
        }

    }
}
