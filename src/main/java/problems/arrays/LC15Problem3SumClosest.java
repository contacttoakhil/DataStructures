package main.java.problems.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC15Problem3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                int diff = Math.abs(sum - target);
                if(diff == 0) return sum;
                if (diff < min) {
                    min = diff;
                    result = sum;
                }
                if (sum <= target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

}
