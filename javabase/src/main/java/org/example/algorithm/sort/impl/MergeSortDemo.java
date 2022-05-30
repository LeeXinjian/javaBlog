package org.example.algorithm.sort.impl;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.example.algorithm.sort.Sort;
import org.example.algorithm.sort.SortName;

import java.util.ArrayList;

/**
 * 归并排序
 * 中文博客：https://zhuanlan.zhihu.com/p/36075856
 * 视频：https://www.bilibili.com/video/BV1oV41187z1?spm_id_from=333.999.0.0
 * leetcode: https://leetcode.com/problems/sort-an-array/
 */
@SortName("归并")
public class MergeSortDemo extends Sort {

    /**
     * -----   -----   ----    ArrayList实现     -----   -----   ----
     * -----   -----   ----    ArrayList实现     -----   -----   ----
     * -----   -----   ----    ArrayList实现     -----   -----   ----
     */
    @Override
    protected ArrayList<Integer> sort(ArrayList<Integer> unSortArray) {
        return mergSort(unSortArray);
    }

    private ArrayList<Integer> mergSort(ArrayList<Integer> unSortArray) {
        int minIndex = 0;
        int maxIndex = unSortArray.size() - 1;
        if (minIndex == maxIndex) {
            return unSortArray;
        }

        int mid = (minIndex + maxIndex) / 2;
        Pair<ArrayList<Integer>, ArrayList<Integer>> pair = arrayToList(unSortArray, mid);
        ArrayList<Integer> left = mergSort(pair.getKey());
        ArrayList<Integer> right = mergSort(pair.getValue());

        return mergTwoList(left, right);
    }

    private ArrayList<Integer> mergTwoList(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> resultList = Lists.newArrayList();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                resultList.add(left.get(leftIndex));
                leftIndex++;
            } else {
                resultList.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            resultList.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            resultList.add(right.get(rightIndex));
            rightIndex++;
        }

        return resultList;
    }

    private Pair<ArrayList<Integer>, ArrayList<Integer>> arrayToList(ArrayList<Integer> unSortArray, int midIndex) {
        ArrayList<Integer> leftList = Lists.newArrayList();
        ArrayList<Integer> rightList = Lists.newArrayList();
        for (int i = 0; i < unSortArray.size(); i++) {
            if (i <= midIndex) {
                leftList.add(unSortArray.get(i));
            } else {
                rightList.add(unSortArray.get(i));
            }
        }

        return new Pair<>(leftList, rightList);
    }


    /**
     * -----   -----   ----    数组实现     -----   -----   ----
     * -----   -----   ----    数组实现     -----   -----   ----
     * -----   -----   ----    数组实现     -----   -----   ----
     */
    @Override
    protected int[] sort(int[] unSortArray) {
        int[] result = new int[unSortArray.length];
        mergSort(unSortArray, 0, unSortArray.length - 1, result);
        return result;
    }

    @Override
    protected String getName() {
        return "-------归并-----";
    }

    private void mergSort(int[] unSortArray, int begin, int end, int[] result) {
        if (begin >= end) {
            return;
        }

        int mid = (begin + end) / 2;
        mergSort(unSortArray, begin, mid, result);
        mergSort(unSortArray, mid + 1, end, result);
        mergTwoList(unSortArray, begin, mid, end, result);
    }

    private void mergTwoList(int[] unSortArray, int begin, int mid, int end, int[] result) {
        int leftBegin = begin;
        int rightBegin = mid + 1;

        int resultIndex = 0;
        while (leftBegin <= mid && rightBegin <= end) {
            if (unSortArray[leftBegin] < unSortArray[rightBegin]) {
                result[resultIndex++] = unSortArray[leftBegin++];
            } else {
                result[resultIndex++] = unSortArray[rightBegin++];
            }
        }

        while (leftBegin <= mid) {
            result[resultIndex++] = unSortArray[leftBegin++];
        }

        while (rightBegin <= end) {
            result[resultIndex++] = unSortArray[rightBegin++];
        }

        for (int i = 0; i < resultIndex; i++) {
            unSortArray[begin + i] = result[i];
        }

    }
}
