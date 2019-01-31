/*
Leet Code 40 : Combination Sum II

Description
Given a set of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
Each number in candidates may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

 */
package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC40CombinationSumUnique {
    public List<List<Integer>> combinationSum(int[] input, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if(input.length == 0 || input == null)   return result;
        Arrays.sort(input); //O(nlgn)
        compute(result, new ArrayList<>(), input, 0, targetSum); // Common mistake : passing Collections.emptyList() in place of ArrayList
        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, int start, int remained) {
        if(remained == 0) {  // CM : to avoid duplicate combination also search for it using contains.
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < input.length; i++) {   // O(n)
            if(input[i] > remained) break;
            if(i > start && input[i] == input[i-1]) continue; // <--- skip duplicates
            temp.add(input[i]);
            compute(result, temp, input, i,remained - input[i]); // not i + 1 because we can reuse same elements
            temp.remove(temp.size()-1); //remove the last added item
        }
    }

    public static void main(String[] args) {
        LC40CombinationSumUnique cs = new LC40CombinationSumUnique();
        List<List<Integer>> result = cs.combinationSum(new int[]{2,3,3,6,7}, 7);
        System.out.println(result);
    }
}
