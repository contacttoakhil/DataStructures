package main.java.problems.slidingWindow;

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

    public int lengthOfLongestSubstringConcised(String s) {
        int[] map = new int[128];
        int left = 0, right = 0, res = 0, counter = 0;
        while (right < s.length()) {
            final char c1 = s.charAt(right);
            if (map[c1] > 0) counter++;
            map[c1]++;      right++;
            while (counter > 0) {
                final char c2 = s.charAt(left);
                if (map[c2] > 1) counter--;
                map[c2]--;    left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, counter = 0, result = 0;

        while (right < s.length()) {
            char charAtRight = s.charAt(right);
            map.put(charAtRight, map.getOrDefault(charAtRight, 0) + 1);
            if(map.get(charAtRight) > 1) counter++;
            right++;
            while (counter > 0) {
                char charAtLeft = s.charAt(left);
                if (map.get(charAtLeft) > 1) counter--;
                map.put(charAtLeft, map.get(charAtLeft)-1);
                left++;
            }
            result = Math.max(result, right - left);
        }
        return result;
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