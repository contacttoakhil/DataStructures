package main.java.problems.others.calculators;

import java.util.Stack;

/***
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC227BasicCalculatorII {
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        if (s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0, num = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch))     //consecutive digits as a number, save in `pre`
                num = num * 10 + (ch - '0');
            if (i == s.length()-1 || !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        stack.push(num); break;
                    case '-':
                        stack.push(-num); break;
                    case '*':
                        stack.push(stack.pop()*num); break;
                    case '/':
                        stack.push(stack.pop()/num); break;
                }
                num = 0;
                sign = ch;
            }
            i++;
        }
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    //Eliminate all "()" pairs, calculate the result in between and save in `pre`
    private int findClosingParantheses(String s) {
        int level = 0, i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') level++;
            else if (s.charAt(i) == ')' && --level == 0)  break;
        }
        return i;
    }

    public static void main(String[] args) {
        LC227BasicCalculatorII basicCalculatorII = new LC227BasicCalculatorII();
        System.out.println(basicCalculatorII.calculate(" 3+5 / 2 ")); // 5
    }
}
