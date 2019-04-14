package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * a) The left subtree of a node contains only nodes with keys less than the node's key.
 * b) The right subtree of a node contains only nodes with keys greater than the node's key.
 * c) Both the left and right subtrees must also be binary search trees.
 *
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class LC98ValidateBST {

    public boolean isBSTHelper(TreeNode node, Integer lower_limit, Integer upper_limit) {
        if ((lower_limit != null) && (node.data <= lower_limit))
            return false;
        if ((upper_limit != null) && (upper_limit <= node.data))
            return false;

        boolean leftIsBST = node.left != null ? isBSTHelper(node.left, lower_limit, node.data) : true;
        if((!leftIsBST))
            return false;
        boolean right = node.right != null ? isBSTHelper(node.right, node.data, upper_limit) : true;
        return right;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        return isBSTHelper(root, null, null);
    }

}
