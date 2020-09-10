package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/***
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 *
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 *
 * https://leetcode.com/articles/24-game/
 * https://leetcode.com/problems/24-game/discuss/107673/JAVA-Easy-to-understand.-Backtracking.
 */
public class LC67924Game {

    static final double EPSILON = 0.001;

    public boolean judgePoint24(int[] nums) {
        double[] data = new double[nums.length] ;
        for (int i = 0; i < nums.length; i++) data[i] = (double) nums[i];
        return helper(data);
    }

    private boolean helper(double[] nums) {

        if (nums.length == 1) return Math.abs(nums[0]- 24.0) < EPSILON;

        // create a new double[] whose length is 1 smaller than that of nums
        int n = nums.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {

                // initialize double[] for dfs and set its part of entries
                double[] next = new double[n-1];
                for(int k = 0, index = 0; k < n; k++) {
                    if (k != i && k != j) next[index++] = nums[k];
                }

                double d1 = nums[i], d2 = nums[j];

                // get all possible operation results from nums[i] and nums[j] then dfs
                // the last entry in 'next' is next[n-2]
                double[] dirs = new double[]{d1 + d2, d1 - d2, d2 - d1, d2 * d1};
                for (double dir: dirs) {
                    next[n-2] = dir;
                    if (helper(next)) return true;
                }

                if (d1 > EPSILON) {
                    next[n-2] = d2 / d1;
                    if (helper(next)) return true;
                }

                if (d2 > EPSILON) {
                    next[n-2] = d1 / d2;
                    if (helper(next)) return true;
                }
            }
        }

        return false;
    }

}
// Time: O(1)