package main.java.problems.arrays;

import java.util.Arrays;

/***
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 *
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 *
 * a) You must not modify the array (assume the array is read only).
 * b) You must use only constant, O(1) extra space.
 * c) Your runtime complexity should be less than O(n2).
 * d) There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * Hint: Two ways: using hashset and using hare and tortoise
 */
public class LC287FindTheDuplicateNumber {

    public int findDuplicateUsingSet(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }

    // Floyd's Tortoise and Hare (Cycle Detection)
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }
}
