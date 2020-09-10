package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/***
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class LC216CombinationSumIII {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        combination(new ArrayList<Integer>(), k, 1, n);
        return result;
    }

    private void combination(List<Integer> temp, int k,  int start, int remainder) {
        if (temp.size() == k && remainder == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= 9; i++) {
            temp.add(i);
            combination(temp, k, i+1, remainder-i);
            temp.remove(temp.size() - 1);
        }
    }

}
