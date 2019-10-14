package main.java.problems.others;

import java.util.Arrays;

/***
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 *
 * Return 0 if the array contains less than 2 elements.
 *
 * Example 1:
 *
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 *              (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * Note:
 *
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 *
 * 1. if min==max, we can return 0 directly.
 * 2. the length of bucketMin and bucketMax is n rather than n-1. So max can be put in bucket.
 * 3. to check if bucket is empty, check if(bucketMin[i]!=Integer.MAX_VALUE) is ok
 * 4. do not need maxGap, gap is enough.
 */
public class LC164MaximumGap {
    public int maximumGap(int[] nums) {
        if(nums==null || nums.length<2)    return 0;
        int min=nums[0];            int max=nums[0];
        for(int n: nums){
            min=Math.min(min, n);
            max=Math.max(max, n);
        }
        if(min==max) return 0;
        int n=nums.length;
        int gap = (int)Math.ceil((double)(max-min)/(n-1));
        int bucketMin[] = new int[n];
        int bucketMax[] = new int[n];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for(int num: nums){
            int i=(num-min)/gap;
            bucketMin[i] = Math.min(bucketMin[i], num);
            bucketMax[i] = Math.max(bucketMax[i], num);
        }
        for(int i=0;i<bucketMin.length;++i){
            if(bucketMin[i]!=Integer.MAX_VALUE){
                gap = Math.max(gap, bucketMin[i]-min);
                min = bucketMax[i];
            }
        }
        return gap;
    }
}
