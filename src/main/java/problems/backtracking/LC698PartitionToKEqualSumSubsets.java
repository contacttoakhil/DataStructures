package main.java.problems.backtracking;

import java.util.Arrays;

/***
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class LC698PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % k != 0 || maxNum > sum / k) return false;
        return backtrack(nums, k, sum / k, 0, new boolean[nums.length], 0);
    }

    private boolean backtrack(int[] nums, int k, int targetSum, int curSum, boolean[] visited, int start) {
        if (k == 0) return true; // Outer base case for number of subsets.
        else if (curSum > targetSum) return false; // Inner base case for current subset sum.
        else if (curSum == targetSum) return backtrack(nums, k - 1, targetSum, 0, visited, 0); // Inner base case for current subset sum.

        for (int i = start; i < nums.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            if (backtrack(nums, k, targetSum, curSum + nums[i], visited, i + 1)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        LC698PartitionToKEqualSumSubsets partitionToKEqualSumSubsets = new LC698PartitionToKEqualSumSubsets();
        System.out.println(partitionToKEqualSumSubsets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
}
