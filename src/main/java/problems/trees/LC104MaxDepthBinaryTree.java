package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class LC104MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
