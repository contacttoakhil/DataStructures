package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 *
 * Hint:
 * a) Consider implement these two helper functions:
 *    i) getPredecessor(N), which returns the next smaller node to N.
 *    ii)getSuccessor(N), which returns the next larger node to N.
 * b) Try to assume that each node has a parent pointer, it makes the problem much easier.
 * c) Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
 * d) You would need two stacks to track the path in finding predecessor and successor node separately.
 *
 */
public class LC272ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<Integer>();
        LinkedList<Integer> stackPre = new LinkedList<Integer>();
        LinkedList<Integer> stackSucc = new LinkedList<Integer>();
        inorder(root, target, false, stackPre);
        inorder(root, target, true, stackSucc);
        while (k-- > 0) {
            if (stackPre.isEmpty()) {
                result.add(stackSucc.pop());
            } else if (stackSucc.isEmpty()) {
                result.add(stackPre.pop());
            } else if (Math.abs(stackPre.peek() - target) < Math.abs(stackSucc.peek() - target)) {
                result.add(stackPre.pop());
            } else {
                result.add(stackSucc.pop());
            }
        }
        return result;
    }
    public void inorder(TreeNode root, double target, boolean reverse, LinkedList<Integer> stack) {
        if (root == null) return;
        inorder(reverse ? root.right : root.left, target, reverse, stack);
        if ((reverse && root.data <= target) || (!reverse && root.data > target))
            return;
        stack.push(root.data);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
}
