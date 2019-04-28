package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/***
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * Solution:
 * 1. Push root to first stack.
 * 2. Loop while first stack is not empty
 *    2.1 Pop a node from first stack and push it to second stack
 *    2.2 Push left and right children of the popped node to first stack
 * 3. Print contents of second stack
 */
public class LC145PostOrderTraversalIterative {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root==null) {
            return res;
        }
        Stack<TreeNode> first = new Stack<TreeNode>();
        Stack<TreeNode> second = new Stack<TreeNode>();
        first.push(root);
        while(!first.isEmpty()) {
            TreeNode node = first.pop();
            second.push(node);
            if(node.left != null)
                first.push(node.left);
            if(node.right != null)
                first.push(node.right);
        }
        while (!second.isEmpty()) {
            res.add(second.pop().data);
        }
        return res;
    }
}
