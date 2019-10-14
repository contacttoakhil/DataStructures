package main.java.problems.others;

/***
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 *
 * The 'closest' is defined as absolute difference minimized between two integers.
 *
 * Example 1:
 * Input: "123"
 * Output: "121"
 * Note:
 * The input n is a positive integer represented by string, whose length will not exceed 18.
 * If there is a tie, return the smaller one as answer.
 *
 * ref: https://leetcode.com/problems/find-the-closest-palindrome/discuss/147949/Logical-Thinking-with-Java-Code-Beats-98.80
 */
public class LC564FindTheClosestPalindrome {
    public String nearestPalindromic(String n) {
        String result = handleSpecialCases(n);
        if(!"".equals(result)) return result;
        long nVal = Long.parseLong(n);
        int valPalindromeRoot = Integer.valueOf(n.substring(0, (n.length() + 1) / 2));
        boolean isEvenDigits = n.length() % 2 == 0;
        long tmpEqual = Long.parseLong(toPalindromeDigits(valPalindromeRoot, isEvenDigits));
        long tmpBigger = Long.parseLong(toPalindromeDigits(valPalindromeRoot + 1, isEvenDigits));
        long tmpSmaller = Long.parseLong(toPalindromeDigits(valPalindromeRoot - 1, isEvenDigits));
        long distEqual = Math.abs(nVal - tmpEqual);
        long distBigger = Math.abs(nVal - tmpBigger);
        long distSmaller = Math.abs(nVal - tmpSmaller);
        long distMin = 0L;
        if (distEqual == 0)   distMin = Math.min(distBigger, distSmaller);
        else                  distMin = Math.min(distEqual, Math.min(distBigger, distSmaller));
        if (distMin == distSmaller)   return String.valueOf(tmpSmaller);
        if (distMin == distEqual)     return String.valueOf(tmpEqual);
        return String.valueOf(tmpBigger);
    }

    private String toPalindromeDigits(int num, boolean isEvenDigits) {
        String numStr = String.valueOf(num);
        if (isEvenDigits) {
            return numStr + (new StringBuilder(numStr).reverse()).toString();
        }
        return numStr + (new StringBuilder(numStr).reverse().deleteCharAt(0)).toString();
    }

    private String handleSpecialCases(String n) {
        long nVal = Long.parseLong(n);
        // smaller than 10 OR 100, 1000, 10000, ...
        if (nVal <= 10 || ((nVal % 10 == 0) && (n.charAt(0) == '1') && Integer.valueOf(n.substring(1)) == 0)) return String.valueOf(nVal - 1);
        // 11, 101, 1001, 10001, 100001, ...
        if (nVal == 11 || ((nVal % 10 == 1) && (n.charAt(0) == '1') && (n.charAt(n.length() - 1) == '1') && Integer.valueOf(n.substring(1, n.length() - 1)) == 0))  return String.valueOf(nVal - 2);
        // 99, 999, 9999, 99999, ...
        boolean notAllNine = false;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '9') {
                notAllNine = true;
            }
        }
        if (!notAllNine && nVal >= 99)   return String.valueOf(nVal + 2);
        return "";
    }

    public static void main(String[] args) {
        LC564FindTheClosestPalindrome findTheClosestPalindrome = new LC564FindTheClosestPalindrome();
        System.out.println(findTheClosestPalindrome.nearestPalindromic("123"));
    }
}
