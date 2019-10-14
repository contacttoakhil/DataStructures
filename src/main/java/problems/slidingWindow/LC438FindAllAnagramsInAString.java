package main.java.problems.slidingWindow;

import java.util.*;

/***
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *
 * Hints:
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92015/shortestconcise-java-on-sliding-window-solution
 *
 */
public class LC438FindAllAnagramsInAString {

    public List<Integer> findAnagramsConcised(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(p.length()> s.length()) return result;
        int[] map = new int[256];
        for(char c : p.toCharArray())
            map[c]++;
        int left = 0, right = 0, counter = p.length();
        while(right < s.length()){
            final char charAtRight = s.charAt(right);
            if(map[charAtRight] > 0) counter--; // means char exists in p so decrease the counter
            map[charAtRight]--; right++;

            if(counter == 0) result.add(left);

            if(right - left == p.length()) {
                final char charAtLeft = s.charAt(left);
                if(map[charAtLeft] >= 0) counter++;
                map[charAtLeft]++; left++;
            }
        }
        return result;
    }

    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<>();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        int counter = map.size();

        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
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
                if(right-left == t.length())
                    result.add(left);
                left++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        LC438FindAllAnagramsInAString findAllAnagramsInAString = new LC438FindAllAnagramsInAString();
        System.out.println(findAllAnagramsInAString.findAnagrams("abab", "ab")); // [0,1,2]
        System.out.println(findAllAnagramsInAString.findAnagrams("cbaebabacd", "abc"));  // [0,6]
    }
}
