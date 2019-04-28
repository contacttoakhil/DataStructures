package main.java.problems.others;

import java.util.stream.IntStream;

/***
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * Example 1:
 *
 * Input: 1
 * Output: "A"
 * Example 2:
 *
 * Input: 28
 * Output: "AB"
 * Example 3:
 *
 * Input: 701
 * Output: "ZY"
 *
 * https://leetcode.com/problems/excel-sheet-column-title/
 *
 * Solution: Problem is basically converting a integer to a string representing a base-26 number except that "A" corresponds to 1 and not 0.
 */
public class LC168ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n = n / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        LC168ExcelSheetColumnTitle columnTitle = new LC168ExcelSheetColumnTitle();
        System.out.println(columnTitle.convertToTitle(701));
    }

}
