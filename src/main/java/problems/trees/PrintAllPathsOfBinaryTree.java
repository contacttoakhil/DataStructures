package main.java.problems.trees;

public class PrintAllPathsOfBinaryTree {
    Node root;

    static void printPathsRecur(Node node, int[] path, int pathLen) {
        if (node == null) return;
        path[pathLen++] = node.data; // append node
        if (isLeaf(node)){       // prints the path array from 0 to pathLen
            printArray(path, pathLen); return;
        }
        printPathsRecur(node.left, path, pathLen);     // try both subtrees
        printPathsRecur(node.right, path, pathLen);
    }

    private static boolean isTargetNode(Node node, Node target) {
        return (node == target);
    }

    static void printPathsRecurTarget(Node node, int[] path, int pathLen, Node target) {
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

    private static boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

    public static void main(String args[])
    {
        int[] paths = new int[1000];

        PrintAllPathsOfBinaryTree tree = new PrintAllPathsOfBinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(8);
        tree.root.right = new Node(2);
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(2);

        /* Let us test the built tree by printing Insorder traversal */
        //printPathsRecur(tree.root, paths, 0);
        printPathsRecurTarget(tree.root, paths, 0, new Node(8));

    }
}
