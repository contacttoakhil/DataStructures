package main.java.problems.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 *
 * Note:
 * Both the string's length and k will not exceed 104.
 *
 * Example 1:
 *
 * Input:
 * s = "ABAB", k = 2
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input:
 * s = "AABABBA", k = 1
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class LC424LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int[] freqMap = new int[128];
        int maxCount = 0;
        int left = 0;
        for(int right = 0; right < s.length(); right++) {
            char charAtRight = s.charAt(right);
            maxCount  = Math.max(maxCount , ++freqMap[charAtRight]);
            // if max character frequency + distance between start and right is greater than k
            // this means we have considered changing more than k charactres. So time to shrink window
            if(right - left - maxCount >= k) {
                char charAtLeft = s.charAt(left);
                freqMap[charAtLeft]--;
                left++;
            }
        }
        return s.length()-left;
    }

    public static void main(String[] args) {
        LC424LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LC424LongestRepeatingCharacterReplacement();
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement("AABABBA" , 1));
    }
}
