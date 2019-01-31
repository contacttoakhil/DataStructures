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
    public List<List<Integer>> combinationSum(int[] input, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if(input.length == 0 || input == null)   return result;
        Arrays.sort(input); //O(nlgn)
        compute(result, new ArrayList<>(), input, 0, targetSum); // Common mistake : passing Collections.emptyList() in place of ArrayList
        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, int start, int remained) {
        if(remained == 0) {  // CM : to avoid duplicate combination also search for it using contains.
            //System.out.println("pushing start:" + start + " target: " + remained);
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < input.length; i++) {   // O(n)
            //System.out.println("i:" + i + " input[i]: " + input[i] + " start:" + start + " target: " + remained);
            if(input[i] > remained)  {
                //System.out.println("input[i]:" + input[i] + " > target:" + remained + " so breaking out");
                break;
            }
            temp.add(input[i]);
            // remained = remained - candidates[i]; <- common mistake
            compute(result, temp, input, i,remained - input[i]); // not i + 1 because we can reuse same elements
            temp.remove(temp.size()-1); //remove the last added item
        }
    }

    public static void main(String[] args) {
        LC39CombinationSum cs = new LC39CombinationSum();
        List<List<Integer>> result = cs.combinationSum(new int[]{2,3,6,7}, 7);
        System.out.println(result);
    }
}
