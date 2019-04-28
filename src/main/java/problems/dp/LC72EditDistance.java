package main.java.problems.dp;

import java.util.Arrays;

/***
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'r')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * https://leetcode.com/problems/edit-distance/
 *
 * Solution:
 * For two strings with length i and j : word1[0...i-1] and word2[0...j-1] dp[i][j] is:
 * dp[i][j] : edit distance between two strings OR the minimum number of operations to convert word1 to word2.
 *
 * Boundary Cases:
 * To convert a string to an empty string e.g. word1[0...i-1] to "" (empty string) requires at least i operations (deletions). So the boundary case is simply:
 * dp[i][0] = i;
 * dp[0][j] = j.
 *
 * Consider first string has length i and last char is r and second string has length j and last char is c.
 *
 *                    ◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️X
 *        ◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️◽️Y
 *
 *  1) if X = Y  then no operation is required and dp[i][j] = dp[i-1][j-1]
 *  2) if X != Y then we have three possibilities:
 *      a) insert Y to first then:   dp[i][j] = dp[i][j-1] + 1
 *      b) delete X from first then: dp[i][j] = dp[i-1][j] + 1
 *      c) replace X with Y then:    dp[i][j] = dp[i-1][j-1] + 1
 *      d) so dp[i][j] = min (a,b,c);
 *
 */
public class LC72EditDistance {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)             // i deletions
            dp[i][0] = i;
        for (int j = 0; j <= len2; j++)             // j insertions
            dp[0][j] = j;

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                    dp[i + 1][j + 1] = (word1.charAt(i) == word2.charAt(j)) ? dp[i][j] : min(i, j, dp);
            }
        }
        return dp[len1][len2];
    }

    private int min(int i, int j, int[][] dp) {
        int replace = dp[i][j] + 1;
        int insert = dp[i][j + 1] + 1;
        int delete = dp[i + 1][j] + 1;
        return Math.min(replace, Math.min(insert, delete));
    }
}
