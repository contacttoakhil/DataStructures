package main.java.problems.backtracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.UIManager.put;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * @Related :
 * CombinationSum
 */
public class LC17MobilePhoneDigitWordCombinations {

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<String>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }

    public void backtrack(String temp, String remainingDigits) {
        if (remainingDigits.length() == 0) {    // no more digits to check
            output.add(temp);
            return;
        }
        String nextDigit = remainingDigits.substring(0, 1);
        String letters = phone.get(nextDigit);
        for (int i = 0; i < letters.length(); i++) {
            String letter = phone.get(nextDigit).substring(i, i + 1);
            // append the current letter to the combination
            // and proceed to the children digits
            backtrack(temp + letter, remainingDigits.substring(1));
        }
    }

    public static void main(String[] args) {
        LC17MobilePhoneDigitWordCombinations wordCombinations = new LC17MobilePhoneDigitWordCombinations();
        List<String> result = wordCombinations.letterCombinations("23");
        System.out.println(result);
    }
}

// Time complexity : O(3^N * 4^M) where N is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and M is the number of digits in the input that maps to 4 letters (e.g. 7, 9), and N+M is the total number digits in the input.
// Space complexity : O(3^N * 4^M) since one has to keep 3^N * 4^M solutions.

