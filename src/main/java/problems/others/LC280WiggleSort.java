package main.java.problems.others;

import java.util.Arrays;

public class LC280WiggleSort {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LC280WiggleSort wiggleSort = new LC280WiggleSort();
        int[] array = new int[] {3, 5, 2, 1, 6, 4};
        wiggleSort.wiggleSort(array);
        System.out.println(Arrays.toString(array)); //1, 6, 2, 5, 3, 4
    }
}
