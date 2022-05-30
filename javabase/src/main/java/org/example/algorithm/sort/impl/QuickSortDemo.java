package org.example.algorithm.sort.impl;

import com.google.common.collect.Lists;
import org.example.algorithm.sort.Sort;
import org.example.algorithm.sort.SortName;

import java.util.ArrayList;

/**
 * 快速排序
 * 中文博客：https://zhuanlan.zhihu.com/p/35946897
 * 视频：https://www.bilibili.com/video/BV15f4y1i7Vt?spm_id_from=333.999.0.0
 * 视频2：https://www.bilibili.com/video/BV1it41167v2?from=search&seid=1512810624812815539&spm_id_from=333.337.0.0
 * leetcode: https://leetcode.com/problems/sort-an-array/
 */
@SortName("快速排序")
public class QuickSortDemo extends Sort {

    @Override
    protected ArrayList<Integer> sort(ArrayList<Integer> unSortArray) {
        if (unSortArray.size() <= 1) {
            return unSortArray;
        }

        Integer povit = unSortArray.get(0);

        ArrayList<Integer> smallThan = Lists.newArrayList();
        ArrayList<Integer> biggerThan = Lists.newArrayList();
        ArrayList<Integer> eqThan = Lists.newArrayList();

        for (Integer integer : unSortArray) {
            if (integer.equals(povit)) {
                eqThan.add(integer);
            } else if (integer < povit) {
                smallThan.add(integer);
            } else {
                biggerThan.add(integer);
            }
        }

        ArrayList<Integer> resultList = Lists.newArrayList();
        resultList.addAll(sort(smallThan));
        resultList.addAll(eqThan);
        resultList.addAll(sort(biggerThan));
        return resultList;
    }

    @Override
    protected int[] sort(int[] unSortArray) {
        quickSort(unSortArray,0,unSortArray.length -1 );
        return unSortArray;
    }

    private void quickSort(int[] unSortArray, int begin, int end) {
        if (begin > end) {
            return;
        }

        int head = begin;
        int tail = end;
        int base = unSortArray[begin];

        while (head < tail){
            while (head < tail && unSortArray[tail] >= base){
                tail--;
            }
            while(head < tail && unSortArray[head] <= base){
                head++;
            }
            exchange(unSortArray,tail,head);
        }

        unSortArray[begin] =  unSortArray[head];
        unSortArray[head] = base;

        quickSort(unSortArray,begin,tail-1);
        quickSort(unSortArray,tail+1,end);

    }

    private void exchange(int[] unSortArray, int left, int right) {
        int tmp = unSortArray[left];
        unSortArray[left] = unSortArray[right];
        unSortArray[right] = tmp;
    }

    @Override
    protected String getName() {
        return "-------快排-----";
    }

}
