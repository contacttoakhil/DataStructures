package main.java.ds.trees.bst;

import main.java.ds.trees.bst.regular.BinarySearchTreeNode;

public interface INodeCreator<T extends Comparable<T>> {
    BinarySearchTreeNode<T> createNewNode(T value);
}
