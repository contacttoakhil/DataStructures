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

    List<String> result = new ArrayList<String>();
    public List<String> generateAbbreviations(String word) {
        backtrack("", word, 0, 0);
        return result;
    }
    private void backtrack(String temp, String word, int pos, int count){
        if(pos == word.length()){
            if(count > 0) temp = temp + count;
            result.add(temp);
            return;
        }
        backtrack(temp, word, pos + 1, count + 1);
        backtrack(temp +(count>0?count:"") + word.charAt(pos), word, pos+1, 0);
    }

    public static void main(String[] args) {
        LC320GeneralizedAbbreviation generalizedAbbreviation = new LC320GeneralizedAbbreviation();
        List<String> result2 = generalizedAbbreviation.generateAbbreviations("word"); // [4, 3d, 2r1, 2rd, 1o2, 1o1d, 1or1, 1ord, w3, w2d, w1r1, w1rd, wo2, wo1d, wor1, word]
        System.out.println(result2);
    }
}

// http://shirleyisnotageek.blogspot.com/2016/10/generalized-abbreviation.html
//http://massivealgorithms.blogspot.com/2015/12/leetcode-320-generalized-abbreviation.html

// https://www.leetfree.com/problems/minimum-unique-word-abbreviation.html
// http://massivealgorithms.blogspot.com/2016/10/leetcode-411-minimum-unique-word.html