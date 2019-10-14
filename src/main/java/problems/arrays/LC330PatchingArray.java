package main.java.problems.arrays;

/***
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
 *
 * Example 1:
 *
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 *
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 * Example 3:
 *
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 *
 * https://leetcode.com/problems/patching-array/
 */
public class LC330PatchingArray {
    public int minPatches(int[] nums, int n) {
        int count = 0, i = 0;
        long miss = 1;          // long to avoid overflow
        while(miss <= n){
            if(i<nums.length && nums[i] <= miss){   // miss is covered
                miss = miss + nums[i];
                i++;
            }else{                                  // patch miss to array
                miss += miss;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC330PatchingArray patchingArray = new LC330PatchingArray();
        System.out.println(patchingArray.minPatches(new int[]{1,3}, 6));  // 1 as we need to patch 2.
        System.out.println(patchingArray.minPatches(new int[]{1,5,10}, 20));  // 2 as we need to patch 2,4
    }
}
