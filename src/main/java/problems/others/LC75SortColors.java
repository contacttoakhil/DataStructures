package main.java.problems.others;

/***
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * a) A rather straight forward solution is a two-pass algorithm using counting sort.
 *    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * b) Could you come up with a one-pass algorithm using only constant space?
 *
 * https://leetcode.com/problems/sort-colors/
 */
public class LC75SortColors {

    public void sortColors(int[] nums) {
        int start = 0, mid = 0, end = nums.length-1;
        int pivot = 1;
        while (mid <= end) {
            if(nums[mid] < pivot)               // current element is 0
                swap(nums, start++, mid++);
            else if (nums[mid] > pivot)     	// current element is 2
                swap(nums, mid, end--);
            else						        // current element is 1
                ++mid;
        }
    }

    void swap(int[] nums, int indexOne, int indexTwo) {
        int temp = nums[indexOne];
        nums[indexOne] = nums[indexTwo];
        nums[indexTwo] = temp;
    }

}
