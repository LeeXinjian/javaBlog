package org.example.algorithm.leetcode;

public class L415_addStrings {

    public static void main(String[] args) {
        System.out.println(
                new L415_addStrings()
                        .addStrings("0", "9"));
    }

    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        if (num1.length() == 0 || num2.length() == 0) {
            return null;
        }

        // 短数字
        String shortNum = num1.length() >= num2.length() ? num2 : num1;
        // 长数字
        String longNum = num1.length() >= num2.length() ? num1 : num2;

        int carry = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= longNum.length(); i++) {
            int shortIndex = shortNum.length() - i;
            int longIndex = longNum.length() - i;

            int aNum = 0;
            if (shortIndex >= 0) {
                aNum = Integer.parseInt(shortNum.charAt(shortIndex) + "");
            }
            int bNum = Integer.parseInt(longNum.charAt(longIndex) + "");

            // 当前轮结果
            int curResult = aNum + bNum + carry;
            carry = curResult / 10;
            curResult = curResult % 10;

            result.insert(0,curResult);
        }

        if (carry>0) {
            result.insert(0,carry);
        }
        return result.toString();
    }

}
