package org.example.algorithm.bfs_dfs.impl;

import org.example.algorithm.bfs_dfs.face.BFS;
import org.example.algorithm.util.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class L199_BinaryTreeRightSee implements BFS {


    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }

        LinkedList<TreeNode> quque = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        quque.add(root);

        while (!quque.isEmpty()) {

            // 当前层
            int size = quque.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = quque.pop();
                if (curNode.left != null) {
                    quque.add(curNode.left);
                }
                if (curNode.right != null) {
                    quque.add(curNode.right);
                }
                // 当前行 最右边元素
                if (i == size - 1) {
                    result.add(curNode.val);
                }
            }
        }

        return result;
    }

    @Override
    public void BFSWay(Object... params) {
        TreeNode root = (TreeNode) params[0];
        rightSideView(root).forEach(System.out::println);
    }
}
