package main.java.problems.others;

import java.util.Arrays;

/***
 * Implement children permutation, which rearranges numbers into the lexicographically children greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * Solution: The steps followed are:
 * a) Find inversion point: left to ip elements are sorted in increasing fashion and right to ip are sorted in decreasing fashion.
 *    1  5  8  4  7  6  5  3  1
 *             |
 *         inversion point
 * b) Scan from right to left, find the first element that is greater than ip i.e. rp and element is replacement element
 *    4 5 6 3 2 1
 *        |
 *        q
 * c) Swap p and q
 *    4 5 6 3 2 1
 *    swap
 *    4 6 5 3 2 1
 * d) reverse elements [p+1, nums.length]
 *    4 6 1 2 3 5
 */
public class LC31NextPermutation {

    public void nextPermutation(int[] nums) {
        // find inversion point
        int ip = nums.length - 2;
        while (ip >= 0 && nums[ip] >= nums[ip + 1]) {
            ip--;
        }
        // find replacement point if ip is not -1 and swap elements at ip and rp.
        if(ip >= 0) {
            for (int rp = nums.length - 1; rp > ip ; rp--) {
                if(nums[rp] > nums[ip]) {
                    swap(nums, ip, rp);
                    break;
                }
            }
        }
        // reverse inversion point till end. If ip==-1 e.g. [3,2,1]then we need to give starting permutation, e.g. [1,2,3]
        reverse(nums, ip + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LC31NextPermutation nextPermutation = new LC31NextPermutation();
        int[] nums = new int[]{3,2,1};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
