package main.java.problems.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 * The problem becomes much more difficult after the spaces are removed. Now we need to determine which part matchs which part by ourselves.
 *
 */
public class LC291WordPatternII {

    private Map<Character, String> map = new HashMap<>();
    private Set<String> set = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String str) {
        return backtrack(str, 0, pattern, 0);
    }

    private boolean backtrack(String str, int strIdx, String pattern, int ptIdx) {
        if (strIdx == str.length() && ptIdx == pattern.length()) return true;
        if (strIdx == str.length() || ptIdx == pattern.length()) return false;
        char currentChar = pattern.charAt(ptIdx);
        if (map.containsKey(currentChar)) {     // pattern char exists ?
            String s = map.get(currentChar);
            if(!str.startsWith(s, strIdx))      // str must start with this char equivalent else its wrong.
                return false;
            return backtrack(str, strIdx + s.length(), pattern, ptIdx+1); // Yay! it matches, continue for rest
        }
        // pattern char is not in map
        for(int k = strIdx; k < str.length(); k++) {
            String p = str.substring(strIdx, k + 1);
            if(set.contains(p))
                continue;
            map.put(currentChar, p);    // create or update
            set.add(p);
            // continue to match the rest
            if(backtrack(str, k+1, pattern, ptIdx+1))
                return true;
            // backtracking
            map.remove(currentChar);
            set.remove(p);
        }
        return false;
    }


    public static void main(String[] args) {
        LC291WordPatternII wordPatternII = new LC291WordPatternII();
        System.out.println(wordPatternII.wordPatternMatch("abab","redblueredblue")); // true
        //System.out.println(wordPatternII.wordPatternMatch("aaaa","asdasdasdasd"));   // true
        //System.out.println(wordPatternII.wordPatternMatch("aabb","xyzabcxzyabc"));   // false
    }
}
