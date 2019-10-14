package main.java.problems.dp;

/***
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Solution:
 * If children char (j+1) in pattern is * then
 *   a) ignore the character as * can represent empty or no presence of previous char, move to children char (j+2).
 *   b) matches one or more of previous chars, so if current char match in text and pattern, move one char in text and try again.
 *
 * If children char is not * then it can be regular alphabet or . and in that case move one char in both text and pattern, if current char match.
 */
public class LC06RegularExpressionMatching {

    private char DOT = '.';
    private char KLEENE_STAR = '*';

    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        int tl = text.length();
        int pl = pattern.length() - 1; // for a pattern we consider * also

        for (int i = tl; i >= 0; i--){
            for (int j = pl; j >= 0; j--){
                boolean first_match = (i < tl && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*')
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                else
                    dp[i][j] = first_match && dp[i+1][j+1];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        LC06RegularExpressionMatching regularExpressionMatching = new LC06RegularExpressionMatching();
        System.out.println(regularExpressionMatching.isMatch("aa","a"));
        System.out.println(regularExpressionMatching.isMatch("aa","a*"));
    }
}
