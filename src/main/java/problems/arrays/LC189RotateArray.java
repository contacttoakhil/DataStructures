package main.java.problems.arrays;

/***
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Solution:
 * [1,2,3,4,5,6,7] k=3  =>  [5,6,7,1,2,3,4]
 *
 * a) reverse entire array      => [7,6,5,4,3,2,1]
 * b) reverse first k entries   => [5,6,7,4,3,2,1]
 * c) reverse last n-k entries    => [5,6,7,1,2,3,4]
 *
 * https://leetcode.com/problems/rotate-array/
 */
public class LC189RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;  // But why? If k is more than length of nums then it will take care of rotations actually needed.
        reverseArray(nums,0, nums.length);
        reverseArray(nums,0, k-1);
        reverseArray(nums, k, nums.length-1);
    }
    private void reverseArray(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++; end--;
        }
    }
}
