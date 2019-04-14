package main.java.problems.divideAndConquer;

import java.util.Arrays;

public class LC34FirstAndLastPosition {

    public int first(int[] nums, int start, int finish, int target) {
        if(start > finish) return -1;
        int mid = start + (finish - start) / 2;
        if (nums[mid] == target && (mid == start || nums[mid - 1] < target))
            return mid;
        else if(nums[mid] >= target)        //select left half if element at mid is higher or equal.
            return first(nums,start,mid-1,target);
        else
            return first(nums,mid+1,finish,target);
    }

    public int last(int[]  nums, int start, int finish, int target) {
        if(start>finish) return -1;
        int mid = start +(finish-start)/2;
        if(nums[mid]==target && (mid==finish ||  nums[mid+1]>target)) return mid;
        else if(nums[mid] <= target)         //select right half if element is smaller or equal as we want last occurrence.
            return last(nums,mid+1,finish,target);
        else
            return last(nums,start,mid-1,target);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] occurrences = new int[2];
        occurrences[0] = first(nums,0, nums.length - 1, target);
        occurrences[1] = last(nums,0, nums.length - 1, target);
        return occurrences;
    }

    public static void main(String[] args) {
        LC34FirstAndLastPosition firstAndLastPosition = new LC34FirstAndLastPosition();
        System.out.println(Arrays.toString(firstAndLastPosition.searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(firstAndLastPosition.searchRange(new int[]{2,2}, 2)));

    }
}
