package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Also check : Subsets
public class LC78Subsets {

    public List<List<Integer>> generateSubsets(int[] input) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        compute(result, new ArrayList<>(), input, 0);
        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < input.length; i++) {
            temp.add(input[i]);
            compute(result, temp, input, i+1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC78Subsets subsets = new LC78Subsets();
        List<List<Integer>> result = subsets.generateSubsets(new int[] {1,3,2});
        System.out.println(result);
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// http://javabypatel.blogspot.com/2015/10/all-subsets-of-set-powerset.html <- explanation
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////