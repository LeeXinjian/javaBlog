package org.example.algorithm.bfs_dfs.impl;

import org.example.algorithm.bfs_dfs.face.BFS;
import org.example.algorithm.bfs_dfs.face.DFS;

import java.util.LinkedList;

/**
 * leecode : https://leetcode-cn.com/problems/number-of-islands/solution/number-of-islands-shen-du-you-xian-bian-li-dfs-or-/
 * 教程 ：https://www.bilibili.com/video/BV1C54y1G7fx/
 */
public class L200_NumOfLands implements BFS, DFS {


    @Override
    public void BFSWay(Object... params) {
        char[][] lands = (char[][]) params[0];
        System.out.println(bfsEnter(lands));
    }

    /**
     * TODO 有点问题 还没debug 2022年5月7日14:32:53
     *
     * @param lands
     * @return
     */
    private int bfsEnter(char[][] lands) {
        if (lands == null) {
            return 0;
        }

        int rowLength = lands.length;
        int colLength = lands[0].length;

        int ans = 0;
        for (int curRow = 0; curRow < rowLength; curRow++) {
            for (int curCol = 0; curCol < lands[curRow].length; curCol++) {
                if (lands[curRow][curCol] == '0') {
                    continue;
                }
                bfs(lands, rowLength, colLength, curRow, curCol);
                ans++;
            }
        }

        return ans;
    }

    private void bfs(char[][] lands, int rowLength, int colLength, int curRow, int curCol) {
        LinkedList<BFSNode> queue = new LinkedList<>();
        queue.add(new BFSNode(curRow, curCol));

        while (queue.size() > 0) {

            BFSNode node = queue.pop();
            int row = node.row;
            int col = node.col;

            if (row < 0 || col < 0
                    || row >= rowLength || col >= colLength
                    || lands[curRow][curCol] == '0') {
                continue;
            }

            // 重置为0
            lands[curRow][curCol] = '0';

            queue.add(new BFSNode(curRow - 1, curCol));
            queue.add(new BFSNode(curRow + 1, curCol));
            queue.add(new BFSNode(curRow, curCol - 1));
            queue.add(new BFSNode(curRow, curCol + 1));
        }
    }


    @Override
    public void DFSWay(Object... params) {
        char[][] lands = (char[][]) params[0];
        System.out.println(dfsEnter(lands));
    }

    private int dfsEnter(char[][] lands) {
        if (lands == null) {
            return 0;
        }

        int allRowLen = lands.length;
        int allColLen = lands[0].length;

        int ans = 0;
        for (int curRow = 0; curRow < lands.length; curRow++) {
            for (int curCol = 0; curCol < lands[curRow].length; curCol++) {
                if (doDfsWay(lands, curRow, curCol, allRowLen, allColLen) == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public int doDfsWay(char[][] lands, int curRow, int curCol, int allRowLength, int allColLength) {

        // 区域范围外 或者不是不岛
        if (curCol < 0 || curRow < 0
                || curRow >= allRowLength || curCol >= allColLength
                || lands[curRow][curCol] == '0') {
            return 0;
        }

        // 是岛屿则对自己清零,避免循环引用
        lands[curRow][curCol] = '0';

        doDfsWay(lands, curRow + 1, curCol, allRowLength, allColLength);
        doDfsWay(lands, curRow - 1, curCol, allRowLength, allColLength);
        doDfsWay(lands, curRow, curCol - 1, allRowLength, allColLength);
        doDfsWay(lands, curRow, curCol + 1, allRowLength, allColLength);

        return 1;

    }


}


class BFSNode {
    // 行
    int row;
    // 列
    int col;

    BFSNode(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
