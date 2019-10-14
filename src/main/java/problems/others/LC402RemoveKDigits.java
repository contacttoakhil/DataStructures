package main.java.problems.others;

import java.util.Stack;

/***
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 * https://leetcode.com/problems/remove-k-digits/
 */
public class LC402RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if(k == num.length()) return "0";
        int i = 0;
        Stack<Character> stack = new Stack<>();
        for (char ch : num.toCharArray()) {
            while(k>0 && !stack.isEmpty() && stack.peek()>ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }
        while (k>0) {      // special cases like 11111 or 12345
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        while (sb.length()>1 && sb.charAt(sb.length()-1)=='0')
            sb.deleteCharAt(sb.length()-1);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        LC402RemoveKDigits removeKDigits = new LC402RemoveKDigits();
        System.out.println(removeKDigits.removeKdigits("1432219",3)); // 1219
        System.out.println(removeKDigits.removeKdigits("10200",1));   // 200
        System.out.println(removeKDigits.removeKdigits("10",2));      // 0
    }
}
