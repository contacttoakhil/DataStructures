package main.java.problems.trees;

import com.sun.source.tree.Tree;
import main.java.problems.trees.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *children;
 * }
 * Populate each children pointer to point to its children right node. If there is no children right node, the children pointer should be set to NULL.
 *
 * Initially, all children pointers are set to NULL.
 */
public class LC116PopulatingNextRightPointersinEachNode {

    public Node connect(Node root) {
        helper(root);
        return root;
    }
    public void helper(Node node){
        if(node == null) return;
        Node left = node.left;
        Node right = node.right;
        while(left != null){
            left.next = right;
            left = left.right;
            right = right.left;
        }
        helper(node.left);
        helper(node.right);
        return;
    }

}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};