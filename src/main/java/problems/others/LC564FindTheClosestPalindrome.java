package main.java.problems.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 *
 *
 * Solution:
 * Let's build a list of candidate answers for which the final answer must be one of those candidates. Afterwards, choosing from these candidates is straightforward.
 *
 * If the final answer has the same number of digits as the input string S, then the answer must be the middle digits + (-1, 0, or 1) flipped into a palindrome. For example, 23456 had middle part 234, and 233,
 * 234, 235 flipped into a palindrome yields 23332, 23432, 23532. Given that we know the number of digits, the prefix 235 (for example) uniquely determines the corresponding palindrome 23532, so all palindromes with
 * larger prefix like 23732 are strictly farther away from S than 23532 >= S.
 *
 * If the final answer has a different number of digits, it must be of the form 999....999 or 1000...0001, as any palindrome smaller than 99....99 or bigger than 100....001 will be farther away from S.
 */
public class LC564FindTheClosestPalindrome {

    public String nearestPalindromic(String n) {
        if(n.length()==1) return String.valueOf(Integer.parseInt(n)-1);
        int halflen = (n.length()+1)/2;
        long half = Long.parseLong(n.substring(0,halflen));

        List<Long> candidates = new ArrayList<>();
        candidates.add(getAllNine(n.length()));
        candidates.add(getAllNine(n.length()-1));
        candidates.add(getOneZero(n.length()));
        candidates.add(getOneZero(n.length()+1));

        getcandidates(candidates,half,n.length());
        long dif = Long.MAX_VALUE;
        long r = Long.parseLong(n);
        String res = "";
        Collections.sort(candidates);
        for(Long ele:candidates){
            if(ele == r){
                continue;
            }
            if(dif>Math.abs(ele-r)){
                dif = Math.abs(ele-r);
                res = String.valueOf(ele);
            }
        }
        return res;
    }

    public void getcandidates(List<Long> ans,long m,int len){
        List<Long> reg = new ArrayList<>();
        reg.add(m);
        reg.add(m-1);
        reg.add(m+1);
        for(Long ele : reg){
            if(len%2==0){
                String str = String.valueOf(ele);
                str+=new StringBuilder(str).reverse().toString();
                ans.add(Long.parseLong(str));
            }else{
                String str = String.valueOf(ele);
                StringBuilder sb = new StringBuilder(str.substring(0,str.length()-1));
                str = str + sb.reverse().toString();
                ans.add(Long.parseLong(str));
            }
        }
    }

    //999....999
    public Long getAllNine(int n){
        String str = "";
        for(int i=0; i<n; i++)
            str += '9';
        return Long.parseLong(str);
    }

    // 1000...0001
    public Long getOneZero(int n){
        return (long)Math.pow(10,n-1)+1;
    }

    public String nearestPalindromic2(String n) {
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
