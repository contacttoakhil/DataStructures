package main.java.problems.graphs;

import java.util.ArrayList;
import java.util.List;

/***
 * A string S represents a list of words.
 *
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 *
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 *
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 *
 * Input: "abcd"
 * Output: ["abcd"]
 *
 *
 * Note:
 *
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
public class LC1087BraceExpansion {
    List<String> result = new ArrayList<>();
    public List<String> getAllExpansion(String s) {
        dfs(s, 0, "");
        return result;
    }
    private void dfs(String s, int start, String temp) {
        if (start == s.length()) {
            result.add(temp);
            return;
        }
        int left = s.indexOf('{', start);
        int right = s.indexOf('}', start);
        if (left == -1) {
            result.add(temp + s.substring(start));
            return;
        }
        String[] strs = s.substring(left + 1, right).split(",");
        for (String str : strs) {
            dfs(s, right + 1, temp + s.substring(start, left) + str);
        }
    }

    public static void main(String[] args) {
        LC1087BraceExpansion braceExpansion = new LC1087BraceExpansion();
        System.out.println(braceExpansion.getAllExpansion("{a,b}c{d,e}f"));// [acdf, acef, bcdf, bcef]
    }
}
