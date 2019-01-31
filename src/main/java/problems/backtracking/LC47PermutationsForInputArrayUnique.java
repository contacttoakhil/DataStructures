package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC47PermutationsForInputArrayUnique {

    public List<List<Integer>> generatePermutations(int[] input) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        compute(result, new ArrayList<>(), input, new boolean[input.length]);
        System.out.println(result);

        List<List<Integer>> result2 = new ArrayList<>();
        Arrays.sort(input);
        compute2(result2, new ArrayList<>(), input);
        System.out.println(result2);


        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, boolean[] visited) {
        if(temp.size() == input.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if(visited[i] || i > 0 && input[i] == input[i-1] && !visited[i - 1]) continue;
            visited[i] = true;
            temp.add(input[i]);
            compute(result, temp, input, visited);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    private void compute2(List<List<Integer>> result, List<Integer> temp, int[] input) {
        if(temp.size() == input.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if(temp.contains(input[i])|| i > 0 && input[i] == input[i-1] ) continue;
            temp.add(input[i]);
            compute2(result, temp, input);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC47PermutationsForInputArrayUnique pm = new LC47PermutationsForInputArrayUnique();
        List<List<Integer>> result = pm.generatePermutations(new int[] {1,2,3,3});
        //System.out.println(result);
    }
}
