package main.java.problems.arrays;

import java.util.HashSet;
import java.util.Set;

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
 */
public class LC03LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty() || s == null ) return 0;
        Set<Character> set = new HashSet<>();
        int prevIdx = 0, max = 1;
        for (int curIdx = 0; curIdx < s.length(); curIdx++) {
            char c = s.charAt(curIdx);
            if(!set.contains(c)) {
                set.add(c);
                max = Math.max(max, set.size());
            }
            else
            {
                while (prevIdx < curIdx) {
                    if(s.charAt(prevIdx) == c) {
                        prevIdx++;
                        break;
                    }
                    set.remove(s.charAt(prevIdx));
                    prevIdx++;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LC03LongestSubstringWithoutRepeatingCharacters substringWithoutRepeatingCharacters = new LC03LongestSubstringWithoutRepeatingCharacters();
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("bbbb"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("au"));
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring("dvdf")); // 3
        System.out.println(substringWithoutRepeatingCharacters.lengthOfLongestSubstring(" "));
    }
}
