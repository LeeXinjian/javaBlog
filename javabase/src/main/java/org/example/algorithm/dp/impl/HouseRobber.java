package org.example.algorithm.dp.impl;

import com.google.common.collect.Maps;
import org.example.algorithm.dp.DynamicProgram;

import java.util.HashMap;

import static java.lang.Math.max;

/**
 * 打家劫舍问题
 * [1],[8],[3],[4],[2],[6]
 * 抢钱,但是如果你抢了一家的钱就没法抢他邻居的钱
 * 问怎么抢收入最高？
 * <p>
 * 从底至上想法:
 * 如果只有一家,抢他行了
 * 如果有两家,那我得挑个贵的抢
 * 如果有三家,那么有两个选择:
 * 1. 抢他就ok,那么我们得到的钱就是第一家+第三家
 * 2. 如果不抢,那就是抢第二家
 * 1.2取高的
 * 那么可以得到推理
 * 我们能赚到最多的钱 = max(
 * f(n-2) + val(n),
 * f(n-1)
 * )
 * <p>
 * 规划他！
 */
public class HouseRobber implements DynamicProgram {

    @Override
    public void buttomUp() {
        int[] value = {1, 8, 3, 4, 2, 6};
        System.out.println(houseRobberDownToTop(value, value.length - 1, Maps.newHashMap()));
    }

    @Override
    public void topDown() {
        int[] value = {1, 8, 3, 4, 2, 6};
        System.out.println(houseRobberTopToDown(value, value.length - 1));
    }

    private int houseRobberDownToTop(int[] values, int curIndex, HashMap<Integer, Integer> noteBook) {

        if (curIndex == 0 || curIndex == 1) {
            noteBook.put(curIndex, values[curIndex]);
            return noteBook.get(curIndex);
        }

        putToNoteBook(values, curIndex, noteBook, 1);
        putToNoteBook(values, curIndex, noteBook, 2);

        return max(
                noteBook.get(curIndex - 1),
                noteBook.get(curIndex - 2) + values[curIndex]
        );
    }

    private void putToNoteBook(int[] values, int curIndex, HashMap<Integer, Integer> noteBook, int i) {
        if (noteBook.get(curIndex - i) == null) {
            noteBook.put(curIndex - i, houseRobberDownToTop(values, curIndex - i, noteBook));
        }
    }

    private int houseRobberTopToDown(int[] values, int curIndex) {
        if (curIndex == 0 || curIndex == 1) {
            return values[curIndex];
        }

        int max = 0;
        for (int i = 2; i <= curIndex; i++) {
            max = max(
                    houseRobberTopToDown(values, curIndex - 1),
                    houseRobberTopToDown(values, curIndex - 2) + values[curIndex]
            );
        }

        return max;
    }
}
