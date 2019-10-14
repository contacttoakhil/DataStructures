package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.chrono.JapaneseEra.values;

public class LC987VerticalOrderTraversalOfBinaryTree {

    public List<List<Integer>> verticalTraversal(TreeNode2 root) {
        verticalTrav( root,  0);
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> val : map.values()) {
            Collections.sort(val);
            result.add(val);
        }
        return result;
    }

    TreeMap<Integer, List<Integer>> map = new TreeMap<>();
    void verticalTrav(TreeNode2 root, int hD) {
        if (root == null) { return; }
        verticalTrav(root.left, hD - 1);
        List<Integer> list = (map.get(hD) == null) ? map.put(hD,new ArrayList<Integer>()) : map.get(hD);
        list.add(root.data);
        verticalTrav(root.right, hD + 1);
    }

    public static void main(String[] args) {
        LC987VerticalOrderTraversalOfBinaryTree verticalOrderTraversalOfBinaryTree = new LC987VerticalOrderTraversalOfBinaryTree();

    }
}

class TreeNode2 implements Comparable<TreeNode2> {
    public int data;
    public TreeNode2 left, right;

    public TreeNode2 (int d)
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

    @Override
    public int compareTo(TreeNode2 o) {
        return o.data - this.data;
    }
}
