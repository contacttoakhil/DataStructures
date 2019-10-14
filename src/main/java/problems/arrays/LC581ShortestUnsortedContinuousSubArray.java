package main.java.problems.arrays;

import java.util.Arrays;

/***
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 *
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 *
 * Hint: We can sort a copy of the given array nums, say snums. Then, if we compare the elements of nums and snums, we can determine the leftmost and rightmost elements which mismatch. The subarray lying between them is, then, the required shorted unsorted
 * subarray.
 *
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 *
 * Solution:
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/#approach-5-without-using-extra-space-accepted
 *
 * The idea behind this method is that the correct position of the minimum element in the unsorted subarray helps to determine the required left boundary. Similarly, the correct position of the maximum element in the unsorted subarray helps to
 * determine the required right boundary.
 *
 * Thus, firstly we need to determine when the correctly sorted array goes wrong. We keep a track of this by observing rising slope starting from the beginning of the array. Whenever the slope falls, we know that the unsorted array has surely started.
 * Thus, now we determine the minimum element found till the end of the array nums given by min.
 *
 * Similarly, we scan the array nums in the reverse order and when the slope becomes rising instead of falling, we start looking for the maximum element till we reach the beginning of the array, given by max. Then, we traverse over nums and determine
 * the correct position of min and max by comparing these elements with the other array elements. e.g. To determine the correct position of min, we know the initial portion of nums is already sorted. Thus, we need to find the first element which is just
 * larger than min. Similarly, for max's position, we need to find the first element which is just smaller than max searching in nums backwards.
 *
 *
 */
public class LC581ShortestUnsortedContinuousSubArray {

    public int findUnsortedSubarray(int[] nums) {
        if(nums == null) return 0;
        if(nums.length == 0 || nums.length == 1) return 0;
        int end = scanFromLeft(nums);
        int begin = scanFromRight(nums);
        return end - begin + 1;
    }

    /***
     * Ideally when we start from left every next element should be greater than the max element seen so far and if it is not then it means unsorted array has started. So we track max from the beginning of the array and when we find an element smaller than
     * max it means it is misplaced. So we find the last element which is smaller than the last seen max from the lft side and mark it as end.
     */
    private int scanFromLeft(int[] nums) {
        Integer max = Integer.MIN_VALUE;
        int end = -2;
        for(int i = 0; i < nums.length; i ++){
            max = Math.max(max, nums[i]);
            if(nums[i] < max)
                end = i;
        }
        return end;
    }

    private int scanFromRight(int[] nums) {
        Integer min = Integer.MAX_VALUE;
        int begin = -1;
        for(int i = nums.length - 1; i >= 0; i --){
            min = Math.min(min, nums[i]);
            if(nums[i] > min)
                begin = i;
        }
        return begin;
    }

    public static void main(String[] args) {
        LC581ShortestUnsortedContinuousSubArray shortestUnsortedContinuousSubArray = new LC581ShortestUnsortedContinuousSubArray();
        System.out.println(shortestUnsortedContinuousSubArray.findUnsortedSubarray(new int[] {2, 6, 4, 8, 10, 9, 15}));
    }
}
