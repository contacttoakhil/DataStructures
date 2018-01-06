package main.java.trees.bst.regular;

public class BinarySearchTreeUtils {
    /**
     * Returns the lowest common ancestor for nodes nodeOne and nodeTwo in a BST.
     * @param root root of the tree
     * @param nodeOne
     * @param nodeTwo
     * @param <T>
     * @return lca of two nodes
     */
    public static <T extends Comparable<T>> BinarySearchTreeNode<T> leastCommonAncestor(BinarySearchTreeNode<T> root , BinarySearchTreeNode<T> nodeOne, BinarySearchTreeNode<T> nodeTwo) {
        if (root == null || nodeOne == null || nodeTwo == null) {
            return null;
        }
        T max = findMax(nodeOne, nodeTwo).getData();
        T min = findMin(nodeOne, nodeTwo).getData();
        if (max.compareTo(root.getData()) < 0) {    // both nodes are on the left
            return leastCommonAncestor((BinarySearchTreeNode<T>) root.getLeft(), nodeOne, nodeTwo);
        } else if (min.compareTo(root.getData()) > 0) {   // both nodes are on the right
            return leastCommonAncestor((BinarySearchTreeNode<T>) root.getRight(), nodeOne, nodeTwo);
        } else {    // the nodes are on separate branches
            return root;
        }
    }

    private static <T extends Comparable<T>> BinarySearchTreeNode<T> findMin(BinarySearchTreeNode<T> nodeOne, BinarySearchTreeNode<T> nodeTwo) {
        if (nodeOne == null) {
            if (nodeTwo == null) return nodeOne;
            else return nodeTwo;
        }
        if (nodeTwo == null)
            return nodeOne;
        return nodeOne.getData().compareTo(nodeTwo.getData()) < 0 ? nodeOne : nodeTwo;
    }

    private static <T extends Comparable<T>> BinarySearchTreeNode<T> findMax(BinarySearchTreeNode<T> nodeOne, BinarySearchTreeNode<T> nodeTwo) {
        if (nodeOne == null) {
            if (nodeTwo == null) return nodeOne;
            else return nodeTwo;
        }
        if (nodeTwo == null)
            return nodeOne;
        return nodeOne.getData().compareTo(nodeTwo.getData()) > 0 ? nodeOne : nodeTwo;
    }
}
