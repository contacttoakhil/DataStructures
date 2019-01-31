package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC90SubsetsWithDuplicates {

    public List<List<Integer>> generateSubsets(int[] input) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        compute(result, new ArrayList<>(), input, 0);
        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < input.length; i++) {
            if(i > start && input[i] == input[i-1]) continue; // skip duplicates
            temp.add(input[i]);
            compute(result, temp, input, i+1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC90SubsetsWithDuplicates subsets = new LC90SubsetsWithDuplicates();
        List<List<Integer>> result = subsets.generateSubsets(new int[] {1,2,3,3});
        System.out.println(result);
    }
}
