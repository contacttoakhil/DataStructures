package main.java.ds.trees;

public class BinaryTreeNode<T extends Comparable<T>>  {
    protected T data;
    protected BinaryTreeNode<T> left, right;
    private static final int NULL_NODE_SIZE = 0; // size for a null node.
    protected int size = NULL_NODE_SIZE;   // number of nodes in subtree rooted at this node.

    public BinaryTreeNode(T data) {
        this(data, null, null);
        this.size = 1;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public boolean isLeafNode() {
        return (left == null && right == null);
    }

    public boolean isHalfNode() {
        return hasOnlyOneChild();
    }

    public boolean isFullNode() {
        return  hasBothChild();
    }

    public boolean hasOnlyOneChild() {
        return (hasOnlyLeftChild() || hasOnlyRightChild());
    }

    public boolean hasOnlyLeftChild() {
        return (hasLeftChild() && this.right == null);
    }

    public boolean hasOnlyRightChild() {
        return (hasRightChild()  && this.left == null);
    }

    public boolean hasRightChild() {
        return this.right != null;
    }

    public boolean hasLeftChild() {
        return this.left != null;
    }

    public boolean isLeftChild(BinaryTreeNode<T> childToCheck) {
        return ( this.hasLeftChild() && this.left.equals(childToCheck));
    }

    public void setLeftChild(BinaryTreeNode<T> childToSet) {
        this.setLeft(childToSet);
    }

    public boolean isRightChild(BinaryTreeNode<T> childToCheck) {
        return ( this.hasRightChild() && this.right.equals(childToCheck));
    }

    public void setRightChild(BinaryTreeNode<T> childToSet) {
        this.setRight(childToSet);
    }

    public boolean hasBothChild() {
        return (this.right != null && this.left != null);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLeftSize() {
        if(!hasLeftChild()) return NULL_NODE_SIZE;
        return getLeft().getSize();
    }

    public int getRightSize() {
        if(!hasRightChild()) return NULL_NODE_SIZE;
        return getRight().getSize();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
