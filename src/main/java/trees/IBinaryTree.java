package main.java.trees;

import main.java.queue.ArrayQueue;
import main.java.queue.IQueue;

import java.util.Objects;
import java.util.StringJoiner;

public interface IBinaryTree<T extends Comparable<T>> extends Iterable<T>{

    /**
     * This method returns root of the tree.
     * @return root of tree.
     */
    BinaryTreeNode<T> getRootOfTree();

    /**
     * Adds value to the tree.
     * @param value to add to tree
     */
    void insert(T value);

    /**
     * Removes the first occurrence of the value in tree.
     * @param value to remove from tree
     */
    void delete(T value);

    /**
     * Clear the tree.
     */
    void clear();

    /**
     * Verifies if the tree contains the value.
     *
     * @param value to locate in the tree.
     * @return {@code true} if tree contains value; {@code false} otherwise
     */
    boolean contains(T value);

    /**
     * Get number of nodes in the tree.
     *
     * @return number of nodes in the tree.
     */
    int size();

    /**
     * Gets the size of tree rooted at node
     * @param node root of the tree whose size needs to find.
     * @return size of the tree rooted at node
     */
    default int size(BinaryTreeNode<T> node) {
        return ( node == null ) ? 0 : node.getSize();
    }

    /**
     * Checks if the tree is empty i.e. size is zero?
     * @return {@code true} if tree is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Validate the tree according to the invariants.
     *
     * @return {@code true} if the tree is valid; {@code false} otherwise
     */
    boolean validate();

    /**
     * Returns the height of the tree.
     *
     * @return the height of the tree (a 1-node tree has height 0)
     */
    default int height() {
        return height(getRootOfTree());
    }

    private int height(BinaryTreeNode<T> root) {
        if (root == null) return -1;
        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }

    /**
     * prints the preorder traversal for the tree.
     */
    default void preorder() {
        preorder(getRootOfTree());
    }

    /**
     * prints the preorder traversal of the tree.
     * @param root is root of the tree
     */
    default void preorder(BinaryTreeNode<T> root) {
        if (root != null)
        {
            System.out.print(root.getData() +" ");
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }

    /**
     * prints the postorder traversal for the tree.
     */
    default void postorder() {
        postorder(getRootOfTree());
    }

    /**
     * prints the postorder traversal for the tree.
     * @param root of the tree
     */
    default void postorder(BinaryTreeNode<T> root) {
        if (root != null)
        {
            postorder(root.getLeft());
            postorder(root.getRight());
            System.out.print(root.getData() +" ");
        }
    }

    /**
     * prints the inorder traversal for the tree.
     */
    default void inorder() {
        inorder(getRootOfTree());
    }

    /**
     * prints the inorder traversal for the tree.
     * @param root of the tree
     */
    default void inorder(BinaryTreeNode<T> root) {
        if (root != null)
        {
            inorder(root.getLeft());
            System.out.print(root.getData() +" ");
            inorder(root.getRight());
        }
    }

    /**
     * prints the level order traversal for the tree.
     */
    default void levelorder() {
        levelorder(getRootOfTree());
    }

    /**
     * prints the level order traversal for the tree rooted.
     * @param root of the tree
     */
    default void levelorder(BinaryTreeNode<T> root) {
        Objects.requireNonNull(root);
        IQueue<BinaryTreeNode<T>> queue = new ArrayQueue<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            System.out.print(node.getData() +" ");
            if(node.getLeft() != null)
                queue.offer(node.getLeft());
            if(node.getRight() != null)
                queue.offer(node.getRight());
        }
    }

    /**
     * Prints the comma separated list of elements onto this stack.
     */
    default void print()
    {
        StringJoiner joiner = new StringJoiner(",");
        for(T e : this) {
            joiner.add(Objects.toString(e));
        }
        System.out.println("Tree content:" + joiner);
    }

    /**
     * returns the string representation of the tree.
     * @param root of the tree
     * @return string representation
     */
    default String printTree(IBinaryTree<T> root) {
        return BinarySearchTreePrinter.getString(root);
    }

    class BinarySearchTreePrinter {
        static <T extends Comparable<T>> String getString(IBinaryTree<T> tree) {
            if (tree.getRootOfTree() == null)
                return "Tree has no nodes.";
            return toString(new StringBuilder(), true, new StringBuilder(), tree.getRootOfTree());
        }

        private static <T extends Comparable<T>> String toString(StringBuilder prefix, boolean isTail, StringBuilder sb, BinaryTreeNode<T> current) {
            if(current.getRight()!=null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, current.getRight());
            }
            // In some cases terminal nodes may have null data e.g. Red-Black Tree. To avoid NPE substitute X.
            if(current.getData() == null ) {
                sb.append(prefix).append(isTail ? "└── " : "┌── ").append("X").append("\n");
            } else {
                sb.append(prefix).append(isTail ? "└── " : "┌── ").append(current.getData().toString()).append("\n");
            }

            if(current.getLeft() != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, current.getLeft());
            }
            return sb.toString();
        }
    }
}
