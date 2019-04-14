package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.Stack;

/***
 * Given a binary tree, flatten it to a linked list in-place.
 * Hint : Go down the tree to the left, when the right child is not null, push the right child to the stack.
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class LC114FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;

        while(p != null || !stack.empty()){

            if(p.right != null){
                stack.push(p.right);
            }

            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                TreeNode temp = stack.pop();
                p.right=temp;
            }

            p = p.right;
        }
    }

}
