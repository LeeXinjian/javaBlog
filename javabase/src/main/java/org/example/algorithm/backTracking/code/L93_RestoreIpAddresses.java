package org.example.algorithm.backTracking.code;

import java.util.ArrayList;
import java.util.List;

public class L93_RestoreIpAddresses {


    public static void main(String[] args) {
        new L93_RestoreIpAddresses()
                .restoreIpAddresses("0000")
                .forEach(System.out::println);
    }

    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();
        if (s == null || s.length() < 4) {
            return result;
        }

        backTracking("", 0, s, "", 0, result);
        return result;
    }

    public void backTracking(String curString, int curIndex, String digits, String groupString, int gourpTimes, List<String> result) {
        int groupLength = groupString.length();
        // 不是考虑范围内
        if (gourpTimes == 4
                || groupLength == 4
                || (
                groupString.length() > 0
                        && Integer.parseInt(groupString) > 255)
        ) {
            return;
        }
        // 遍历到最后一个字符，加结果集返回
        if (curIndex == digits.length() && gourpTimes == 3) {
            result.add(curString);
            return;
        }
        // 如果组长不是0 我们可以开启新组
        if (groupLength != 0) {
            // 开启新组
            backTracking(
                    curString + "." + digits.charAt(curIndex),
                    curIndex + 1,
                    digits,
                    "" + digits.charAt(curIndex),
                    gourpTimes + 1,
                    result);
            // 上一个元素是0,只能开启新组,不能拼接其他元素
            if (curIndex - 1 >= 0 && digits.charAt(curIndex - 1) == '0') {
                return;
            }
        }

        // 拼接其他元素
        backTracking(
                curString + digits.charAt(curIndex),
                curIndex + 1,
                digits,
                groupString + digits.charAt(curIndex),
                gourpTimes,
                result);

    }
}


