package org.example.algorithm.bfs_dfs.impl.base;

import com.google.common.collect.Lists;
import org.example.algorithm.bfs_dfs.face.BFS;
import org.example.algorithm.bfs_dfs.face.DFS;
import org.example.algorithm.util.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * 遍历二叉树
 */
public class BinaryTreeForeach implements BFS, DFS {


    private ArrayList<Integer> bfsTree(TreeNode head) {
        if (head == null) {
            return null;
        }


        ArrayList<Integer> results = Lists.newArrayList();
        LinkedList<TreeNode> quene = new LinkedList<>();
        quene.add(head);

        while (quene.size() != 0) {
            TreeNode pop = quene.pop();
            results.add(pop.val);

            if (pop.left != null) {
                quene.add(pop.left);
            }

            if (pop.right != null) {
                quene.add(pop.right);
            }
        }

        return results;

    }

    private void dfsTree(TreeNode head, ArrayList<Integer> result) {

        if (head == null) {
            return;
        }

        result.add(head.val);

        dfsTree(head.left, result);
        dfsTree(head.right, result);
    }


    @Override
    public void BFSWay(Object... params) {
        bfsTree((TreeNode) params[0]).forEach(System.out::println);
    }

    @Override
    public void DFSWay(Object... params) {
        ArrayList<Integer> result = Lists.newArrayList();
        dfsTree((TreeNode) params[0], result);
        result.forEach(System.out::println);
    }
}
