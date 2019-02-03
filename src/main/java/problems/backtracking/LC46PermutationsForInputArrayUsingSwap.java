package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class LC46PermutationsForInputArrayUsingSwap {

    public List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        compute(result, nums, 0);
        return result;
    }

    private void compute (List<List<Integer>> result, int[] input, int start) {
        if (start >= input.length) {
            List<Integer> item = convertArrayToList(input);
            result.add(item);
        }

        for (int j = start; j < input.length; j++) {
            swap(input, start, j);
            compute(result, input, start + 1);
            swap(input, start, j);
        }
    }

    private List<Integer> convertArrayToList(int[] input) {
        return Arrays.stream(input).boxed().collect(Collectors.toList());
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        LC46PermutationsForInputArrayUsingSwap pm = new LC46PermutationsForInputArrayUsingSwap();
        List<List<Integer>> result = pm.generatePermutations(new int[] {1,2,3});
        System.out.println(result);
    }
}
