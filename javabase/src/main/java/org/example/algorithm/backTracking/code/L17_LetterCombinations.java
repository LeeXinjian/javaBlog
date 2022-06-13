package org.example.algorithm.backTracking.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L17_LetterCombinations {

    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public static void main(String[] args) {
        new L17_LetterCombinations().letterCombinations("23").forEach(System.out::println);
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        backTracking("", 0, digits, result);
        return result;
    }

    /**
     * @param curString 当前结果
     * @param curIndex  当前处理的坐标
     * @param digits    原始字符串
     * @param result    结果集合
     */
    public void backTracking(String curString, int curIndex, String digits, List<String> result) {
        if (curIndex == digits.length()) {
            result.add(curString);
            return;
        }

        Character curChar = digits.charAt(curIndex);
        String curProbablyString = phoneMap.get(curChar);

        for (int i = 0; i < curProbablyString.length(); i++) {
            backTracking(
                    curString + curProbablyString.charAt(i),
                    curIndex + 1,
                    digits,
                    result);
        }
    }
}
