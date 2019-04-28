package main.java.problems.arrays;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Hint: Use sliding window
 */
public class LC03LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        char currentChar;
        for (int j = 0, i = 0; j < s.length(); j++) {
            currentChar = s.charAt(j);
            if (map.containsKey(currentChar)) {
                i = Math.max(map.get(currentChar), i);
            }
            maxLen = Math.max(maxLen, j - i + 1);
            map.put(currentChar, j + 1);
        }
        return maxLen;
    }

    public int lengthOfLongestSubstringBetter(String s) {
        int n = s.length(), maxLen = 0;
        int[] index = new int[256];
        for (int j = 0, i = 0; j < n; j++) {
            char charAtIdxJ = s.charAt(j);
            i = Math.max(index[charAtIdxJ], i);             // i->j would be sting without repeating chars
            maxLen = Math.max(maxLen, j - i + 1);
            index[charAtIdxJ] = j + 1;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LC03LongestSubstringWithoutRepeatingCharacters substringWithoutRepeatingCharacters = new LC03LongestSubstringWithoutRepeatingCharacters();
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcdbmnop"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("bbbb"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("au"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("dvdf")); // 3
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring(" "));
    }
}