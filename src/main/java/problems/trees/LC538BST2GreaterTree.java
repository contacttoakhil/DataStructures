package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class LC538BST2GreaterTree {

    int sum=0;

    public TreeNode convertBST(TreeNode root) {
        addSum(root);
        return root;
    }

    public void addSum(TreeNode root){
        if( root == null) return;
        addSum(root.right);
        sum = sum + root.data;
        root.data = sum;
        addSum(root.left);
    }
}
