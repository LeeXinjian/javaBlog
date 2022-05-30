package org.example.algorithm.dp.impl;

import org.example.algorithm.dp.DynamicProgram;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长的不重复子串
 */
public class L3_LongestNoRepeatSubstring implements DynamicProgram {

    public static void main(String[] args) {
        new L3_LongestNoRepeatSubstring().buttomUp();
    }

    /**
     * 动态规划 O(N)
     */
    @Override
    public void buttomUp() {
        System.out.println(doButtonUp("abcabcbb"));
    }


    /**
     * 滑动窗口的解决方案
     * Time : O(N)
     */
    public int slidingWindow(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 记录下出现的位置
        Map<Character, Integer> indexCache = new HashMap<>();

        int result = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char curChar = s.charAt(right);
            Integer lastIndex = indexCache.get(curChar);

            // 如果当前已存在
            if (lastIndex != null) {
                // 指针移位
                left = Math.max(lastIndex + 1, left);
            }

            // 放入最新的出现位置
            indexCache.put(curChar, right);
            result = Math.max(result, right - left + 1);
        }

        return result;
    }


    private int doButtonUp(String value) {
        if (value == null || value.length() == 0) {
            return 0;
        }

        // 上一轮动态规划结果
        String dpIResult = String.valueOf(value.charAt(0));
        // 返回结果
        String result = String.valueOf(value.charAt(0));
        for (int index = 1; index < value.length(); index++) {
            String curValue = String.valueOf(value.charAt(index));
            // 如果包含
            if (dpIResult.contains(curValue)) {
                // 拿到当前元素出现的位置
                int i = dpIResult.lastIndexOf(curValue);
                // 当前结果
                String curResult = dpIResult.substring(i + 1) + curValue;
                if (curResult.length() > result.length()) {
                    result = curResult;
                }
                // 位移动态规划的最好结果
                dpIResult = curResult;
            } else {
                String curResult = dpIResult + curValue;
                if (curResult.length() > result.length()) {
                    result = curResult;
                }
                dpIResult = curResult;
            }
        }

        return result.length();
    }

    @Override
    public void topDown() {

    }

}
