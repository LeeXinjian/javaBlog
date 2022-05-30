package org.example.algorithm.dp.impl;

import javafx.util.Pair;

/**
 * 最长回文子串
 */
public class L5_MaxPalindromeSubString {


    public static void main(String[] args) {
        System.out.println(
                new L5_MaxPalindromeSubString().
                        longestPalindrome("bbbb")
        );
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }

        String result = null;

        for (int i = 0; i < s.length(); i++) {
            Integer leftIndex = i;
            Integer rightIndex = i;
            // 考虑中间结点是奇数和偶数两种情况
            // 偶数中间结点，左偏移
            if (leftMove(s, i, leftIndex)) {
                // 左偏移一位，最为中心，计算结果
                --leftIndex;
                // 向左获取结果
                result = getString(s, result, leftIndex, rightIndex);
            }

            if (rightMove(s, i, rightIndex)) {
                // 偶数结点右侧偏移
                ++rightIndex;
                // 向右获取结果
                result = getString(s, result, leftIndex, rightIndex);
            }

            // 中心获取结果
            result = getString(s, result, leftIndex, rightIndex);
        }
        return result;
    }

    private String getString(String s, String result, Integer leftIndex, Integer rightIndex) {
        Pair<Integer, Integer> pair = doCheckPalindrome(s, --leftIndex, ++rightIndex);
        String curResult = s.substring(pair.getKey(), pair.getValue() + 1);
        // 结果位移
        if (result == null || curResult.length() > result.length()) {
            return curResult;
        }
        return result;
    }

    private Pair<Integer, Integer> doCheckPalindrome(String s, Integer leftIndex, Integer rightIndex) {
        // 数组越界了，或者不满足结果了，返回上一轮的结果
        if (leftIndex < 0
                || rightIndex >= s.length()
                || s.charAt(leftIndex) != s.charAt(rightIndex)) {
            ++leftIndex;
            --rightIndex;
            return new Pair<>(leftIndex, rightIndex);
        }

        return doCheckPalindrome(s, --leftIndex, ++rightIndex);
    }

    private boolean rightMove(String s, int i, int rightIndex) {
        return rightIndex + 1 < s.length() && s.charAt(rightIndex + 1) == s.charAt(i);
    }

    private boolean leftMove(String s, int i, int leftIndex) {
        return leftIndex - 1 >= 0 && s.charAt(leftIndex - 1) == s.charAt(i);
    }

}
