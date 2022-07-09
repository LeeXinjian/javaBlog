package org.example.algorithm.backTracking.code;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 删除最小数目的无效括号，使得输入字符串有效，返回所有可能的结果。
 * 输入:
 * "()())()"
 * 输出:
 * ["(())()","()()()"]
 */
public class HuaWeiDemo {

    public static void main(String[] args) {
        new HuaWeiDemo()
                .delete("()())()")
                .forEach(System.out::println);
    }

    public HashSet<String> delete(String str) {
        //计算左右括号分别的个数
        Pair<Integer, Integer> leftAndRightNums = getLeftAndRightNums(str);
        // check
        if (leftAndRightNums == null) {
            return new HashSet<>();
        }

        Integer leftNum = leftAndRightNums.getKey();
        Integer rightNum = leftAndRightNums.getValue();

        // 只返回有效的
        return new HashSet<>(
                // step2. 只返回有效括号结果
                doCheck(
                        // step1. 得到删除对应个数的括号后的所有结果
                        doDelete(
                                str
                                // step0.1 计算需要删除的括号数量
                                , Math.abs(leftNum - rightNum)
                                // step0.2 需要删除的符号
                                , leftNum > rightNum ? '(' : ')'
                        )
                )
        );
    }

    private Pair<Integer, Integer> getLeftAndRightNums(String str) {
        int leftNum = 0;
        int rightNum = 0;

        for (int i = 0; i < str.length(); i++) {
            if ('(' == str.charAt(i)) {
                leftNum++;
                continue;
            }
            if (')' == str.charAt(i)) {
                rightNum++;
            }
        }

        if (leftNum == 0 || rightNum == 0) {
            return null;
        }

        return new Pair<>(leftNum, rightNum);
    }

    private List<String> doCheck(List<String> result) {
        return result
                .stream()
                .filter(this::checkValid)
                .collect(Collectors.toList());
    }

    private boolean checkValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            // 遇到左括号入栈
            if ('(' == s.charAt(i)) {
                stack.addLast(s.charAt(i));
            } else {
                //遇到右括号
                if (stack.isEmpty()) {
                    return false;
                } else {
                    // 出栈
                    stack.removeLast();
                }
            }
        }

        return stack.isEmpty();
    }

    private List<String> doDelete(String str, int needDeleteNum, char needDeleteChar) {
        List<String> result = new ArrayList<>();
        doDeleteBack(str, new StringBuilder(str), 0, needDeleteNum, needDeleteChar, 0, result);
        return result;
    }

    private void doDeleteBack(String str, StringBuilder curResult, int curIndex, int needDeleteNum, char needDeleteChar, int deleteNum, List<String> result) {
        // 相等加结果集
        if (needDeleteNum == deleteNum) {
            String s = curResult.toString();
            result.add(s);
            return;
        }
        // 删除元素>需要删除 则 折枝
        if (deleteNum > needDeleteNum) {
            return;
        }

        for (int i = curIndex; i < str.length(); i++) {
            // 当前元素非需删除 继续
            if (str.charAt(i) != needDeleteChar) {
                continue;
            }
            char curChar = str.charAt(i);
            // 删除当前元素
            StringBuilder sb = curResult.deleteCharAt(i);
            // 做递归删除
            doDeleteBack(str, sb, curIndex + 1, needDeleteNum, needDeleteChar, deleteNum + 1, result);
            // 回溯
            curResult.insert(i, curChar);
        }
    }

}
