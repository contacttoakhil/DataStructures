package main.java.problems.others.calculators;

import java.util.Stack;

/***
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC224BasicCalculator {

    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        if (s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0, pre = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch))     //consecutive digits as a number, save in `pre`
                pre = pre*10+(ch-'0');
            if (ch == '(') {
                int j = findClosingParantheses(s.substring(i));
                pre = calculate(s.substring(i+1, i+j));
                i += j;
            }
            if (i == s.length()-1 || !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        stack.push(pre); break;
                    case '-':
                        stack.push(-pre); break;
                }
                pre = 0;
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
        LC224BasicCalculator basicCalculator = new LC224BasicCalculator();
        System.out.println(basicCalculator.calculate("(1+(4+5+2)-3)+(6+8)")); // 23
    }
}
