package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Solution: To avoid global variable we are tracking max as int[].
 */
public class LC124BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(max, root);
        return max[0];
    }
    private int maxPathSum(int[] max, TreeNode root){
        if(root == null)
            return 0;
        int leftMax =  Math.max(0, maxPathSum(max, root.left));
        int rightMax = Math.max(0, maxPathSum(max, root.right));
        max[0] = Math.max(max[0],  root.data+leftMax+rightMax);
        return root.data + Math.max(leftMax,rightMax);
    }

}
