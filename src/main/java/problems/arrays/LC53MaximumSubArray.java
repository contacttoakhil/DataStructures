package main.java.problems.arrays;

/***
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * https://leetcode.com/problems/maximum-subarray/
 *
 *
 * Variants
 * Max sum for non-contiguous subarray: https://stackoverflow.com/questions/4487438/maximum-sum-of-non-consecutive-elements
 * Max sum for subsequences: https://www.hackerrank.com/challenges/maxsubarray/problem
 * We define subsequence as any subset of an array. We define a subarray as a contiguous subsequence in an array.
 *
 * maxSumArray:
 *
 * maxSumSubSequence:
 * The logic works as follows:
 * a) each positive element adds to max-sum
 * b) each negative element does not add so ignore
 * c) zero makes no difference.
 * But it works if we have at least one positive or zero element, is all negative then we return the maximum (max) negative value i.e. one with smallest absolute value. We want to have max positive sum.
 *
 */
public class LC53MaximumSubArray {

    public int maxSumArray(int[] nums) {
        int max = nums[0];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);
            max = Math.max(max, sum[i]);
        }

        return max;
    }

    public int maxSumArrayNonContiguous(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

    public int maxSumSubSequence(int[] nums) {
        int max = nums[0], totalPosSum = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if(nums[i] > 0) totalPosSum = totalPosSum + nums[i];
        }
        return (max >= 0) ? totalPosSum : max;
    }

    public static void main(String[] args) {
        LC53MaximumSubArray maximumSubArray = new LC53MaximumSubArray();
        System.out.println(maximumSubArray.maxSumArray(new int[]{2, -1, 2, 3, 4, -5}));
        System.out.println(maximumSubArray.maxSumSubSequence(new int[]{2, -1, 2, 3, 4, -5}));
        System.out.println(maximumSubArray.maxSumArrayNonContiguous(new int[]{2, -1, 2, 3, 4, -5}));

        System.out.println(maximumSubArray.maxSumArray(new int[]{1,2,3,8,9}));
        System.out.println(maximumSubArray.maxSumSubSequence(new int[]{1,2,3,8,9}));
        System.out.println(maximumSubArray.maxSumArrayNonContiguous(new int[]{1,2,3,8,9}));

        System.out.println(maximumSubArray.maxSumArray(new int[]{-2, -3, -1, -4, -6}));
        System.out.println(maximumSubArray.maxSumSubSequence(new int[]{-2, -3, -1, -4, -6}));
        System.out.println(maximumSubArray.maxSumArrayNonContiguous(new int[]{-2, -3, -1, -4, -6}));
    }
}
