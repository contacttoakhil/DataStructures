package main.java.problems.backtracking;

import java.util.*;

/***
 * We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.
 *
 * We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.
 *
 * We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
 *
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 *
 * Example 1:
 *
 * Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 *     A
 *    / \
 *   G   E
 *  / \ / \
 * B   C   D
 *
 * We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.
 *
 *
 * Example 2:
 *
 * Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
 * Output: false
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 *
 *
 * Note:
 *
 * bottom will be a string with length in range [2, 8].
 * allowed will have length in range [0, 200].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 *
 * https://leetcode.com/problems/pyramid-transition-matrix/
 */
public class LC756PyramidTransitionMatrix {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> m = new HashMap<>();
        for (String s : allowed) {
            String pre = s.substring(0, 2);
            m.putIfAbsent(pre, new HashSet<>());
            m.get(pre).add(s.charAt(2));
        }
        return dfs(bottom, "", m, 1);
    }

    boolean dfs(String row, String nextRow, Map<String, Set<Character>> allowed, int i) {
        if (row.length() == 1) return true;
        if (nextRow.length() + 1 == row.length())
            return dfs(nextRow, "", allowed, 1);
        for (Character c : allowed.getOrDefault(row.substring(i - 1, i + 1), new HashSet<>()))
            if (dfs(row, nextRow + c, allowed, i + 1))
                return true;
        return false;
    }
}
