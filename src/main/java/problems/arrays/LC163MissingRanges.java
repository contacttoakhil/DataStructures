package main.java.problems.arrays;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
 *
 * For example, given [0, 1, 3, 50, 75], return ["2", "4->49", "51->74", "76->99"]
 *
 * Ref: https://segmentfault.com/a/1190000003790309
 */
public class LC163MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        // Initialize prev to lower-1 and determine if there is a "first" interval
        int prev = lower - 1, current = 0;
        for (int i = 0; i <= nums.length; i++) {
            // // When traversing to length, set curr to upper + 1 to determine if there is a "last" interval
            current = (i == nums.length) ? upper + 1 : nums[i];
            // If the difference between the previous number and the current number is greater than 1, there is an interval between them
            if(current - prev > 1){
                result.add(getRanges(prev+1, current-1));
            }
            prev = current;

        }
        return result;
    }
    private String getRanges(int from, int to){
        return from == to ? String.valueOf(from) : from + "->" + to;
    }

    public static void main(String[] args) {
        LC163MissingRanges missingRanges = new LC163MissingRanges();
        System.out.println(missingRanges.findMissingRanges(new int[] {0,1,3,50,75}, 0, 99));
    }
}
