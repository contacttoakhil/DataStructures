package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC257BinaryTreePaths {

    private List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return result;
        getRootToLeafPaths(root, root.data + "");
        return result;
    }

    private void getRootToLeafPaths(TreeNode root, String current) {
        if(isLeaf(root)) {
            result.add(current);
            return;
        }
        if(root.left != null)
            getRootToLeafPaths(root.left, current + "->" + root.left.data);
        if(root.right != null)
            getRootToLeafPaths(root.right, current + "->" + root.right.data);
    }

    private boolean isLeaf(TreeNode node) {
        return (node.left == null && node.right == null);
    }
}
