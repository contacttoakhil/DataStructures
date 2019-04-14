/*
Leet Code 39 : Combination Sum

Description
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
The same repeated number may be chosen from candidates unlimited number of times.

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

 */


package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC39CombinationSum {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length == 0 || candidates == null)   return result;
        Arrays.sort(candidates); //O(nlgn)
        compute(new ArrayList<>(), candidates, 0, target); // Common mistake : passing Collections.emptyList() in place of ArrayList
        return result;
    }

    private void compute(List<Integer> temp, int[] candidates, int start, int remained) {
        if(remained == 0) {  // CM : to avoid duplicate combination also search for it using contains.
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {   // O(n)
            if(candidates[i] > remained) break;
            temp.add(candidates[i]);
            // remained = remained - candidates[i]; <- common mistake
            compute(temp, candidates, i,remained - candidates[i]); // not i + 1 because we can reuse same elements
            temp.remove(temp.size()-1); //remove the last added item
        }
    }

    public static void main(String[] args) {
        LC39CombinationSum cs = new LC39CombinationSum();
        System.out.println( cs.combinationSum(new int[]{2,3,6,7}, 7));
    }
}
