package main.java.problems.dp;

import main.java.problems.trees.domain.TreeNode;

/***
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * https://leetcode.com/problems/house-robber-iii/
 *
 * Solution:
 * The idea of the DP solution is for each node, maintain two fields: the max value if rob the root, and the max value without robbing the root. Then we can use a bottom-up DP to avoid the repeated calculations.
 */
public class LC337HouseRobberThree {

    public int rob(TreeNode root) {
        if(root == null)
            return 0;

        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    public int[] helper(TreeNode root){
        if(root == null){
            int[] result = {0, 0};
            return result;
        }

        int[] result = new int[2];
        int[] left = helper(root.left);
        int[] right = helper (root.right);

        // result[0] is when root is selected, result[1] is when not.
        result[0] = root.data + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }
}
