package org.example.algorithm.sort.impl;

import org.example.algorithm.sort.Sort;
import org.example.algorithm.sort.SortName;

import java.util.ArrayList;
import java.util.Arrays;

@SortName("堆排序")
public class HeapSortDemo extends Sort {

    public static void main(String[] args) {
        int[] array = {2, 1};
        int[] sort = new HeapSortDemo().sort(array);
        System.out.println(Arrays.toString(sort));
    }

    @Override
    protected ArrayList<Integer> sort(ArrayList<Integer> unSortArray) {
        return null;
    }

    @Override
    protected int[] sort(int[] unSortArray) {
        return heapSort(unSortArray, 2);
    }

    private int[] heapSort(int[] unSortArray, int k) {
        if (unSortArray.length < k) {
            return heapSort(unSortArray, unSortArray.length);
        }

        // 最大堆初始化
        int[] maxHeap = initMaxHeap(k);
        return heapSort(unSortArray, maxHeap);
    }

    private int[] initMaxHeap(int k) {
       int[] maxHeap = new int[k];
        for (int i = 0; i < k; i++) {
            maxHeap[i] = Integer.MIN_VALUE;
        }
        return maxHeap;
    }

    private int[] heapSort(int[] unSortArray, int[] maxHeap) {
        for (int i = 0; i < unSortArray.length; i++) {
            // 最大堆没满 或者 堆中最小的比当前要小,往堆中放元素
            if (maxHeap[0] < unSortArray[i]) {
                putToHeap(unSortArray[i], maxHeap);
            }
        }
        return maxHeap;
    }

    private void putToHeap(int i, int[] maxHeap) {
        for (int num = 0; num < maxHeap.length; num++) {
            if (i > maxHeap[num]) {
                if (num == 0) {
                    maxHeap[num] = i;
                } else {
                    maxHeap[num - 1] = maxHeap[num];
                    maxHeap[num] = i;
                }
            }
        }
    }

}
