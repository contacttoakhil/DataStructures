package main.java.problems.arrays;

import java.util.Arrays;

/***
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 */
public class LC259Problem3SumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        int result = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++) {
            int start = i+1, end = nums.length-1;
            while(start < end) {
                if(nums[i] + nums[start] + nums[end] < target) {
                    result = result + (end - start);
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

}
