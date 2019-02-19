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

    public boolean isValid(String input) {
        Map<Character,Character> map = getMap();
        Stack<Character> stack = new Stack();
        for (char current : input.toCharArray()) {
            if(map.keySet().contains(current)) {
                stack.push(current);
            }
            else if(map.values().contains(current)
                    && !stack.isEmpty()
                    && map.get(stack.peek()) == current ) {
               stack.pop();
            }
            else
                return false;
        }
        return stack.isEmpty();
    }

    private Map<Character,Character> getMap() {
        Map<Character,Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        return map;
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
