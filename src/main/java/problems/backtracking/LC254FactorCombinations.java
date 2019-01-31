package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC254FactorCombinations {
    public List<List<Integer>> factorCombinations(int number) {
        List<List<Integer>> result = new ArrayList<>();
        findFactorCombinations(result, new ArrayList<>(), number, 2,1);
        return result;
    }

    private void findFactorCombinations(List<List<Integer>> result, List<Integer> temp, int inputNumber, int start, int product) {
        if (start > inputNumber || product > inputNumber)
            return;
        if(product == inputNumber) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start; i<=inputNumber; i++) {
            if(i*product > inputNumber)
                break;
            if(inputNumber % i == 0) {
                temp.add(i);
                findFactorCombinations(result, temp, inputNumber, i, i*product);
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


//////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// https://www.programcreek.com/2014/07/leetcode-factor-combinations-java/
// http://buttercola.blogspot.com/2015/08/leetcode-factor-combinations.html
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////