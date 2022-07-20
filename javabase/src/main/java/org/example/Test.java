package org.example;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new Test().sortColors(nums);
        System.out.println(nums);
    }

    public void sortColors(int[] nums) {
        ArrayList<Integer> smallerThan = new ArrayList<>();
        ArrayList<Integer> eqThan = new ArrayList<>();
        ArrayList<Integer> biggerThan = new ArrayList<>();

        for (int i : nums) {
            if (i == 0) {
                smallerThan.add(i);
            }
            if (i == 1) {
                eqThan.add(i);
            }
            if (i == 2) {
                biggerThan.add(i);
            }
        }

        smallerThan.addAll(eqThan);
        smallerThan.addAll(biggerThan);


        for (int i = 0; i < nums.length; i++) {
            nums[i] = smallerThan.get(i);
        }

    }
}
