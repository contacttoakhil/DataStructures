package main.java.ds.trees.bst.balanced;

import main.java.ds.trees.bst.INodeCreator;
import main.java.ds.trees.bst.regular.BinarySearchTree;
import main.java.ds.trees.bst.regular.BinarySearchTreeNode;

import java.util.Objects;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> implements INodeCreator<T>{
    public AVLTree() {
        this.creator = this;
    }

    @Override
    protected BinarySearchTreeNode<T> insert(BinarySearchTreeNode<T> node, T value) {
        AVLTreeNode<T> insertedNode = (AVLTreeNode<T>) super.insert(node, value);
        int leftNodeHeight = insertedNode.getLeftHeight();
        int rightNodeHeight = insertedNode.getRightHeight();
        insertedNode.setHeight(Math.max(leftNodeHeight, rightNodeHeight) + 1);
        return balance(insertedNode);
    }

    @Override
    protected BinarySearchTreeNode<T> delete(BinarySearchTreeNode<T> node, T value) {
        Objects.requireNonNull(node);
        int cmp = value.compareTo(node.getData());
        if      (cmp < 0) node.setLeft(delete((BinarySearchTreeNode<T>) node.getLeft(),  value));
        else if (cmp > 0) node.setRight(delete((BinarySearchTreeNode<T>) node.getRight(), value));
        else {
            if (node.getLeft()  == null) return (BinarySearchTreeNode<T>) node.getRight();
            else if (node.getRight() == null) return (BinarySearchTreeNode<T>) node.getLeft();
            else {
                BinarySearchTreeNode<T> t = node;
                node = getSmallestNode((BinarySearchTreeNode<T>) t.getRight());         // get replacement node
                node.setRight(deleteSmallest((BinarySearchTreeNode<T>) t.getRight()));  // delete duplicate node and get root of the subtree.
                node.setLeft(t.getLeft());
            }
        }
        updateSize(node);
        updateHeight(node);
        return balance((AVLTreeNode<T>) node);
    }

    @Override
    public BinarySearchTreeNode<T> deleteSmallest(BinarySearchTreeNode<T> node) {
        if(node.getLeft() == null) return (BinarySearchTreeNode<T>) node.getRight();
        node.setLeft(deleteSmallest((BinarySearchTreeNode<T>) node.getLeft()));
        updateSize(node);
        updateHeight(node);
        return balance((AVLTreeNode<T>) node);
    }

    @Override
    public boolean contains(T value) {
        Objects.requireNonNull(value);
        AVLTreeNode<T> node =  (AVLTreeNode<T>) search(value);
        return (node != null);
    }

    public int size() {
        return size(getRootOfTree());
    }

    @Override
    protected BinarySearchTreeNode<T> getNewNode(T value) {
        return (new AVLTreeNode<>(value));
    }

    public boolean validate() {
        boolean isBST = isBST();
        if(!isBST) System.err.println("Tree is not BST.");
        boolean isSizeCorrect = isSizeCorrect();
        if(!isSizeCorrect) System.err.println("Sizing of subtrees do not match");
        boolean isAVL = isAVL();
        if(!isAVL) System.err.println("Tree is not AVL");
        return (isBST && isSizeCorrect && isAVL);
    }

    private boolean isAVL() {
        return isAVL((AVLTreeNode<T>) getRootOfTree());
    }

    private boolean isAVL(AVLTreeNode<T> node) {
        if(node == null) return true;
        int balanceFactor = node.getBalanceFactor();
        if(balanceFactor > 1 || balanceFactor < -1) return false;
        return isAVL((AVLTreeNode<T>) node.getLeft()) && isAVL((AVLTreeNode<T>) node.getRight());
    }

    private AVLTreeNode<T> balance(AVLTreeNode<T> node) {
        int balanceFactorOfNode = node.getBalanceFactor();
        if(balanceFactorOfNode < -1) {
            int balanceFactorOfRightNode = ((AVLTreeNode)node.getRight()).getBalanceFactor();
            if(balanceFactorOfRightNode > 0) {
                node.setRight(rotateRight((AVLTreeNode<T>)node.getRight()));
            }
            node = rotateLeft(node);
        }
        else if(balanceFactorOfNode > 1) {
            int balanceFactorOfLeftNode = ((AVLTreeNode)node.getLeft()).getBalanceFactor();
            if(balanceFactorOfLeftNode < 0) {
                node.setLeft(rotateLeft((AVLTreeNode<T>)node.getLeft()));
            }
            node = rotateRight(node);
        }
        return node;
    }

    private void updateHeight(BinarySearchTreeNode<T> node) {
        AVLTreeNode<T> avlTreeNode = (AVLTreeNode<T>) node;
        avlTreeNode.setHeight(Math.max(avlTreeNode.getLeftHeight(), avlTreeNode.getRightHeight()) + 1);
    }

    /**
     * Performs a right rotate on this node.
     *
     *       b                           a
     *      / \                         / \
     *     a   e -> b.rotateRight() -> c   b
     *    / \                             / \
     *   c   d                           d   e
     *
     * @return The root of the sub-tree; the node where this node used to be.
     */
    private AVLTreeNode<T> rotateRight(AVLTreeNode<T> b) {
        AVLTreeNode<T> a = (AVLTreeNode<T>) b.getLeft();
        b.setLeft(a.getRight());
        a.setRight(b);
        a.setSize(b.getSize());
        b.setSize(1 + size(b.getLeft()) + size(b.getRight()));
        updateHeight(b);
        updateHeight(a);
        return a;
    }

    /**
     * Performs a left rotate on this node.
     *
     *     a                              b
     *    / \                            / \
     *   c   b   -> a.rotateLeft() ->   a   e
     *      / \                        / \
     *     d   e                      c   d
     *
     * @return The root of the sub-tree; the node where this node used to be.
     */
    private AVLTreeNode<T> rotateLeft(AVLTreeNode<T> a) {
        AVLTreeNode<T> b = (AVLTreeNode<T>) a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        b.setSize(a.getSize());
        a.setSize(1 + size(a.getLeft()) + size(a.getRight()));
        updateHeight(a);
        updateHeight(b);
        return b;
    }

    @Override
    public BinarySearchTreeNode<T> createNewNode(T value) {
        return (new AVLTreeNode<>(value, 0));
    }
}

//visualization for AVL Tree- https://www.cs.usfca.edu/~galles/visualization/AVLtree.html