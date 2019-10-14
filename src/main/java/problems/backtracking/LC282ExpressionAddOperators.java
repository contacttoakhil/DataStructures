package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 *
 * https://leetcode.com/problems/expression-add-operators/
 *
 * Solution: We want to output all possible solutions so we will use DFS. How do we split the problem into multiple searches?
 * a) Addition and Subtraction is easy. When we cut out a number, add or subtract the previously calculated result, we can substitute the remaining numeric string and the new calculation result into the children search.
 * Our calculations are the same as the goal, and we complete a search.
 * b) However, how is multiplication handled? We need to use a variable to record the value of the current multiplication of the multiplication, until the multiplication is completed, and when the children plus or minus
 * sign is encountered and then counted into the calculation result
 *
 * There are two situations here:
 * i) The multiplication sign is preceded by a plus or minus sign e.g. 2+3*4, while calculating the result in 2, we will add 3 to 2 and the result will be 5. At the time of 4, because we chose the multiplication
 * sign before 4, here 3 should be multiplied by 4, instead of adding 2 to 3. So when calculating the result, we must subtract 3 from the previous (5) to get 2 and then add 3 times to 4 to get 2+12=14, so 14 is the result of the calculation until 4th.
 * ii) The multiplication sign is also preceded by a multiplication sign. If 2+3*4*5, here at 4 the the result is 14 (2+3*4) , children we have multiply but when we go to 5, we have to remove the 3*4 just added. And
 * then add 3*4*5, that is 14 - 3*4 + 3*4*5 = 62. The result of 5 is 62.
 *
 * Some edge cases to be considered:
 * a) overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
 * b) 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
 * c) a little trick is that we should save the value that is to be multiplied in the children recursion (multi).
 */
public class LC282ExpressionAddOperators {

    private List<String> result = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        backtrack(new StringBuilder(), num.toCharArray(), 0, target, 0, 0);
        return result;

    }
    // DFS with backtracking
    private void backtrack(StringBuilder temp, char[] input, int pos, int target, long prev, long multi) {
        if(pos == input.length) {
            if(target == prev)
                result.add(temp.toString());
            return;
        }
        long curr = 0;
        for(int i = pos; i < input.length; i++) {
            if(input[pos] == '0' && i != pos) break;
            curr = 10 * curr + input[i] - '0';      // get digit (single or multiple e.g. 1 or 12 or 123).
            if(curr > Integer.MAX_VALUE) break;
            int len = temp.length();
            if(pos == 0) {
                backtrack(temp.append(curr), input, i + 1, target, curr, curr);
                temp.setLength(len);
                continue;
            }
            backtrack(temp.append("+").append(curr), input, i + 1, target, prev + curr, curr);
            temp.setLength(len);
            backtrack(temp.append("-").append(curr), input, i + 1, target, prev - curr, -curr);
            temp.setLength(len);
            backtrack(temp.append("*").append(curr), input, i + 1, target, prev - multi + multi * curr, multi * curr);
            temp.setLength(len);
        }
    }

    public static void main(String[] args) {
        LC282ExpressionAddOperators expressionAddOperatrors = new LC282ExpressionAddOperators();
        System.out.println(expressionAddOperatrors.addOperators("123456", 30));
    }
}

/*
T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
Thus T(n) = 4T(n-1);
So the time complexity is O(4^n)
 */
