package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/***
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 *
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Examples:
 * input: 1
 * output:
 * []
 *
 * input: 37
 * output:
 * []
 *
 * input: 12
 * output:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 *
 * input: 32
 * output:
 * [
 *   [2, 16],
 *   [2, 2, 8],
 *   [2, 2, 2, 4],
 *   [2, 2, 2, 2, 2],
 *   [2, 4, 4],
 *   [4, 8]
 * ]
 *
 */
public class LC254FactorCombinations {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> factorCombinations(int n) {
        backtrack(new ArrayList<>(), n, 2,1);
        return result;
    }

    private void backtrack(List<Integer> temp, int n, int start, int product) {
        if (start > n || product > n)
            return;
        if(product == n) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i < n; i++) {
            if(i * product > n)
                break;
            if(n % i == 0) {
                temp.add(i);
                backtrack(temp, n, i, i*product);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LC254FactorCombinations factorCombinationsOfANumber = new LC254FactorCombinations();
        List<List<Integer>> result = factorCombinationsOfANumber.factorCombinations(24);
        System.out.println(result);
    }
}