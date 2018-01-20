package main.java.problems.trees;

import main.java.ds.trees.BinaryTreeNode;
import main.java.ds.trees.bst.regular.BinarySearchTree;
import main.java.ds.trees.bst.regular.BinarySearchTreeNode;

/**
 * The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * @param <T> type parameter
 */
public class LowestCommonAncestor<T extends Comparable<T>> {

    /**
     * Returns the lowest common ancestor for binary tree and node value one and two
     * @param root of the binary tree
     * @param nodeOne
     * @param nodeTwo
     * @return
     */
    public BinaryTreeNode<T> lowestCommonAncestorForBinaryTree(BinaryTreeNode<T> root , BinaryTreeNode<T> nodeOne, BinaryTreeNode<T> nodeTwo) {
        if(root == null) return null;
        if(root.equals(nodeOne) || root.equals(nodeTwo)) {
            // At least one match? no need to continue as this is LCA
            return root;
        }
        BinaryTreeNode<T> left = lowestCommonAncestorForBinaryTree(root.getLeft(), nodeOne, nodeTwo);
        BinaryTreeNode<T> right = lowestCommonAncestorForBinaryTree(root.getRight(), nodeOne, nodeTwo);
        if (left != null && right != null) {
            return root;  // nodes are each on a separate branch
        }
        // either one node is on one branch or none found in any of branches
        return left != null ? left : right;

    }

    /**
     * Returns the lowest common ancestor for bst and node value one and two
     * @param node root of bst
     * @param one item at node one
     * @param two item at node two
     * @return lca of two nodes
     */
    public BinarySearchTreeNode<T> leastCommonAncestorForBST(BinarySearchTreeNode<T> node, T one, T two) {
        if( node == null ) return null;
        if( node.getData().compareTo(one)>0 && node.getData().compareTo(two)>0 )
            return leastCommonAncestorForBST((BinarySearchTreeNode<T>) node.getLeft(), one, two);
        if( node.getData().compareTo(one)<0 && node.getData().compareTo(two)<0 )
            return leastCommonAncestorForBST((BinarySearchTreeNode<T>) node.getRight(), one, two);
        return node; // nodes are in separate branches
    }

    public static void main(String[] args) {
        /*
                        20
                       /  \
                     8    22
                    / \
                   4  12
                     /  \
                   10   14
         */
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(20);        bst.insert(8);        bst.insert(22);        bst.insert(4);        bst.insert(12);        bst.insert(10);        bst.insert(14);        bst.printTree();
        LowestCommonAncestor<Integer> lca = new LowestCommonAncestor<>();
        System.out.println(lca.leastCommonAncestorForBST(bst.getRootOfTree(), 10,14));    // 12
        System.out.println(lca.leastCommonAncestorForBST(bst.getRootOfTree(), 14,8));     // 8
        System.out.println(lca.leastCommonAncestorForBST(bst.getRootOfTree(), 10,22));    // 20
    }
}
