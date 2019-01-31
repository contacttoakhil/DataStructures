package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Given a set of non-negative integers, and a value sum, determine and print all subsets of the given set (X) with sum equal to given sum K.
 *
 * @Related SubSetSum
 */
public class PerfectSumBT {

    public List<List<Integer>> getSubSetsForTargetSum(int[] input, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        compute(result, new ArrayList<>(), input, 0, targetSum);
        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, int start, int k) {
        if(computeSum(temp) == k)
            result.add(new ArrayList<>(temp));
        for (int i = start; i < input.length; i++) {
            temp.add(input[i]);
            compute(result, temp, input, i+1, k);
            temp.remove(temp.size() - 1);
        }
    }

    private int computeSum(List<Integer> temp) {
        return temp.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    public static void main(String[] args) {
        PerfectSumBT ss = new PerfectSumBT();
        List<List<Integer>> result = ss.getSubSetsForTargetSum(new int[]{3, 34, 4, 12, 5, 2},9);
        System.out.println(result);
    }
}
