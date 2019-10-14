package main.java.problems.others;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * Example 1:
 *
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * Example 2:
 *
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 *
 */
public class LC228SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            if (i != nums.length-1 && nums[i + 1]  == nums[i] + 1)
                continue;
            String str = (list.size() == 1) ? list.get(0) + "" : list.get(0) + "->" + list.get(list.size() - 1);
            result.add(str);
            list.clear();
        }
        return result;
    }

    public static void main(String[] args) {
        LC228SummaryRanges summaryRanges = new LC228SummaryRanges();
        System.out.println(summaryRanges.summaryRanges(new int[] {0,1,2,4,5,7}));
    }
}
