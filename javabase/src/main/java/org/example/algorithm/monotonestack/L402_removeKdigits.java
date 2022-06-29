package org.example.algorithm.monotonestack;

import java.util.LinkedList;

public class L402_removeKdigits {


    public static void main(String[] args) {
        System.out.println(
                new L402_removeKdigits()
                        .removeKdigits("10200", 1)
        );
    }

    public String removeKdigits(String num, int k) {
        if (num == null) {
            return null;
        }

        if (num.length() == k) {
            return "0";
        }

        // 用LinkedList代替Stack
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < num.length(); i++) {
            int curNum = Integer.parseInt(num.charAt(i) + "");
            // 单调递增栈
            while (!stack.isEmpty()
                    && stack.getLast() > curNum
                    && k > 0
            ) {
                stack.removeLast();
                k--;
            }

            if (curNum != 0 || !stack.isEmpty()) {
                stack.addLast(curNum);
            }
        }

        StringBuilder result = new StringBuilder();
        for (Integer integer : stack) {
            result.append(integer);
        }

        String resultString = result.substring(0, stack.size() - k < 1 ? 0 : stack.size() - k);
        return resultString.length() == 0 ? "0" : resultString;
    }

}
