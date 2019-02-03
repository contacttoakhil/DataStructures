package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Leet Code 46: https://leetcode.com/problems/permutations/description/
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class LC46PermutationsForInputArray {

    public List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        compute(result, new ArrayList<>(), nums);
        return result;
    }

    private void compute (List<List<Integer>> result, List<Integer> temp, int[] input) {
        if(temp.size() == input.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (temp.contains(input[i])) continue;
            temp.add(input[i]);
            compute(result, temp, input);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC46PermutationsForInputArray pm = new LC46PermutationsForInputArray();
        List<List<Integer>> result = pm.generatePermutations(new int[] {1,2,3});
        System.out.println(result);
    }
}
