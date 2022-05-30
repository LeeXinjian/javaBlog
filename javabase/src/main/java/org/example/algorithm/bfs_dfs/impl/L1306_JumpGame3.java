package org.example.algorithm.bfs_dfs.impl;

import org.example.algorithm.bfs_dfs.face.BFS;
import org.example.algorithm.bfs_dfs.face.DFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class L1306_JumpGame3 implements BFS, DFS {

    /**
     * Bfs
     * 教程 ：https://www.bilibili.com/video/BV1i64y1f772?spm_id_from=333.999.0.0
     * leetcode : https://leetcode-cn.com/problems/jump-game-iii/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     */
    private boolean canReachBfs(int[] arr, int start) {

        if (arr == null || arr.length == 0) {
            return false;
        }

        // 初始化队列
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);

        // 走过的位置
        Set<Integer> passIndex = new HashSet<>();

        // 队列中有元素
        while (queue.size() > 0) {
            // 从头部pop一个
            Integer pop = queue.pop();
            int curValue = arr[pop];
            if (curValue == 0) {
                return true;
            }

            int leftIndex = pop - curValue;
            int rightIndex = pop + curValue;

            checkAndAddToQueue(arr, queue, passIndex, leftIndex);
            checkAndAddToQueue(arr, queue, passIndex, rightIndex);
        }

        return false;

    }

    /**
     * Dfs
     * 教程 ：https://www.bilibili.com/video/BV1Uy4y1S73y/?spm_id_from=333.788
     * leetcode : https://leetcode-cn.com/problems/jump-game-iii/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     */
    private boolean canReachDfs(int[] arr, int i, Set<Integer> noteBook) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int curOne = arr[i];

        if (curOne == 0) {
            return true;
        }

        // 当前没有到达边界,且没有循环过当前节点
        if (doDfs(arr, noteBook, i, i - curOne)) {
            return true;
        }

        if (doDfs(arr, noteBook, i, i + curOne)) {
            return true;
        }

        noteBook.remove(curOne);
        return false;

    }

    private boolean doDfs(int[] arr, Set<Integer> noteBook, int curIndex, int nextIndex) {
        if (nextIndex >= 0 && nextIndex < arr.length && !noteBook.contains(nextIndex)) {
            noteBook.add(curIndex);
            return canReachDfs(arr, nextIndex, noteBook);
        }

        return false;
    }

    private void checkAndAddToQueue(int[] arr, LinkedList<Integer> queue, Set<Integer> passIndex, int rightIndex) {
        if (rightIndex >= 0 && rightIndex < arr.length) {
            if (!passIndex.contains(rightIndex)) {
                queue.add(rightIndex);
                passIndex.add(rightIndex);
            }
        }
    }

    @Override
    public void BFSWay(Object... params) {
        System.out.println(
                canReachBfs((int[]) params[0], (Integer) params[1])
        );
    }

    @Override
    public void DFSWay(Object... params) {
        System.out.println(
                canReachDfs(
                        (int[]) params[0],
                        (Integer) params[1],
                        new HashSet<>()));
    }


}
