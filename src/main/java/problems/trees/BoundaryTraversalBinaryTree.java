package main.java.problems.trees;

import main.java.problems.trees.domain.BinaryTree;
import main.java.problems.trees.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoundaryTraversalBinaryTree {
    List<Integer> nodes = new ArrayList<>(1000);

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null) return nodes;
        nodes.add(root.data);
        leftBoundary(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBoundary(root.right);
        return nodes;
    }
    public void leftBoundary(TreeNode root) {
        if(root == null || isLeaf(root)) return;
        nodes.add(root.data);
        if(root.left == null) leftBoundary(root.right);
        else leftBoundary(root.left);
    }
    public void rightBoundary(TreeNode root) {
        if(root == null || isLeaf(root)) return;
        if(root.right == null)rightBoundary(root.left);
        else rightBoundary(root.right);
        nodes.add(root.data); // add after child visit(reverse)
    }
    private boolean isLeaf(TreeNode node) {
        if(node.left == null && node.right == null)
            return true;
        return false;
    }
    public void leaves(TreeNode root) {
        if(root == null) return;
        if(isLeaf(root)) {
            nodes.add(root.data);
            return;
        }
        leaves(root.left);
        leaves(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = getBinaryTree();
        BoundaryTraversalBinaryTree bt = new BoundaryTraversalBinaryTree();
        List<Integer> traversal = bt.boundaryOfBinaryTree(root);
        String result = traversal.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(result);
    }

    private static TreeNode getBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(20);
        tree.root.left = new TreeNode(8);
        tree.root.right = new TreeNode(22);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(12);
        tree.root.right.right = new TreeNode(25);
        tree.root.left.right.left = new TreeNode(10);
        tree.root.left.right.right = new TreeNode(14);
        return tree.root;
    }
}
