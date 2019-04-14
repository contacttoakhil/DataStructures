package main.java.problems.arrays;

import java.util.Arrays;

/***
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Note:
 * a) You must do this in-place without making a copy of the array.
 * b) Minimize the total number of operations.
 *
 * https://leetcode.com/problems/move-zeroes/
 */
public class LC283MoveZeroes {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int num : nums)
            if (num != 0) nums[j++] = num;
        Arrays.fill(nums, j, nums.length, 0);
    }

}
