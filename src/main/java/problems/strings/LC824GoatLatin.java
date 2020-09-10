package main.java.problems.strings;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 *
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 *
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 *
 *
 *
 * Example 1:
 *
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 */
public class LC824GoatLatin {
    public String toGoatLatin(String S) {
        Set<Character> vowel = "aeiouAEIOU".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        StringBuilder res = new StringBuilder();
        int count=0;
        for(String curr:S.split(" ")) {
            if(count++>0) res.append(" ");
            if(vowel.contains(curr.charAt(0)))  res.append(curr);
            else  res.append(curr.substring(1)+curr.charAt(0));
            res.append("ma");
            for(int j=0; j<count; j++)  res.append("a");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LC824GoatLatin goatLatin = new LC824GoatLatin();
        System.out.println(goatLatin.toGoatLatin("I speak Goat Latin"));
    }
}
