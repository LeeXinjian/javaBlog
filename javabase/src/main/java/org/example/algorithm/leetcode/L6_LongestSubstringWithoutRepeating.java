package org.example.algorithm.leetcode;

import java.util.HashMap;

public class L6_LongestSubstringWithoutRepeating {


    public static void main(String[] args) {
        String s = "jxdlnaaij";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }

        int length = s.length();
        if (length <= 1) {
            return 1;
        }

        int ans = 0;
        // key : 对应的字符
        // value : 上次出现的位置+1
        HashMap<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < s.length(); end++) {
            // 当前位置
            char charAt = s.charAt(end);
            // 上次位置
            Integer prevPos = map.get(charAt);

            // 如果重复了
            if (prevPos != null) {
                start = Math.max(prevPos, start);
            }

            ans = Math.max(end - start + 1, ans);
            map.put(charAt, end + 1);

        }
        return ans;
    }
}
