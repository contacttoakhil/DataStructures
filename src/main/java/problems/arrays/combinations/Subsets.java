package main.java.problems.arrays.combinations;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<String> generateSubsets(String input) {
        List<String> result = new ArrayList<>();
        compute(result, "", input); // StringUtils.EMPTY_STRING if using apache common
        return result;
    }

    private void compute(List<String> result, String inputSoFar, String rest) {
        if(rest.isEmpty() || rest == null) {
            result.add(inputSoFar);
            return;
        }
        compute(result, inputSoFar + rest.charAt(0), rest.substring(1));
        compute(result, inputSoFar, rest.substring(1));
    }

    public static void main(String[] args) {
        Subsets ss = new Subsets();
        List<String> subsets = ss.generateSubsets("ABC");
        System.out.println(subsets);
    }
}
