package org.example.algorithm.bfs_dfs.impl;

import org.example.algorithm.bfs_dfs.face.BFS;
import org.example.algorithm.util.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class L103_ZigzagOrder implements BFS {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        forEachRoot(root, result);
        return result;

    }


    public void forEachRoot(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        // 是否正序
        boolean isAsc = true;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);

        // 遍历整个树
        while (!q.isEmpty()) {

            // 当前轮结果
            LinkedList<Integer> curResult = new LinkedList<>();

            // 遍历当前层
            int curSize = q.size();
            while (curSize != 0) {


                // 从头结点取元素
                TreeNode curNode = q.removeFirst();
                // 追加队列
                if (curNode.left != null) {
                    q.add(curNode.left);
                }
                if (curNode.right != null) {
                    q.add(curNode.right);
                }

                if (isAsc) {
                    curResult.add(curNode.val);
                } else {
                    curResult.addFirst(curNode.val);
                }

                curSize--;
            }

            isAsc = !isAsc;
            result.add(curResult);
        }
    }

    @Override
    public void BFSWay(Object... params) {
        TreeNode param = (TreeNode) params[0];
        List<List<Integer>> order = zigzagLevelOrder(param);
        order.forEach(
                entity -> {
                    entity.forEach(System.out::print);
                    System.out.println("--");
                });
    }
}
