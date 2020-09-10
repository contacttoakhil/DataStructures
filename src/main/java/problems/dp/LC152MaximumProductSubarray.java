package main.java.problems.dp;

import static java.lang.Math.max;
import static java.lang.Math.min;

/***
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Hint: When iterating the array, each element has two possibilities: positive number or negative number. We need to track a minimum value, so that
 * when a negative number is given, it can also find the maximum value. We define two local variables, one tracks the maximum and the other tracks the minimum.
 *
 */
public class LC152MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        // store the result that is the max we have found so far
        int result = nums[0];
        int imax = result, imin = result; // max/min product of subarray ending at A[i]
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {      // negative makes big number smaller and vice versa {
               int temp = imax;
               imax = imin;
               imin = temp;
            }
            imax = max(nums[i], imax * nums[i]);
            imin = min(nums[i], imin * nums[i]);
            result = max(result, imax);
        }
        return result;
    }

    private void swap(int imax, int imin) {
        int temp = imax;
        imax = imin;
        imin = temp;
    }

    public int maxProduct2(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i]>0){
                max[i]= max(nums[i], max[i-1]*nums[i]);
                min[i]= min(nums[i], min[i-1]*nums[i]);
            }else{
                max[i]= max(nums[i], min[i-1]*nums[i]);
                min[i]= min(nums[i], max[i-1]*nums[i]);
            }

            result = max(result, max[i]);
        }

        return result;
    }
}
