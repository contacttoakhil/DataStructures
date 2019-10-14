package main.java.problems.trees;

import main.java.ds.trees.FenwickTree;

import java.util.Arrays;

/***
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 *
 * You need to return the number of important reverse pairs in the given array.
 *
 * Example1:
 *
 * Input: [1,3,2,3,1]
 * Output: 2
 * Example2:
 *
 * Input: [2,4,3,5,1]
 * Output: 3
 * Note:
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 */
public class LC493ReversePairs {

    private int search(int[] BITree, int i) {
        int sum = 0;

        while (i < BITree.length) {
            sum = sum + BITree[i];
            i = i + (i & -i);
        }

        return sum;
    }

    private void insert(int[] BITree, int i) {
        while (i > 0) {
            BITree[i] += 1;
            i = i - (i & -i);
        }
    }

    public int reversePairs(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] BITree = new int[copy.length + 1];
        Arrays.sort(copy);
        for (int num : nums) {
            int grtIdx = indexUsingBinarySearch(copy, 2L * num + 1); // idx of value grater than 2*nums[i]
            int sr = search(BITree, grtIdx);  // Find number greater than or equal to nums[i] * 2l + 1
            res = res + sr;
            int numIdx = indexUsingBinarySearch(copy, num);
            insert(BITree, numIdx);   // reinsert the number
        }
        return res;
    }

    public int reversePairs2(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        FenwickTree fenwickTree = new FenwickTree(nums.length);
        Arrays.sort(copy);
        for (int num : nums) {
            int grtIdx  = fenwickTree.index(2L * num + 1);
            res += res + fenwickTree.search(grtIdx);
            int numIdx = fenwickTree.index(num);
            fenwickTree.insert(numIdx);
        }
        return res;
    }

    // 在一个有序数组中搜索
    // For each element, the "index" function will return its index in the BIT.
    // Unlike the BST-based solution, this is guaranteed to run at O(nlogn)
    private int indexUsingBinarySearch(int[] arr, long val) {
        int l = 0, r = arr.length - 1, m = 0;

        while (l <= r) {
            m = l + ((r - l) >> 1);

            if (arr[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l + 1;  // BIT is one-indexed so +1
    }

    public static void main(String[] args) {
        LC493ReversePairs reversePairs = new LC493ReversePairs();
        System.out.println(reversePairs.reversePairs(new int[] {1,3,2,3,1}));  // 2
        System.out.println(reversePairs.reversePairs(new int[] {2,4,3,5,1}));  // 3
        System.out.println(reversePairs.reversePairs2(new int[] {1,3,2,3,1}));  // 2
        System.out.println(reversePairs.reversePairs2(new int[] {2,4,3,5,1}));  // 3
    }
}
