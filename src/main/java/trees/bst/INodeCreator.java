package main.java.trees.bst;

import main.java.trees.bst.regular.BinarySearchTreeNode;

public interface INodeCreator<T extends Comparable<T>> {
    BinarySearchTreeNode<T> createNewNode(T value);
}
