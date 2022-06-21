package org.example;

public class Test {

    public static void main(String[] args) {

    }

    public String multi(String num1, String nums2) {
        int pos = 1;
        int result = 0;

        for (int i = nums2.length() - 1; i >= 0; i++) {
            // 当前值
            int curValue = Integer.parseInt(nums2.charAt(i) + "") * pos;
            // 计算结果
            result = result + (curValue * Integer.parseInt(num1));
            // 位移
            pos *= 10;
        }

        return result + "";
    }

}
