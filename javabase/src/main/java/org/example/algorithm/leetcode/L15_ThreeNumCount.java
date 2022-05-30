package org.example.algorithm.leetcode;

import org.example.algorithm.sort.impl.QuickSortDemo;

import java.util.ArrayList;
import java.util.List;

public class L15_ThreeNumCount {


    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        // 结果集
        ArrayList<List<Integer>> result = new ArrayList<>();

        int[] sortedNums = new QuickSortDemo().doSort(nums);

        // 第一个人找另外两个小伙伴的故事
        for (int oneGay = 0; oneGay < nums.length; oneGay++) {

            // 元素重复了就忽略
            if (oneGay > 0 && sortedNums[oneGay] == sortedNums[oneGay - 1]) {
                continue;
            }

            // 左指针
            int leftOne = oneGay + 1;
            // 右指针
            int rightOne = sortedNums.length - 1;

            while (leftOne < rightOne) {
                // 找到结果
                if (sortedNums[oneGay] + sortedNums[leftOne] + sortedNums[rightOne] == 0) {
                    //放入结果
                    setResult(result, sortedNums[oneGay], sortedNums[leftOne], sortedNums[rightOne]);
                    // 左指针移动
                    do {
                        leftOne++;
                    } while (
                            leftOne < rightOne
                                    && sortedNums[leftOne] == sortedNums[leftOne - 1]);

                    // 左指针移动
                    do {
                        rightOne--;
                    } while (
                            leftOne < rightOne
                                    && sortedNums[rightOne] == sortedNums[rightOne + 1]);

                }else if (sortedNums[oneGay] + sortedNums[leftOne] + sortedNums[rightOne] < 0){
                    // 做指针移动
                    do {
                        leftOne++;
                    } while (
                            leftOne < rightOne
                                    && sortedNums[leftOne] == sortedNums[leftOne - 1]);
                }else{
                    // 右指针移动
                    do {
                        rightOne--;
                    } while (
                            leftOne < rightOne
                                    && sortedNums[rightOne] == sortedNums[rightOne + 1]);

                }


            }
        }

        return result;
    }

    private void setResult(ArrayList<List<Integer>> result, int curOne, int leftOne, int rightOne) {
        ArrayList<Integer> curResult = new ArrayList<>();
        curResult.add(curOne);
        curResult.add(leftOne);
        curResult.add(rightOne);
        result.add(curResult);
    }
}
