package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/***
 * Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Also check {@link main.java.problems.others.LC408ValidWordAbbreviation}
 */

public class LC320GeneralizedAbbreviation {

    List<String> output = new ArrayList<String>();

    public List<String> generateAbbreviations(String word) {
        backtrack(new StringBuilder(), word,0, 0);
        return output;
    }

    public void backtrack(StringBuilder sb, String input, int pos, int count) {
        int len = sb.length();
        if(pos == input.length()) {
            if(count > 0) sb.append(count);
            output.add(sb.toString());
        }
        else
        {
            backtrack(sb, input,pos + 1, count + 1);               // abbr c[i]
            if(count > 0) sb.append(count);                            // not abbr c[i]
            backtrack(sb.append(input.charAt(pos)), input, pos + 1, 0);
        }
        sb.setLength(len);
    }

    public static void main(String[] args) {
        LC320GeneralizedAbbreviation generalizedAbbreviation = new LC320GeneralizedAbbreviation();
        List<String> result2 = generalizedAbbreviation.generateAbbreviations("word");
        System.out.println(result2);
    }
}

// http://shirleyisnotageek.blogspot.com/2016/10/generalized-abbreviation.html
//http://massivealgorithms.blogspot.com/2015/12/leetcode-320-generalized-abbreviation.html

// https://www.leetfree.com/problems/minimum-unique-word-abbreviation.html
// http://massivealgorithms.blogspot.com/2016/10/leetcode-411-minimum-unique-word.html