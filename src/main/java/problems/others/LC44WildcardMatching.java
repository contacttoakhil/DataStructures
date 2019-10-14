package main.java.problems.others;

/***
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
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
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 *
 * https://leetcode.com/problems/wildcard-matching/
 *
 * Solution: In this specific problem, the DP algorithm doesn’t use the fact that if we meet another '*', then the last '*' we meet can be ignore. I use a Two-Pointer algorithm to get a linear time algorithm.
 *
 * http://massivealgorithms.blogspot.com/2015/05/wildcard-matching-leetcode-simple-stupid.html
 * sIdx - index in string
 * pIdx - index in pattern
 * lastS - the index of the s which we try to match with p[lastp].
 *
 * At first we initialize lastP and lastS to be −1−1. Also we set sIdx=0 and pIdx=0.
 *
 * While sIdx <= length of s we have three cases:
 * a) s[sIdx] == p[pIdx] or p[pIdx] == '?'. Then we match these two character and add sIdx and pIdx by 1.
 * b) p[pIdx] == '*'. Then set lastP = pIdx+1 and lastS = sIdx. (Because we can match '*' with empty)
 * c) lastP != -1. If we are in this case, then it means we need to rematch the previous '*'. Set lastS += 1 and pIdx = lastp.
 *
 * When the loop terminate, we need to check the rest element in p[j:end]. If all elements in p[j:end] is '*', then it should be true. Otherwise, it cannot match. Why? Because in this algorithm, once we met a '*' in p, we ignore the previous '*' and start using it to match the remaining string. It’s kind of greedy. If there is a non '*'left in p[j:end], then it means we cannot find a way to match p with s.
 * Since in each times of loop, we at least add either i or j by one.
 * So the overall time complexity will be O(n+m)O(n+m). It’s much better than the DP algorithm above.
 * Space complexity is O(n+m)O(n+m) which is the same as the input.
 */
public class LC44WildcardMatching {
    public boolean isMatch(String s, String p) {
        int pIdx = 0;   // index in pattern p
        int lastS = -1, lastP = -1;

        for(int sIdx = 0; sIdx != s.length();) {
            if(pIdx < p.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?')) {
                sIdx++;
                pIdx++;
            }
            else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                pIdx++;
                lastP = pIdx;
                lastS = sIdx;
            }
            else if (lastP != -1) {
                pIdx = lastP;
                sIdx = ++lastS;
            }
            else
                return false;
        }
        for (; (pIdx < p.length()) && (p.charAt(pIdx) == '*'); pIdx++);
        return (pIdx == p.length());
    }

    public static void main(String[] args) {
        LC44WildcardMatching wildcardMatching = new LC44WildcardMatching();
        System.out.println(wildcardMatching.isMatch("adceb", "*a*b"));
        System.out.println(wildcardMatching.isMatch("acdcb", "a*c?b"));
    }
}
