package main.java.problems.arrays;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

/***
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 *
 * Note:
 *
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 *
 * https://leetcode.com/problems/continuous-subarray-sum/
 *
 * Solution: As per remainder theorem (a+(n*x))%x is same as (a%x) e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42] and the remainders are [5,1,1,5,0]. We got remainder 5 at index 0 and at index 3. That means, in between
 * these two indexes we must have added a number which is multiple of the k.
 */
public class LC523ContinuousSubArraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> partialSumMap = new HashMap<>(){{put(0,-1);}};
        int sum = 0;
        for (int i=0;i<nums.length;i++) {
            sum += nums[i];
            if (k != 0) sum = sum % k;
            Integer prev = partialSumMap.get(sum);
            if(prev == null) {
                partialSumMap.put(sum, i);
                continue;
            }
            if (i - prev > 1) return true; // prev not null
        }
        return false;
    }

    public static void main(String[] args) {
        LC523ContinuousSubArraySum continuousSubArraySum = new LC523ContinuousSubArraySum();
        System.out.println(continuousSubArraySum.checkSubarraySum(new int[] {23,2,4,6,7} , 6));
    }
}
