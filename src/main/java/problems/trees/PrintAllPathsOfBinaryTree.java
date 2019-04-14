package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

public class PrintAllPathsOfBinaryTree {
    TreeNode root;

    static void printPathsRecur(TreeNode node, int[] path, int pathLen) {
        if (node == null) return;
        path[pathLen++] = node.data; // append node
        if (isLeaf(node)){       // prints the path array from 0 to pathLen
            printArray(path, pathLen); return;
        }
        printPathsRecur(node.left, path, pathLen);     // try both subtrees
        printPathsRecur(node.right, path, pathLen);
    }

    private static boolean isTargetNode(TreeNode node, TreeNode target) {
        return (node == target);
    }

    static void printPathsRecurTarget(TreeNode node, int[] path, int pathLen, TreeNode target) {
        if (node == null) return;
        path[pathLen++] = node.data; // append node
        if (isTargetNode(node, target)){       // prints the path array from 0 to pathLen
            printArray(path, pathLen); return;
        }
        printPathsRecur(node.left, path, pathLen);     // try both subtrees
        printPathsRecur(node.right, path, pathLen);
    }

    static private void printArray(int[] path, int pathLen) {
        int i;
        for (i = 0; i < pathLen; i++)
        {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    private static boolean isLeaf(TreeNode node) {
        return (node.left == null && node.right == null);
    }

    public static void main(String args[])
    {
        int[] paths = new int[1000];

        PrintAllPathsOfBinaryTree tree = new PrintAllPathsOfBinaryTree();
        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(8);
        tree.root.right = new TreeNode(2);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(2);

        /* Let us test the built tree by printing Insorder traversal */
        //printPathsRecur(tree.root, paths, 0);
        printPathsRecurTarget(tree.root, paths, 0, new TreeNode(8));

    }
}


