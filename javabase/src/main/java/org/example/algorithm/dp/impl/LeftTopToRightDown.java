package org.example.algorithm.dp.impl;

import com.google.common.collect.Maps;
import javafx.util.Pair;
import org.example.algorithm.dp.DynamicProgram;

import java.util.HashMap;

/**
 * [begin],[],[],[]
 * [] ,[],[],[]
 * [] ,[],[],[]
 * [] ,[],[],[end]
 * <p>
 * 从begin走到end,每次只能往下或者往右走一个单元
 * 问从begin->end有多少种走法
 * <p>
 * 解决：
 * 1. 最上边与最左边的兄弟们,只能有个来源,就是当前步的上/左来
 * [begin],[*],[*],[*]
 * [*] ,[ ],[ ],[ ]
 * [*] ,[ ],[ ],[ ]
 * [*] ,[ ],[ ],[end]
 * <p>
 * 2. 其他单元,有两个来处,一个是自己左边,一个是自己上边
 * *表示某个格子
 * &表示这个格子来源
 * [begin],[ ],[ ],[ ]
 * [] ,[ ],[&],[ ]
 * [ ] ,[&],[*],[ ]
 * [ ] ,[ ],[ ],[end]
 * <p>
 * 3. 所以走到*的走法 = 两个走到&的走法之和
 */

public class LeftTopToRightDown implements DynamicProgram {

    @Override
    public void buttomUp() {
        int[][] ints = new int[2][3];
        System.out.println(leftTopToRightDownDownToTop(ints));
    }

    @Override
    public void topDown() {
        System.out.println(leftTopToRightDownTopToDown(1, 2, Maps.newHashMap()));
    }

    public int leftTopToRightDownDownToTop(int[][] result) {

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (i == 0 || j == 0) {
                    result[i][j] = 1;
                } else {
                    result[i][j] = result[i - 1][j] + result[i][j - 1];
                }
            }
        }

        return result[result.length - 1][result[0].length - 1];
    }

    /**
     * @param curRow
     * @param curCol
     * @param noteBook key:位置,
     *                 left : row
     *                 right : col
     *                 value:多少种走法
     * @return 多少种走法
     */
    public int leftTopToRightDownTopToDown(int curCol, int curRow, HashMap<Pair<Integer, Integer>, Integer> noteBook) {

        if (curCol == 0 || curRow == 0) {
            Pair<Integer, Integer> pair = new Pair<>(curRow, curCol);
            noteBook.put(pair, 1);
            return noteBook.get(pair);
        }

        Pair<Integer, Integer> topPos = new Pair<>(curRow - 1, curCol);
        if (noteBook.get(topPos) == null) {
            noteBook.put(topPos, leftTopToRightDownTopToDown(curRow - 1, curCol, noteBook));
        }

        Pair<Integer, Integer> leftPos = new Pair<>(curRow, curCol - 1);
        if (noteBook.get(leftPos) == null) {
            noteBook.put(leftPos, leftTopToRightDownTopToDown(curRow, curCol - 1, noteBook));
        }

        return noteBook.get(topPos) + noteBook.get(leftPos);

    }
}
