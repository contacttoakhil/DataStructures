package main.java.trees.bst.balanced;

import main.java.trees.bst.regular.BinarySearchTreeNode;

public class AVLTreeNode<T extends Comparable<T>> extends BinarySearchTreeNode<T>{
    private static final int NULL_NODE_HEIGHT = -1; // height for a null node.
    protected int height;

    public AVLTreeNode(T data) {
        super(data);
        this.height = 1;
    }

    public AVLTreeNode(T data, int height) {
        super(data);
        this.height = height;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get height of the left child of node.
     * @return NULL_NODE_HEIGHT(-1) if no left child else height of left child.
     */
    public int getLeftHeight() {
        if(!hasLeftChild()) return NULL_NODE_HEIGHT;
        return ((AVLTreeNode)getLeft()).getHeight();
    }

    public int getRightHeight() {
        if(!hasRightChild()) return NULL_NODE_HEIGHT;
        return ((AVLTreeNode)getRight()).getHeight();
    }

    public int getBalanceFactor() {
        return getLeftHeight() - getRightHeight();
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "height=" + height +
                ", data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", size=" + size +
                '}';
    }
}
