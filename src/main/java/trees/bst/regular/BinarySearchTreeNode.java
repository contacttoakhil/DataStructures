package main.java.trees.bst.regular;

import main.java.trees.BinaryTreeNode;

public class BinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {

    public BinarySearchTreeNode(T data) {
        super(data);
    }

    public BinarySearchTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        super(data, left, right);
    }
}
