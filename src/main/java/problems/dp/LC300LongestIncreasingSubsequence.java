package main.java.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 *
 * Solution:
 * lengthOfLIS:
 * Let max[i] represent the length of the longest increasing subsequence so far.
 * If any element at j before i is smaller than nums[i], then max[i] = max(max[i], max[j]+1).
 * O(n^2)
 *
 * lengthOfLISImprovised:
 * for each num in nums
 *    if(list.size()==0)
 *       add num to list
 *    else if(num > last element in list)
 *       add num to list
 *    else
 *       replace the element in the list which is the smallest but bigger than num
 * O(nlogn)
 */
public class LC300LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {    // O(NlogN) using Dynamic Programming with Binary Search
        int[] dp = new int[nums.length];    // By default it would be filled with zero (initial element).
        int len = 0 ; // element from idx zero to len would be sorted in array dp.

        for(int num : nums) {
            int idx = getInsertionPoint(dp, len, num);
            dp[idx] = num;
            // Two scenarios:
            // a) Existing element is replaced and no change in length
            // b) Element gets added at last i.e. at len and len should be increased by one.
            if( idx == len)
                len++;
        }
        return len;  // In dp elements from 0 to len-1 make LIS.
    }

    private int getInsertionPoint(int[] dp, int len, int num) {
        int idx = Arrays.binarySearch(dp, 0, len, num);
        // If element does not exists then binarySearch would return -i-1 or -(i+1) as insertion point, e.g.
        // e.g. if dp = [0,8,0,0,0], len = 2, num=4 then ideally 4 should be inserted at second place => [0,4,8,....] so method would return -2.
        if(idx < 0)
            idx = - (idx + 1); // correct idx to make it positive.
        return idx;
    }

    public int lengthOfLISUsingDP(int[] nums) {     // O(N^2) using Dynamic Programming
        if(nums==null || nums.length==0)
            return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for(int i=0; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[i]>nums[j]){
                    dp[i]= Math.max(dp[i], dp[j]+1);

                }
            }
            result = Math.max(dp[i], result);
        }
        return result;
    }

    public static void main(String[] args) {
        LC300LongestIncreasingSubsequence longestIncreasingSubsequence = new LC300LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(new int[] {0, 8, 4, 12, 2}));           // 3
        System.out.println(longestIncreasingSubsequence.lengthOfLISUsingDP(new int[] {0, 8, 4, 12, 2}));    // 3
    }
}
