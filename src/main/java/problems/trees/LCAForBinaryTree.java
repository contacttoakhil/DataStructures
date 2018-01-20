package main.java.problems.trees;

import main.java.ds.trees.BinaryTreeNode;

public class LCAForBinaryTree {
    /**
     * Returns the lowest common ancestor for nodes nodeOne and nodeTwo
     * @param root root of the tree
     * @param nodeOne
     * @param nodeTwo
     * @param <T>
     * @return lca of two nodes
     */
    public static <T extends Comparable<T>> BinaryTreeNode<T> lowestCommonAncestor(BinaryTreeNode<T> root , BinaryTreeNode<T> nodeOne, BinaryTreeNode<T> nodeTwo) {
        if(root == null) return null;
        if(root.equals(nodeOne) || root.equals(nodeTwo)) {
            // At least one match? no need to continue as this is LCA
            return root;
        }
        BinaryTreeNode<T> left = lowestCommonAncestor(root.getLeft(), nodeOne, nodeTwo);
        BinaryTreeNode<T> right = lowestCommonAncestor(root.getRight(), nodeOne, nodeTwo);
        if (left != null && right != null) {
            return root;  // nodes are each on a separate branch
        }
        // either one node is on one branch or none found in any of branches
        return left != null ? left : right;

    }
}
