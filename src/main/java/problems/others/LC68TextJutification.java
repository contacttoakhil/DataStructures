package main.java.problems.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * Example 1:
 *
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *              because the last line must be left-justified instead of fully-justified.
 *              Note that the second line is also left-justified becase it contains only one word.
 * Example 3:
 *
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class LC68TextJutification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if(words.length == 0 || maxWidth == 0) {
            result.add("");
            return result;
        }
        for(int i = 0, wordIdx; i < words.length; i = wordIdx) {
            int len = -1; // Need to skip the space for last word hence start len = -1
            //check how many words fit into the line
            for(wordIdx = i; wordIdx <words.length && len + words[wordIdx].length() + 1 <= maxWidth; wordIdx++){
                len += words[wordIdx].length() + 1; // 1 extra for the space
            }
            // calculate number of extra spaces.
            int evenlyDistributedSpaces = 1; //If we don't enter loop then default value
            int extraSpaces = 0;
            //w is already ponting to next index and -1 since n words have n-1 gaps
            //between them
            int numOfGapsBwWords = wordIdx-i-1;
      /*Moreover we don't need to do this computation if we reached the last word
        of array or there is only one word that can be accommodate on the line
        then we don't need to do any justify text. In both cases text can be left,
        left-aligned */
            if(wordIdx != i+1 && wordIdx != words.length) {
                //additional 1 for the default one space between words
                evenlyDistributedSpaces = ((maxWidth-len) / numOfGapsBwWords) + 1;
                extraSpaces = (maxWidth-len)%numOfGapsBwWords;
            }
            StringBuilder sb = new StringBuilder(words[i]);
            for(int j = i+1; j < wordIdx; j++) {
                for(int s = 0; s < evenlyDistributedSpaces; s++)
                    sb.append(' ');
                if(extraSpaces > 0) {
                    sb.append(' ');
                    extraSpaces--;
                }
                sb.append(words[j]);
            }
      /* Handle above two cases we skipped, where only one word on line or we
         reached end of word array.Last line should remain left aligned.*/
            int remaining = maxWidth-sb.length();
            while(remaining > 0) {
                sb.append(' ');
                remaining--;
            }
            result.add(sb.toString());
        }
        return result;
    }

    public List<String> fullJustify2(String[] words, int maxWidth) {
        int currentLineLength = 0;
        List<String> result = new ArrayList<>();
        List<StringBuilder> currentLine = new ArrayList<>();
        for(String word : words) {
            if(currentLineLength + word.length() + currentLine.size() > maxWidth) { // existing words' length in line + current word + spaces
                // example: currentLine: ["This", "is", "an"] and word is "example" then maxWidth:16 and currentLineLength: 8
                for (int i = 0; i<maxWidth-currentLineLength; ++i) {    // distribute equally between words in current line
                    int mw = Math.max(currentLine.size()-1, 1); // max of (total-words-in-line -1, 1); max(3-1,1) => return 2.
                    currentLine.get(i % mw).append(' ');
                    // 0%2 == 0 => ["This_", "is", "an"]     1%2 == 0 => ["This_", "is_", "an"]    2%2 == 0 => ["This__", "is_", "an"]   3%2 == 0 => ["This__", "is__", "an"]    4%2 == 0 => ["This___", "is__", "an"]
                    // 5%2 == 0 => ["This___", "is___", "an"]     6%2 == 0 => ["This____", "is___", "an"]    2%2 == 0 => ["This____", "is____", "an"]   0%2 == 0 => ["This_", "is", "an]    0%2 == 0 => ["This_", "is", "an]
                }
                result.add(String.join("",currentLine));
                currentLineLength = 0;
                currentLine = new ArrayList<>();
            }
            currentLine.add(new StringBuilder(word));
            currentLineLength += word.length();
        }
        // handle last line
        String str = String.join(" ", currentLine); // currentLine: ["justification."] str = "justification."
        List<String> spaces = Collections.nCopies(maxWidth - currentLineLength - (currentLine.size() - 1), " "); // spaces to justify
        result.add(str + String.join("",spaces));
        return result;
    }

    public static void main(String[] args) {
        LC68TextJutification textJutification = new LC68TextJutification();
        System.out.println(textJutification.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."},16));
        System.out.println(textJutification.fullJustify2(new String[]{"This", "is", "an", "example", "of", "text", "justification."},16));
    }
}
