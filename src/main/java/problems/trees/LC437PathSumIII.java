package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Hint: Two ways to solve this:
 * a) Divide and Conquer
 * b) Prefix Sum + Backtracking
 *
 * https://leetcode.com/problems/path-sum-iii/
 */
public class LC437PathSumIII {

    // Divide and Conquer
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        // 取当前节点的路径 + 不取当前节点的路径(左孩子，右孩子)
        return countPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int countPath(TreeNode root, int target) {
        int count = 0;
        if (root == null) {
            return count;
        }
        if (root.data == target) {
            count++;
        }

        count += countPath(root.left, target - root.data);
        count += countPath(root.right, target - root.data);
        return count;
    }

    // Backtracking
    public int pathSumBT(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  //Default sum = 0 has one count
        return backtrack(root, 0, sum, map);
    }

    public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
        if(root == null)
            return 0;
        sum += root.data;
        int res = map.getOrDefault(sum - target, 0);    //See if there is a subarray sum equals to target
        map.put(sum, map.getOrDefault(sum, 0)+1);
        //Extend to left and right child
        res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
        map.put(sum, map.get(sum)-1); //\\Remove the current node so it wont affect other path
        return res;
    }
}
