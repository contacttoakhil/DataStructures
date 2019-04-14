package main.java.problems.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 */
public class LC20ValidParantheses {

    private Map<Character, Character> mappings;

    public LC20ValidParantheses() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()) {
            if (this.mappings.containsKey(c)) {    // closing bracket?
                char topElement = stack.empty() ? '#' : stack.pop();                  // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                if (topElement != this.mappings.get(c)) {                             // If the mapping for this bracket doesn't match the stack's top element, return false.
                    return false;
                }
            } else {
                stack.push(c);          // push opening bracket to stack
            }
        }
        return stack.isEmpty();                         // If the stack still contains elements, then it is an invalid expression.
    }

    public static void main(String[] args) {
        LC20ValidParantheses lc20ValidParantheses = new LC20ValidParantheses();
        System.out.println(lc20ValidParantheses.isValid("["));
        System.out.println(lc20ValidParantheses.isValid("()"));
        System.out.println(lc20ValidParantheses.isValid("()[]{}"));
        System.out.println(lc20ValidParantheses.isValid("(]"));
        System.out.println(lc20ValidParantheses.isValid("([)]"));
        System.out.println(lc20ValidParantheses.isValid("{[]}"));
    }
}

// Time: O(n) - single traversal
// Space: O(n) - stack size
