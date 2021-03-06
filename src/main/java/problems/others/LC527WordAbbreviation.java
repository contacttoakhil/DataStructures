package main.java.problems.others;

import java.util.*;

/***
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 *
 * Example:
 * Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 *
 * Note:
 * 1) Both n and the length of each word will not exceed 400.
 * 2) The length of each word is greater than 1.
 * 3) The words consist of lowercase English letters only.
 * 4) The return answers should be in the same order as the original array.
 *
 * Solution: Make abbreviation for each word. Then, check each word, if there are some strings which have same abbreviation with it, increase the prefix.
 */
public class LC527WordAbbreviation {

    public List<String> wordsAbbreviation(List<String> dict) {
        int len=dict.size();
        String[] ans = getAbbreviations(dict); // ans:[l2e,god,i6l,me,i6t,i6l,i7n,f2e,i7n]
        int[] prefix=new int[len];
        Arrays.fill(prefix,1);
        for (int i=0; i<len; i++) {
            while (true) {
                Set<Integer> set=new HashSet<>();
                for (int j=i+1;j<len;j++) {
                    if (ans[j].equals(ans[i])) set.add(j); // check all strings with the same abbreviation
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int k: set)
                    ans[k] = makeAbbr(dict.get(k), ++prefix[k]); // increase the prefix
            }
        }
        return Arrays.asList(ans);
    }

    // Get abbreviations for all strings with prefix length 1.
    private String[] getAbbreviations(List<String> dict) {
        String[] ans=new String[dict.size()];
        for (int i=0; i<dict.size(); i++) {
            ans[i] = makeAbbr(dict.get(i), 1); // make abbreviation for each string
        }
        return ans;
    }
    private String makeAbbr(String s, int k) {
        if (k >= s.length() - 2) return s;             // s:"internal"  k:3
        StringBuilder builder=new StringBuilder();
        builder.append(s.substring(0, k));            // k:3 -> "int"          k:5 -> "inter"
        builder.append(s.length()-1-k);               //     -> "int4"             -> "inter2"
        builder.append(s.charAt(s.length()-1));       //     -> "int4l"            -> "inter2l"
        return builder.toString();
    }

    public static void main(String[] args) {
        LC527WordAbbreviation wordAbbreviation = new LC527WordAbbreviation();
        System.out.println(wordAbbreviation.wordsAbbreviation(Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"))); //[l2e, god, internal, me, i6t, interval, inte4n, f2e, intr4n]
    }
}
