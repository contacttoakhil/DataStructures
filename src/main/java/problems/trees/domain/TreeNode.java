package main.java.problems.trees.domain;

// supporitng class
public class TreeNode {
    public int data;
    public TreeNode left, right;

    public TreeNode(int d)
    {
        data = d;
        left = right = null;
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
