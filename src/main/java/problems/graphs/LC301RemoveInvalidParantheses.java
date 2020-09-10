package main.java.problems.graphs;

import java.util.*;

/***
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 *
 * The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), check if they are valid, if found valid ones on the current level, put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.
 *
 * The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, also no recursion call is needed in BFS.
 *
 * Thanks to @peisi, we don't need stack to check valid parentheses.
 *
 * Time complexity:
 *
 * In BFS we handle the states level by level, in the worst case, we need to handle all the levels, we can analyze the time complexity level by level and add them up to get the final complexity.
 *
 * On the first level, there's only one string which is the input string s, let's say the length of it is n, to check whether it's valid, we need O(n) time. On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, we need to check whether it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time complexity is (n-2) x C(n, n-2), so on and so forth...
 *
 * Finally we have this formula:
 *
 * T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
 */
public class LC301RemoveInvalidParantheses {

    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;
        queue.add(s);
        visited.add(s);
        boolean reached = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.remove();
                if (isValid(cur)) {
                    reached = true;
                    result.add(cur);
                }
                if (!reached)
                    processForNextLevel(cur);
            }
            if (reached) break;
        }
        return result;
    }
    private void processForNextLevel(String cur) {
        for (int j = 0; j < cur.length(); j++) {
            if (cur.charAt(j) != '(' && cur.charAt(j) != ')') continue;
            String newStr = cur.substring(0, j) + cur.substring(j + 1);
            if (!visited.contains(newStr)) {
                queue.add(newStr);
                visited.add(newStr);
            }
        }
    }
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }

    public static void main(String[] args) {
        LC301RemoveInvalidParantheses removeInvalidParantheses = new LC301RemoveInvalidParantheses();
        System.out.println(removeInvalidParantheses.removeInvalidParentheses("(a)())()")); // O/P: ["(a)()()", "(a())()"]
    }
}
