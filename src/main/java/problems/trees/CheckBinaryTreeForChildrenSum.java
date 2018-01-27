package main.java.problems.trees;

import main.java.problems.trees.domain.BinaryTree;
import main.java.problems.trees.domain.TreeNode;

public class CheckBinaryTreeForChildrenSum {

    private boolean isSumPropTree(TreeNode node) {
        if ((node == null) || isLeaf(node))
            return true;
        int lv = nodeValue(node.left);      int rv = nodeValue(node.right);
        return node.data == lv + rv && isSumPropTree(node.left) && isSumPropTree(node.right);
    }

    private int nodeValue(TreeNode node) {
        return (node == null) ? 0 : node.data;
    }

    private boolean isLeaf(TreeNode node) {
        return (node == null) || (node.left == null && node.right == null);
    }

    public static void main(String[] args) {
        CheckBinaryTreeForChildrenSum cst = new CheckBinaryTreeForChildrenSum();
        TreeNode root = getBinaryTree();
        if (cst.isSumPropTree(root))
            System.out.println("The given tree satisfies children sum property");
        else
            System.out.println("The given tree does not satisfy children sum property");
    }

    private static TreeNode getBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(8);
        tree.root.right = new TreeNode(2);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.right = new TreeNode(2);
        return tree.root;
    }
}
