package main.java.problems.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 */
public class LC159LongestSubstringAtMostTwoDistinctCharacters {

    int lengthOfLongestSubstringTwoDistinctConcised(String s) {
        int[] map = new int[128];
        int left = 0, right = 0, res = 0, counter = 0;
        while (right < s.length()) {
            final char c1 = s.charAt(right);
            if (map[c1] == 0) counter++;
            map[c1]++;       right++;
            while (counter > 2) {
                final char c2 = s.charAt(left);
                if (map[c2] == 1) counter--;
                map[c2]--;      left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int left = 0, right = 0, counter = 0, res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) == 1) counter++;//new char
            right++;
            while(counter > 2){
                char cTemp = s.charAt(left);
                map.put(cTemp, map.get(cTemp) - 1);
                if(map.get(cTemp) == 0){
                    counter--;
                }
                left++;
            }
            res = Math.max(res, right-left);
        }
        return res;
    }

    public static void main(String[] args) {
        LC159LongestSubstringAtMostTwoDistinctCharacters longestSubstringAtMostTwoDistinctCharacters = new LC159LongestSubstringAtMostTwoDistinctCharacters();
        System.out.println(longestSubstringAtMostTwoDistinctCharacters.lengthOfLongestSubstringTwoDistinct("eceba"));
    }
}
