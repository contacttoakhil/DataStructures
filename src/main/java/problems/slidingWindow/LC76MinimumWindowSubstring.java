package main.java.problems.slidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class LC76MinimumWindowSubstring {

    public String minWindowConcised(String s, String t) {
        int[] freqMap = new int[128];
        int res = Integer.MAX_VALUE, left = 0, right = 0;
        int minLeft = 0, counter = t.length();
        for (int i = 0; i < t.length(); i++)
            freqMap[t.charAt(i)]++;
        while (right < s.length()) {
            // The number of times this number should appear minus one
            if (freqMap[s.charAt(right++)]-- > 0) counter--;
            while (counter == 0) {                        // Shorten the left border
                if (res > right - left) {
                    res = right - left;
                    minLeft = left;
                }
                if (freqMap[s.charAt(left++)]++ == 0) counter++;
            }
        }
        return res == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + res);
    }

    public String minWindow(String s, String t) {
        if(t.length()> s.length()) return "";
        Map<Character, Integer> map = getFreqMap(t);
        int counter = map.size();
        int left = 0, right = 0, begin =0;
        int result = Integer.MAX_VALUE;

        while(right < s.length()){
            char charAtRight = s.charAt(right);
            if( map.containsKey(charAtRight) ){
                map.put(charAtRight, map.get(charAtRight)-1);
                if(map.get(charAtRight) == 0) counter--;
            }
            right++;
            while(counter == 0){
                char charAtLeft = s.charAt(left);
                if(map.containsKey(charAtLeft)){
                    map.put(charAtLeft, map.get(charAtLeft) + 1);
                    if(map.get(charAtLeft) > 0)
                        counter++;
                }
                if(right-left < result){
                    result = right - left;
                    begin = left;
                }
                left++;
            }

        }
        return result == Integer.MAX_VALUE ? "" : s.substring(begin, begin+result);
    }

    private Map<Character, Integer> getFreqMap(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray())
            map.put(c, map.getOrDefault(c,0) + 1);
        return map;
    }

    public static void main(String[] args) {
        LC76MinimumWindowSubstring minimumWindowSubstring = new LC76MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("ABAACBAB", "ABC"));  // "ACB"
        //System.out.println(minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));  // "BANC"
        System.out.println(minimumWindowSubstring.minWindowConcised("ADOBECODEBANC", "ABC"));  // "BANC"
    }

}
