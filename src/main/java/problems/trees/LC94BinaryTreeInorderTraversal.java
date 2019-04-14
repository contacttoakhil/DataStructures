package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class LC94BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.data);
            curr = curr.right;
        }
        return res;
    }
}
