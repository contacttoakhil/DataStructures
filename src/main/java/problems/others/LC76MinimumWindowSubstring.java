package main.java.problems.others;

import java.util.StringTokenizer;

/***
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class LC76MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int res = Integer.MAX_VALUE, left = 0, right = 0, begin = 0, counter = t.length();
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'A']++;
        }
        while (right < s.length()) {
            if (map[s.charAt(right++) - 'A']-- > 0) counter--;  // The number of times this number should appear minus one
            while (counter == 0) {                              // Shorten the left border
                if (right - left < res) {
                    res = right - left;
                    begin = left;
                }
                if (map[s.charAt(left++) - 'A']++ == 0) counter++;
            }
        }
        return res == Integer.MAX_VALUE ? "" : s.substring(begin, begin + res);
    }
}
