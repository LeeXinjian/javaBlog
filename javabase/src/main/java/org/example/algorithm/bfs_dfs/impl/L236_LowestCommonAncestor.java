package org.example.algorithm.bfs_dfs.impl;

import com.google.common.collect.Lists;
import org.example.algorithm.bfs_dfs.face.DFS;
import org.example.algorithm.util.binarytree.TreeNode;

public class L236_LowestCommonAncestor implements DFS {

    public TreeNode doDFSWay(TreeNode root, TreeNode p, TreeNode q) {
        // p,q均非当前结点的子结点
        if (root == null || p == null || q == null) {
            return null;
        }

        TreeNode left = doDFSWay(root.left, p, q);
        TreeNode right = doDFSWay(root.right, p, q);

        // 当前结点不在这边
        if (right == null && left == null) {
            return null;
        }

        // 说明找到了右结点,返回匹配左结点
        if (left == null) {
            return right;
        }

        // 说明找到了左结点,返回匹配左结点
        if (right == null) {
            return left;
        }

        // 到这里说明左右结点分别在两侧，返回此结点。
        return root;

    }

    @Override
    public void DFSWay(Object... params) {

    }
}
