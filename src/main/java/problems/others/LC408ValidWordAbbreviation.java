package main.java.problems.others;

/**
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * A string such as "word" contains only the following valid abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n":
 *
 * Return true.
 * Example 2:
 * Given s = "apple", abbr = "a2e":
 *
 * Return false.
 *
 * Also check {@link main.java.problems.backtracking.LC320GeneralizedAbbreviation}
 */
public class LC408ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
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
        LC408ValidWordAbbreviation validWordAbbreviation = new LC408ValidWordAbbreviation();
        System.out.println(validWordAbbreviation.validWordAbbreviation("internationalization", "i12iz4n"));
        System.out.println(validWordAbbreviation.validWordAbbreviation("word", "w1r1"));
        System.out.println(validWordAbbreviation.validWordAbbreviation("word", "w1r1"));
    }
}
