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
 * {a,b}
 * Permutation - Order matters - {ab,ba}
 * Combination - Order doesn't matter and more of choosing k elements out of n - {{},{a},{b},{a,b}}
 *
 * Also check Permutations.java
 */
public class LC46PermutationsForInputArray {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> generatePermutations(int[] nums) {
        compute(new ArrayList<>(), nums);
        return result;
    }

    private void compute (List<Integer> temp, int[] input) {
        if(temp.size() == input.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (temp.contains(input[i])) continue;
            temp.add(input[i]);
            compute(temp, input);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC46PermutationsForInputArray pm = new LC46PermutationsForInputArray();
        System.out.println(pm.generatePermutations(new int[] {1,2,3}));
    }
}
