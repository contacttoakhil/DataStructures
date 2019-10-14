package main.java.problems.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string, find the length of the longest substring T that contains at most K distinct characters.
 * Also check LC159LongestSubstringAtMostTwoDistinctCharacters
 */
public class LC340LongestSubstringAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinctConcised(String s, int k) {
        int[] map = new int[256];
        int left = 0, right = 0, res = Integer.MIN_VALUE, counter = 0;
        while (right < s.length()) {
            final char c1 = s.charAt(right);
            if (map[c1] == 0) counter++;
            map[c1]++;      right++;
            while (counter > k) {
                final char c2 = s.charAt(left);
                if (map[c2] == 1) counter--;
                map[c2]--;     left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

        public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character,Integer> map = new HashMap<>();
        int left = 0, right = 0, counter = 0, res = 0;
        while(right < s.length()){
            char charAtRight = s.charAt(right);
            map.put(charAtRight, map.getOrDefault(charAtRight, 0) + 1);
            if(map.get(charAtRight) == 1) counter++;//new char
            right++;
            while(counter > k){
                char charAtLeft = s.charAt(left);
                map.put(charAtLeft, map.get(charAtLeft) - 1);
                if(map.get(charAtLeft) == 0){
                    counter--;
                }
                left++;
            }
            res = Math.max(res, right-left);
        }
        return res;
    }

    public static void main(String[] args) {
        LC340LongestSubstringAtMostKDistinctCharacters longestSubstringAtMostKDistinctCharacters = new LC340LongestSubstringAtMostKDistinctCharacters();
        System.out.println(longestSubstringAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(longestSubstringAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinctConcised("eceba", 2));
    }
}
