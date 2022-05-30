package org.example.algorithm.bfs_dfs.impl;

import com.google.common.collect.Lists;
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
        if (root == null) {
            return null;
        }

        // 结果集
        ArrayList<List<Integer>> resultList = Lists.newArrayList();
        // 队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 是否是正序打印
        boolean isAsc = true;

        while (!queue.isEmpty()) {
            // 每一层的结果
            LinkedList<Integer> levelResult = new LinkedList<>();
            // 当前size为当前层元素数量
            int size = queue.size();
            // 当前层
            for (int i = 0; i < size; i++) {
                // 从队列头弹出
                TreeNode pop = queue.pop();
                // 把孩子结点入队列
                addChildToQueue(queue, pop);
                // 按正序/逆序将结点放入结果集合
                putResultByOrder(isAsc, levelResult, pop);
            }
            resultList.add(levelResult);
            isAsc = !isAsc;
        }

        return resultList;
    }

    private void putResultByOrder(boolean isAsc, LinkedList<Integer> levelResult, TreeNode pop) {
        // 正序 , 往结果集的尾部
        if (isAsc) {
            levelResult.offerLast(pop.val);
        } else {
            // 逆序 , 往结果集头部
            levelResult.offerFirst(pop.val);
        }
    }

    private void addChildToQueue(LinkedList<TreeNode> queue, TreeNode pop) {
        TreeNode left = pop.left;
        TreeNode right = pop.right;
        if (left != null) {
            queue.add(left);
        }
        if (right != null) {
            queue.add(right);
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
