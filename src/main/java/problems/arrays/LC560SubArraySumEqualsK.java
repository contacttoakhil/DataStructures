package main.java.problems.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class LC560SubArraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> partialSumMap = new HashMap<>();
        int sum = 0, result=0;
        partialSumMap.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if (partialSumMap.containsKey(sum - k)) {
                result += partialSumMap.get(sum - k);
            }

            partialSumMap.put(sum, partialSumMap.getOrDefault(sum,0)+1);
        }
        return result;
    }

    public static void main(String[] args) {
        LC560SubArraySumEqualsK subArraySumEqualsK = new LC560SubArraySumEqualsK();
        System.out.println(subArraySumEqualsK.subarraySum(new int[]{1,2,3,0,3,2,6}, 6));
    }
}
