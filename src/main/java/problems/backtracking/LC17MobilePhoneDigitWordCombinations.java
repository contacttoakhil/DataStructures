package main.java.problems.backtracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<String> printWordCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return result;
        compute(result, new StringBuilder(), digits, 0, getDigitMap());
        return result;
    }

    private void compute(List<String> result, StringBuilder temp, String digits, int start, Map<Integer, String> digitMap) {
        if (start >= digits.length()) {
            result.add(temp.toString());
            return;
        }
        String letters = getDigitMappings(digits.substring(start,start+1), digitMap);
        for(int i=0; i<letters.length(); i++) {
            temp.append(letters.charAt(i));
            compute(result, temp, digits, start+1, digitMap);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    private String getDigitMappings(String strDigit, Map<Integer, String> digitMap) {
        int digit = Integer.valueOf(strDigit);
        return digitMap.containsKey(digit) ? digitMap.get(digit) : "";
    }

    private Map<Integer, String> getDigitMap() {
        return Stream.of(
                new AbstractMap.SimpleEntry<>(2, "abc"),
                new AbstractMap.SimpleEntry<>(3, "def"),
                new AbstractMap.SimpleEntry<>(4, "ghi"),
                new AbstractMap.SimpleEntry<>(5, "jkl"),
                new AbstractMap.SimpleEntry<>(6, "mno"),
                new AbstractMap.SimpleEntry<>(7, "pqrs"),
                new AbstractMap.SimpleEntry<>(8, "tuv"),
                new AbstractMap.SimpleEntry<>(9, "wxyz")).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    public static void main(String[] args) {
        LC17MobilePhoneDigitWordCombinations wordCombinations = new LC17MobilePhoneDigitWordCombinations();
        List<String> result = wordCombinations.printWordCombinations("23");
        System.out.println(result);
    }
}

// Max 4 possible values for a digit and for string having n digits the time complexity would be O(4^n).


