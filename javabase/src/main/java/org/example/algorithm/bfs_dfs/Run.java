package org.example.algorithm.bfs_dfs;

import org.example.algorithm.bfs_dfs.impl.L103_ZigzagOrder;
import org.example.algorithm.bfs_dfs.impl.base.BinaryTreeForeach;
import org.example.algorithm.bfs_dfs.impl.base.NodeCountOfBinaryTree;
import org.example.algorithm.bfs_dfs.impl.base.SumValueOfBinaryTree;
import org.example.algorithm.util.binarytree.TreeNode;

public class Run {

    public static void main(String[] args) {
//        int[] array = {1, 2, 3, 0, 2, 3};
//
//        L1306_JumpGame3 jumpGame3 = new L1306_JumpGame3();
//        jumpGame3.BFSWayRun(() -> new Object[]{array, 3});
//        jumpGame3.DFSWayRun(() -> new Object[]{array, 3});


//        new BinaryTreeForeach().BFSWayRun(Run::initTreeNode);
//        new BinaryTreeForeach().DFSWayRun(Run::initTreeNode);

//        SumValueOfBinaryTree binaryTree = new SumValueOfBinaryTree();
//        binaryTree.DFSWayRun(Run::initTreeNode);
//        binaryTree.BFSWayRun(Run::initTreeNode);

//        NodeCountOfBinaryTree binaryTree = new NodeCountOfBinaryTree();
//        binaryTree.DFSWayRun(Run::initTreeNode);
//        binaryTree.BFSWayRun(Run::initTreeNode);

        new L103_ZigzagOrder().BFSWayRun(Run::initTreeNode);
    }



    public static Object[] initTreeNode() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        TreeNode left = node.left;
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);

        TreeNode right = node.right;
        right.left = new TreeNode(6);
        right.right = new TreeNode(7);

        return new Object[]{node};
    }
}
