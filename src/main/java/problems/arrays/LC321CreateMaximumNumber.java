package main.java.problems.arrays;

import java.util.Arrays;

/***
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.
 *
 * Note: You should try to optimize your time and space complexity.
 *
 * Example 1:
 *
 * Input:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * Output:
 * [9, 8, 6, 5, 3]
 * Example 2:
 *
 * Input:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * Output:
 * [6, 7, 6, 0, 4]
 * Example 3:
 *
 * Input:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * Output:
 * [9, 8, 9]
 *
 * https://leetcode.com/problems/create-maximum-number/
 */
public class LC321CreateMaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        int n1 = nums1.length;
        int n2 = nums2.length;
        // step 1: find the largest number from each array, and merge into one
        for (int i = Math.max(0, k - n2); i <= Math.min(n1, k); i++) {
            int[] list1 = findMax(nums1, i);
            int[] list2 = findMax(nums2, k - i);
            // then merge into one
            int[] curr = merge(list1, list2);
            if (greater(curr, 0, result, 0))
                result = curr;
        }
        return result;
    }

    private int[] findMax(int[] nums, int k) {
        int[] stack = new int[k];
        int n = nums.length;
        for (int i = 0, len = 0; i < n; i++) {
            while (len > 0 && len + n - i > k && nums[i] > stack[len - 1])
                len--;
            if (len < k)
                stack[len++] = nums[i];
        }
        return stack;
    }

    private int[] merge(int[] list1, int[] list2) {
        int n1 = list1.length;
        int n2 = list2.length;
        int[] result = new int[n1 + n2];
        int i = 0, j = 0, k = 0;
        while (k < n1 + n2)
            result[k++] = greater(list1, i, list2, j) ? list1[i++] : list2[j++];
        return result;
    }

    public boolean greater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) return true;
            if (nums1[start1] < nums2[start2]) return false;
        }
        return start1 != nums1.length;
    }

     public static void main(String[] args) {
        LC321CreateMaximumNumber createMaximumNumber = new LC321CreateMaximumNumber();
        int[] result = createMaximumNumber.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
        System.out.println(Arrays.toString(result));
    }
}
