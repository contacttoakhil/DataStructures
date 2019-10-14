package main.java.problems.others.calculators;

import java.util.Stack;

/***
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
 * Some examples:
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 */
public class LC772BasicCalculatorIII {

    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        if (s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0, num = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch))  num = num * 10 + (ch - '0');  // consecutive digits
            if (ch == '(') {
                int j = findClosingParantheses(s.substring(i));
                num = calculate(s.substring(i+1, i+j));
                i += j;
            }
            if (i == s.length()-1 || !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        stack.push(num); break;
                    case '-':
                        stack.push(-num); break;
                    case '*':
                        stack.push(stack.pop() * num); break;
                    case '/':
                        stack.push(stack.pop() / num); break;
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
        LC772BasicCalculatorIII basicCalculatorIII = new LC772BasicCalculatorIII();
        System.out.println(basicCalculatorIII.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); // -12
    }
}
