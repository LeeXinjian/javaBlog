package org.example.algorithm.sort.main;

import org.example.algorithm.sort.Sort;
import org.example.algorithm.sort.impl.HeapSortDemo;

import java.util.ArrayList;
import java.util.Arrays;

public class Run {

    public static void main(String[] args) {
//        int[] unSort = {1, 23, 4, 54, 5657, 2, 21, 4, 2};
//        ArrayList<Integer> unSortList = Lists.newArrayList(1, 23, 4, 54, 5657, 2, 21, 4, 2);

//        sort(unSort, unSortList, new MergeSortDemo());
//        sort(unSort, unSortList, new QuickSortDemo());
    }

    private static void sort(int[] unSort, ArrayList<Integer> unSortList, Sort sort) {
        System.out.println(sort.getSortName() + "List开始");
        sort.doSort(unSortList).forEach(System.out::println);

        System.out.println();

        System.out.println(sort.getSortName() + "数组开始");
        Arrays.stream(sort.doSort(unSort)).forEach(System.out::println);

        System.out.println();
        System.out.println();
    }

}
