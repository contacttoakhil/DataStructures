package main.java.problems.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class LC01TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];
        Map<Integer,Integer> inputMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int toCheck = target-nums[i];
            if(inputMap.keySet().contains(toCheck)) {
                indices[0] = i;
                indices[1] = inputMap.get(toCheck);
            }
            else
                inputMap.put(nums[i],i);
        }
        return indices;
    }

    public static void main(String[] args) {
        LC01TwoSum twoSum = new LC01TwoSum();
        int[] result = twoSum.twoSum(new int[] {2,7,11,5}, 9);
        System.out.println(Arrays.toString(result)); //[0,1]
    }
}
