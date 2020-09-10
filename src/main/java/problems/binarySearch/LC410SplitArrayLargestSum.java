package main.java.problems.binarySearch;

/***
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */

import java.util.Arrays;

public class LC410SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        long lo = Arrays.stream(nums).max().getAsInt();
        long hi = Arrays.stream(nums).sum();
        if (m == 1) return (int)hi;
        while (lo <= hi) {
            long mid = (lo + hi) >>> 1;
            if (valid(mid, nums, m))   hi = mid - 1;
            else                       lo = mid + 1;
        }
        return (int)lo;
    }
    public boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for(int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC410SplitArrayLargestSum splitArrayLargestSum = new LC410SplitArrayLargestSum();
        int result = splitArrayLargestSum.splitArray(new int[]{7,2,5,10,8},2);
        System.out.println(result);
    }
}
