package main.java.problems.arrays;

import java.util.HashMap;
import java.util.Map;

/***
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * Follow Up:
 * Can you do it in O(n) time?
 *
 * Hint: The problem is equal to: find out a range from i to j, in which the sum (nums[i], ..., nums[j]) = k. What is the maximal range?
 *
 */
public class LC325MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0)      return 0;
        int sum = 0, result = 0;
        Map<Integer, Integer> partialSumMap = new HashMap<>();
        partialSumMap.put(0, -1); // IMPOARTANT
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (partialSumMap.containsKey(sum - k))
                result = Math.max(result, i - partialSumMap.get(sum - k));
            partialSumMap.putIfAbsent(sum, i);
        }
        return result;
    }

    public static void main(String[] args) {
        LC325MaximumSizeSubarraySumEqualsk maximumSizeSubarraySumEqualsk = new LC325MaximumSizeSubarraySumEqualsk();
        System.out.println(maximumSizeSubarraySumEqualsk.maxSubArrayLen(new int[]{1, -1, 5, -2, 3},3));  // 4
        System.out.println(maximumSizeSubarraySumEqualsk.maxSubArrayLen(new int[]{-2, -1, 2, 1},1));  // 2
    }
}
