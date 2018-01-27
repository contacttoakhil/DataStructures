package main.java.problems.trees;

import main.java.problems.trees.domain.BinaryTree;
import main.java.problems.trees.domain.TreeNode;

/**
 *
 * A sum tree is a Binary Tree where the value of a node is equal to sum of the nodes present in its left subtree and right subtree e.g. a node with value 10 has kids 4 and 6. An empty tree is sum tree and sum of an empty tree can be considered as 0.
 * A leaf node is also considered as sum tree.
 *
 *  The method isSumTree returns true or false based on whether tree is sum tree or not.
 *
 *                          26
 *                       /   \
 *                      10     3             <-  A SumTree is a Binary Tree where the value of a node is equal to sum of the nodes present in its left subtree and right subtree.
 *                    /    \     \           <-  An empty tree is SumTree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.
 *                   4     6     3
 *
 *  The above solution can be optimized if we observe the following facts:
 *  For a leaf node the sum (right subtree + left subtree) is its value itself.
 *	For non-leaf node (e.g. 10) it is double i.e. 10 + 4 + 6, assuming the tree rooted at this node is also a sum tree.
 *
 *  The method isSumTreeOpt uses this optimization.
 *
 */
public class CheckSumBinaryTree {

    private boolean isSumTree(TreeNode node) {
        if(node == null || isLeaf(node)) return true;
        int ls = sum(node.left);   int rs = sum(node.right);
        return node.data == ls + rs && isSumTree(node.left) && isSumTree(node.right);
    }

    private int sum(TreeNode node) {
        if (node == null)
            return 0;
        return sum(node.left) + node.data + sum(node.right);
    }

    private boolean isLeaf(TreeNode node) {
        return (node == null) || (node.left == null && node.right == null);
    }

    private boolean isSumTreeOpt(TreeNode node) {
        if(node == null || isLeaf(node))  return true;
        int ls = sumOpt(node.left);        int rs = sumOpt(node.right);
        return node.data == ls + rs && isSumTreeOpt(node.left) && isSumTreeOpt(node.right);
    }

    private int sumOpt(TreeNode node) {
        int sum = 0;
        if (node == null)
            return sum;
        sum = sum + node.data;
        if(!isLeaf(node))
            sum = sum + node.data;
        return sum;
    }

    public static void main(String[] args) {
        CheckSumBinaryTree cst = new CheckSumBinaryTree();
        TreeNode root = getBinaryTree();
        boolean sumTreeOne = cst.isSumTree(root);
        boolean sumTreeTwo = cst.isSumTreeOpt(root);
        System.out.println("Given tree is a sum tree? " + sumTreeOne + ":" + sumTreeTwo);
    }

    private static TreeNode getBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(26);
        tree.root.left = new TreeNode(10);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(6);
        tree.root.right.right = new TreeNode(3);
        return tree.root;
    }

}

