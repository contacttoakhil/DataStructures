package main.java.problems.dp;

import java.util.Arrays;

/***
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 *
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
public class LC303RangeSumQuery1DImmutable {
    int[] dp;

    public LC303RangeSumQuery1DImmutable(int[] nums) {
        this.dp = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            dp[i+1] = dp[i] + nums[i];
        }
        //System.out.println(Arrays.toString(dp));
    }

    public int sumRange(int i, int j) {
        return dp[j+1] - dp[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        LC303RangeSumQuery1DImmutable rangeSumQuery1DImmutable = new LC303RangeSumQuery1DImmutable(nums);
        System.out.println(rangeSumQuery1DImmutable.sumRange(0,2));  // 1
    }
}
