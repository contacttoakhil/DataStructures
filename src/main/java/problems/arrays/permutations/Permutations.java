package main.java.problems.arrays.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Given a collection of distinct integers, return all possible permutations.
   Input: [1,2,3]
   Output:
   [
   [1,2,3],
   [1,3,2],
   [2,1,3],
   [2,3,1],
   [3,1,2],
   [3,2,1]
   ]
 *
 * https://leetcode.com/problems/permutations/description/
 * https://www.programcreek.com/2013/02/leetcode-permutations-java/
 *
 * To Try : Find if given string is a anagram (word in dictionary)
 */
public class Permutations {

    public List<String> generatePermutations(String input) {
        List<String> result = new ArrayList<>();
        compute(result, "",input );
        return result;
    }

    private void compute(List<String> result, String inputSoFar, String rest) {
        if(rest.isEmpty() || rest == null) {  // StringUtils.isEmpty if using apache common
            result.add(inputSoFar);
            return;
        }
        for (int i = 0; i < rest.length(); i++) {
            String next = inputSoFar + rest.charAt(i);
            String remaining = rest.substring(0,i) + rest.substring(i+1);
            compute(result, next, remaining);
        }
    }

    public List<String> generatePermutationsUsingDFS(String input) {
        List<String> result = new ArrayList<>();
        computeUsingDFS(result, new StringBuilder(),input, new boolean[input.length()] );
        return result;
    }

    private void computeUsingDFS(List<String> result, StringBuilder temp, String input, boolean[] visited) {
        if(temp.length() == input.length()) {
            result.add(temp.toString());
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            if(visited[i]) continue;
            temp.append(input.charAt(i));
            visited[i] = true;
            computeUsingDFS(result, temp, input, visited);
            visited[i] = false;
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public static void main(String[] args) {
        Permutations pm = new Permutations();
        List<String> result = pm.generatePermutations("ABC");
        //List<String> result = pm.generatePermutations("AABC");
        System.out.println(result);
        Permutations pm2 = new Permutations();
        List<String> result2 = pm2.generatePermutationsUsingDFS("ABC");
        System.out.println(result2);
    }
}
