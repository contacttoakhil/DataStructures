package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC47PermutationsForInputArrayUniqueUsingSwap {

    public List<List<Integer>> generatePermutations(int[] input) {
        List<List<Integer>> result = new ArrayList<>();
        compute(result, input, 0);
        return result;
    }

    private void compute (List<List<Integer>> result, int[] input, int start){
        if (start == input.length ) {
            List<Integer> item = convertArrayToList(input);
            result.add(item);
        }

        for (int j = start; j < input.length; j++) {
            if (containsDuplicate(input, start, j)) {
                swap(input, start, j);
                compute(result, input, start + 1);
                swap(input, start, j);
            }
        }
    }

    private List<Integer> convertArrayToList(int[] input) {
        return Arrays.stream(input).boxed().collect(Collectors.toList());
    }

    private boolean containsDuplicate(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        LC47PermutationsForInputArrayUniqueUsingSwap pm = new LC47PermutationsForInputArrayUniqueUsingSwap();
        List<List<Integer>> result = pm.generatePermutations(new int[] {1,2,3,3});
        System.out.println(result);
    }
}
