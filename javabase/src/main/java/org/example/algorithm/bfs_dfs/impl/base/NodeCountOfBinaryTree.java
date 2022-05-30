package org.example.algorithm.bfs_dfs.impl.base;

import org.example.algorithm.bfs_dfs.face.BFS;
import org.example.algorithm.bfs_dfs.face.DFS;
import org.example.algorithm.util.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * 计算结点数量
 */
public class NodeCountOfBinaryTree implements BFS, DFS {

    @Override
    public void BFSWay(Object... params) {
        TreeNode param = (TreeNode) params[0];
        System.out.println(doBFSWay(param));
    }

    private int doBFSWay(TreeNode param) {
        if (param == null) {
            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(param);

        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode pop = queue.pop();
            sum++;
            if (pop.left != null) {
                queue.add(pop.left);
            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }

        return sum;
    }

    @Override
    public void DFSWay(Object... params) {
        TreeNode param = (TreeNode) params[0];
        System.out.println(doDFSWay(param));
    }

    private int doDFSWay(TreeNode param) {
        if (param == null) {
            return 0;
        }

        int rightVal = doDFSWay(param.right);
        int leftVal = doDFSWay(param.left);

        return leftVal + rightVal + 1;
    }
}
