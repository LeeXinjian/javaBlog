package org.example.algorithm.bfs_dfs.impl;

import org.example.algorithm.bfs_dfs.Run;
import org.example.algorithm.util.binarytree.TreeNode;

public class L124_MaxTreeNodeValue {

    // 最大值
    public int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
         dfsBest(root);
         return max;
    }

    public int dfsBest(TreeNode root){
        if(root == null){
            return 0;
        }

        int curSignalValue = root.val;
        int leftMaxValue = dfsBest(root.left);
        int rightMaxvalue = dfsBest(root.right);

        max = Math.max(curSignalValue,max);
        max = Math.max(curSignalValue + leftMaxValue, max);
        max = Math.max(curSignalValue + rightMaxvalue, max);
        max = Math.max(curSignalValue + rightMaxvalue + leftMaxValue, max);

        return Math.max(curSignalValue,Math.max(leftMaxValue + curSignalValue, rightMaxvalue + curSignalValue));

    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        new L124_MaxTreeNodeValue().maxPathSum(node);
    }

}
