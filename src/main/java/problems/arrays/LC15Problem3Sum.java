package main.java.problems.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * https://leetcode.com/problems/3sum/
 */
public class LC15Problem3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3) return result;
        Arrays.sort(nums); // sort them so that they are in non-decreasing order.

        for (int i = 0; i < nums.length-2; i++) { // length -2 as we need 3 nums
            if( i == 0 || nums[i] > nums[i-1]) {
                int start = i + 1;          // move start onwards based on tupleSum
                int end = nums.length - 1;  // move end backwards based on tupleSum
                while (start < end) {
                    int tupleSum = nums[i]+nums[start]+nums[end];
                    if(tupleSum == 0) {
                        populateTuple(result, nums[i], nums[start], nums[end]); // capture result
                        start++; end--;
                        while (start < end && nums[start] == nums[start - 1]) start++;  // ignore duplicates from star onwards
                        while (start < end && nums[end] == nums[end + 1]) end--;        // ignore duplicates from end backwards
                    }
                    else if(tupleSum < 0)   // move ahead from start as nums is sorted
                        start++;
                    else                   // reduce from end if tuple-sum >0
                        end--;
                }
            }
        }
        return result;
    }

    private void populateTuple(List<List<Integer>> result, int num1, int num2, int num3) {
        result.add(Arrays.asList(num1, num2, num3));
    }

    public static void main(String[] args) {
        LC15Problem3Sum problem3Sum = new LC15Problem3Sum();
        System.out.println(problem3Sum.threeSum(new int[] {1, -1, -1, 0}));  // [1,-1,0]
        System.out.println(problem3Sum.threeSum(new int[] {0, 0, 0, 0}));  // [0,0,0]
    }
}


