package main.java.problems.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LC32LongestValidParantheses {

    public int longestValidParanthesesLength(String input) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '(')
                stack.push(i);
            else                            // char is ')'
            {
                stack.pop();
                if(stack.isEmpty())
                    stack.push(i);
                else
                    max = Math.max(max, i - stack.peek());
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LC32LongestValidParantheses validParantheses = new LC32LongestValidParantheses();
        System.out.println(validParantheses.longestValidParanthesesLength("(()"));
    }
}

// Time: O(n) - single traversal
// Space: O(n) - stack size
