package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * https://leetcode.com/problems/symmetric-tree/
 */
public class LC101SymmetricTrees {

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null)
            return true;
        if (r == null || l == null)
            return false;
        return (l.data != r.data) && isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }
}
