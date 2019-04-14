package main.java.problems.dp;

/***
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * https://leetcode.com/problems/regular-expression-matching/solution/
 *
 * ===== Dynamic Programming =====
 * We can use a 2D array table such that table[i][j] denotes substring from index i to j is palindrome or not.
 *
 * <--- (i)             (j) --->
 * <---    (i+1)...(j-1)    --->
 *
 * if( table[i+1][j-1] == 1 && s.charAt(i) == s.charAt(j))  => table[i][j] == 1
 *
 * The above approach will take O(n^2) time and O(n^2) space complexity as using 2D array.
 *
 *
 * A better solution which use O(1) space can be provided when we observe a palindrome mirrors around its center and a center can be in between the elements also in case of even length. So for a string of length n we can have 2n-1
 * centers.
 * CAMMAC
 *
 * For odd length:
 *
 *  ------------------------
 *  |C | A | M | M | A | C |
 *  ------------------------
 *           ^
 *
 * For even length (between letters):
 *  ------------------------
 *  |C | A | M | M | A | C |
 *  ------------------------
 *             ^
 *
 */
public class LC05LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.isEmpty()) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expandAroundCenter(s, i, i);
            int evenLength = expandAroundCenter(s, i, i + 1);
            int len = Math.max(oddLength, evenLength);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // left - left to center, right - right to center
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        LC05LongestPalindromicSubstring longestPalindromicSubstring = new LC05LongestPalindromicSubstring();
        System.out.println(longestPalindromicSubstring.longestPalindrome("babad")); // expected : bab
        System.out.println(longestPalindromicSubstring.longestPalindrome("cbbd")); // expected : bb
        System.out.println(longestPalindromicSubstring.longestPalindrome("ac")); // expected : a
    }
}
