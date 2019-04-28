package main.java.problems.dp;

/***
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Hint: It's a 0/1 backpack problem. So we first sum up the numbers in the array. The target is to find out if choosing some numbers from the array, can we get it's sum equal to sum/2? As we can see, it's a classic 0/1 backpack problem.
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 */
public class LC416PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int sum = sumOfElements(nums);
        if (sum % 2 == 1)
            return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int t = target; t > 0; t--)
            for(int num : nums)
                dp[t] = t < num ? dp[t] : dp[t] | dp[t-num];

        for (int num : nums)
            for (int t = target; t > 0; t--)
                dp[t] = (t < num) ? dp[t] : dp[t] | dp[t - num];
        return dp[target];
    }

    private int sumOfElements(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
