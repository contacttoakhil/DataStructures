package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Also check : Subsets

/***
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * @link https://leetcode.com/problems/subsets/
 * Also check Subsets
 */
public class LC78Subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> generateSubsets(int[] input) {
        Arrays.sort(input);
        compute(new ArrayList<>(), input, 0);
        return result;
    }

    private void compute(List<Integer> temp, int[] input, int start) {
        result.add(new ArrayList<>(temp));  // for subsets of size k we can check temp.length
        for (int i = start; i < input.length; i++) {
            temp.add(input[i]);
            compute(temp, input, i+1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC78Subsets subsets = new LC78Subsets();
        List<List<Integer>> result = subsets.generateSubsets(new int[] {1,3,2});
        System.out.println(result);
    }
}