package main.java.trees.bst.regular;

import main.java.trees.IBinaryTree;

import java.util.*;

public interface IBinarySearchTree<T extends Comparable<T>> extends IBinaryTree<T> {

    /**
     * This method returns root of the tree.
     * @return root of tree.
     */
    @Override
    BinarySearchTreeNode<T> getRootOfTree();

    void setRootOfTree(BinarySearchTreeNode<T> root);

    @Override
    default boolean validate() {
        boolean isBST = isBST();
        if(!isBST) System.err.println("Tree is not BST.");
        boolean isSizeCorrect = isSizeCorrect();
        if(!isSizeCorrect) System.err.println("Sizing of subtrees do not match");
        return (isBST && isSizeCorrect);
    }

    @Override
    default void clear() {
        setRootOfTree(null);
    }

    @Override
    default int size() {
        return getRootOfTree().getSize();
    }

    @Override
    default boolean contains(T value) {
        Objects.requireNonNull(value);
        BinarySearchTreeNode<T> node =  search(value);
        return (node != null);
    }

    /**
     * search tree for value in bst
     * @param value to search in binary search tree
     * @return the node which contains this value in bst
     */
    default BinarySearchTreeNode<T> search(T value) {
        return search(getRootOfTree(), value);
    }

    default BinarySearchTreeNode<T> search(BinarySearchTreeNode<T> node, T value) {
        Objects.requireNonNull(value);
        if(node == null) return null;
        if(isEmpty()) throw new NoSuchElementException("Empty Tree");
        int valueComparison = value.compareTo(node.getData());
        if (valueComparison < 0) return search((BinarySearchTreeNode<T>) node.getLeft(), value);
        else if (valueComparison > 0) return search((BinarySearchTreeNode<T>) node.getRight(),value);
        else return node;
    }

    /**
     * Removes the smallest node in this binary search tree and returns back the root.
     */
    default void deleteSmallest() {
        if(isEmpty()) throw new NoSuchElementException("Empty Tree");
        setRootOfTree(deleteSmallest(getRootOfTree()));
    }

    /**
     * removes the smallest node in this binary search tree.
     */
    /**
     * Removes the smallest node from the binary search tree with root at {@code node}
     * @param node root of the tree
     * @return the root of the tree post deletion of the smallest node in this binary search tree.
     */
    default BinarySearchTreeNode<T> deleteSmallest(BinarySearchTreeNode<T> node) {
        if(node.getLeft() == null) return (BinarySearchTreeNode<T>) node.getRight();
        node.setLeft(deleteSmallest((BinarySearchTreeNode<T>) node.getLeft()));
        node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
        return node;
    }

    /**
     * removes the largest node in the binary search tree and returns back the root.
     */
    default void deleteLargest() {
        if(isEmpty()) throw new NoSuchElementException("Empty Tree");
        setRootOfTree(deleteLargest(getRootOfTree()));
    }

    /**
     * Removes the largest node from the binary search tree with root at {@code node}
     * @param node root of the tree
     * @return the root of the tree post deletion of the largest node in this binary search tree.
     */
    default BinarySearchTreeNode<T> deleteLargest(BinarySearchTreeNode<T> node) {
        if(node.getRight() == null ) return (BinarySearchTreeNode<T>) node.getLeft();
        node.setRight(deleteLargest((BinarySearchTreeNode<T>) node.getRight()));
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node;
    }

    /***
     * get largest node in the binary search tree
     * @return largest node
     */
    default BinarySearchTreeNode<T> getLargestNode() {
        if(isEmpty())
            throw new NoSuchElementException("Empty Tree");
        return getLargestNode(getRootOfTree());
    }
    /**
     * Get largest node in binary search tree
     *
     * @param node  root of tree to search.
     * @return Node<T> which represents the largest node.
     */
    default BinarySearchTreeNode<T> getLargestNode(BinarySearchTreeNode<T> node) {
        if (node.getRight() == null) return node;
        else return getLargestNode((BinarySearchTreeNode<T>) node.getRight());
    }

    /**
     * get smallest node in binary search tree
     * @return smallest node
     */
    default BinarySearchTreeNode<T> getSmallestNode() {
        if(isEmpty())
            throw new NoSuchElementException("Empty Tree");
        return getSmallestNode(getRootOfTree());
    }
    /**
     * Get smallest node for tree rooted at node. T
     *
     * @param node root of tree
     * @return Node<T> which represents the smallest node
     */
    default BinarySearchTreeNode<T> getSmallestNode(BinarySearchTreeNode<T> node) {
        if (node.getLeft() == null) return node;
        else return getSmallestNode((BinarySearchTreeNode<T>) node.getLeft());
    }

    /**
     * Verify if the tree is a binary search tree.
     * @return {@code true} if tree is a binary search tree; {@code false} otherwise.
     */
    default boolean isBST() {
        return isBST(getRootOfTree(), null, null);
    }

    default boolean isBST(BinarySearchTreeNode<T> root, T min, T max) {
        if (root == null) return true;
        if (min != null && root.getData().compareTo(min) <= 0) return false;
        if (max != null && root.getData().compareTo(max) >= 0) return false;
        return isBST((BinarySearchTreeNode<T>) root.getLeft(), min, root.getData()) && isBST((BinarySearchTreeNode<T>) root.getRight(), root.getData(), max);
    }

    /**
     * verify if sizing is correct for the tree.
     * @return {@code true} if sizing is correct; {@code false} otherwise.
     */
    default boolean isSizeCorrect() {
        return isSizeCorrect(getRootOfTree());
    }

    default boolean isSizeCorrect(BinarySearchTreeNode<T> root) {
        if(root == null) return true;
        if(root.getSize() != size(root.getLeft()) + size(root.getRight()) + 1) return false;
        return isSizeCorrect((BinarySearchTreeNode<T>) root.getLeft()) && isSizeCorrect((BinarySearchTreeNode<T>) root.getRight());
    }
}

