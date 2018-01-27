package main.java.problems.trees;

import main.java.problems.trees.domain.BinaryTree;
import main.java.problems.trees.domain.TreeNode;

public class CheckBalancedBinaryTree {

    private static int INVALID_HEIGHT = Integer.MIN_VALUE; // constant for invalid height

    private int checkHeight(TreeNode root) {
        if(root == null) return -1;

        int lh = checkHeight (root.left);
        if(lh == INVALID_HEIGHT)
            throw new RuntimeException("Tree is imbalanced at node:" + root.left);

        int rh = checkHeight (root.right);
        if(rh == INVALID_HEIGHT)
            throw new RuntimeException("Tree is imbalanced at node:" + root.right);

        if(Math.abs(lh - rh) > 1)
            throw new RuntimeException("Tree is imbalanced at node:" + root);
        else return 1 + Math.max(lh, rh);
    }

    /*public int checkHeight(TreeNode root) {
        if(root == null) return -1;
        int lh = checkHeight (root.left);
        if(lh == INVALID_HEIGHT) return INVALID_HEIGHT;
        int rh = checkHeight (root.right);
        if(rh == INVALID_HEIGHT) return INVALID_HEIGHT;
        if(Math.abs(lh - rh) > 1) return INVALID_HEIGHT;
        else return 1 + Math.max(lh, rh);
    }*/

    private boolean isBalanced(TreeNode root) {
        return checkHeight(root) != INVALID_HEIGHT;
    }

    public static void main(String[] args) {
        CheckBalancedBinaryTree cbbt = new CheckBalancedBinaryTree();
        System.out.println("Balanced:" + cbbt.isBalanced(getBalancedBinaryTree()));
        System.out.println("Balanced:" + cbbt.isBalanced(getImbalancedBinaryTree()));
    }
    private static TreeNode getBalancedBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
        return tree.root;
    }
    private static TreeNode getImbalancedBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.left.left.left = new TreeNode(6);
        tree.root.left.left.left.left = new TreeNode(7);
        return tree.root;
    }
}
