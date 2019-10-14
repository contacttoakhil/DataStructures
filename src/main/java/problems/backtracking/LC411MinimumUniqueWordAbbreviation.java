package main.java.problems.backtracking;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * A string such as "word" contains the following abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with thesmallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.
 * Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.
 * Note:
 * In the case of multiple answers as shown in the second example below, you may return any one of them.
 * Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 *
 * Examples:
 * "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
 *
 * "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 *
 * Solution: coombination of LC320GeneralizedAbbreviation and LC408ValidWordAbbreviation
 */
public class LC411MinimumUniqueWordAbbreviation {

    PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(String::length));

    public String minAbbreviation(String target, String[] dictionary) {
        if (target.length() == 0 || dictionary.length == 0) {
            return "";
        }
        backtrack(new StringBuilder(), target,  0, 0);
        while (!queue.isEmpty()) {
            String abbr = queue.poll();
            if(validForDictionary(abbr, dictionary))
                return abbr;
        }
        return "";
    }

    private boolean validForDictionary(String abbr, String[] dictionary) {
        boolean allValid = true;
        for (String word : dictionary) {
            if (validWordAbbreviation(word, abbr)) {
                allValid = false;
                break;
            }
        }
        return allValid;
    }

    private void backtrack(StringBuilder sb, String input, int pos, int count) {
        int len = sb.length();
        if(pos == input.length()) {
            if(count > 0) sb.append(count);
            queue.offer(sb.toString());
        }
        else
        {
            backtrack(sb, input, pos + 1, count + 1);   // abbr c[i]
            if(count > 0) sb.append(count);
            backtrack(sb.append(input.charAt(pos)), input, pos + 1, 0); // no abbrv for c[i]
        }
        sb.setLength(len);
    }

    private boolean validWordAbbreviation(String word, String abbr) {
        int wdIdx = 0, abIdx = 0;
        while (wdIdx < word.length() && abIdx < abbr.length()) {
            if (word.charAt(wdIdx) == abbr.charAt(abIdx)) {
                ++wdIdx;++abIdx;
                continue;
            }
            if (!Character.isDigit(abbr.charAt(abIdx)))
                return false;
            int start = abIdx;
            while (abIdx < abbr.length() && Character.isDigit(abbr.charAt(abIdx)))
                ++abIdx;
            wdIdx = wdIdx +  Integer.valueOf(abbr.substring(start, abIdx));
        }
        return wdIdx == word.length() && abIdx == abbr.length();
    }

    public static void main(String[] args) {
        LC411MinimumUniqueWordAbbreviation minimumUniqueWordAbbreviation = new LC411MinimumUniqueWordAbbreviation();
        String result = minimumUniqueWordAbbreviation.minAbbreviation("apple", new String[]{"blade"});
        System.out.println(result);
        result = minimumUniqueWordAbbreviation.minAbbreviation("apple", new String[]{"plain", "amber", "blade"});
        System.out.println(result);
    }
}
