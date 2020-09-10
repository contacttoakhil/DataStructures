package main.java.problems.others;

/***
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 *
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 *
 * Input: "abcd"
 * Output: "dcbabcd"
 *
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class LC214ShortestPalindrome {
    public String shortestPalindrome(String s) {
        int left=0, right = s.length()-1;
        while(right >= 0){
            if(s.charAt(left) == s.charAt(right))
                left++;
            right--;
        }
        if(left==s.length())
            return s;
        String suffix = s.substring(left);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String mid = shortestPalindrome(s.substring(0, left));
        return prefix+mid+suffix;
    }

    public static void main(String[] args) {
        String result = new LC214ShortestPalindrome().shortestPalindrome("abcd");
        System.out.println(result);
    }
}
