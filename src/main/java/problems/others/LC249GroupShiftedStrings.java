package main.java.problems.others;

import java.util.*;

/***
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 *
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Return:
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 *
 * Note: For the return value, each inner list's elements must follow the lexicographic order.
 */
public class LC249GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] ss) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: ss){
            String key = getTag(s);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        List<List<String>> ret = new ArrayList<>();
        for(List<String> lst: map.values()){
            Collections.sort(lst); // dont forget to sort.
            ret.add(lst);
        }
        return ret;
    }

    private String getTag(String s){
        int diff = (int)s.charAt(0) - (int)'a';
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray())
            sb.append((c + 26 - diff)%26);
        return sb.toString();
    }

    public static void main(String[] args) {
        LC249GroupShiftedStrings groupShiftedStrings = new LC249GroupShiftedStrings();
        System.out.println(groupShiftedStrings.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }
}
