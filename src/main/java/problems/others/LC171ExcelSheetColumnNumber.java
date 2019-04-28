package main.java.problems.others;

/***
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * Example 1:
 *
 * Input: "A"
 * Output: 1
 * Example 2:
 *
 * Input: "AB"
 * Output: 28
 * Example 3:
 *
 * Input: "ZY"
 * Output: 701
 *
 * https://leetcode.com/problems/excel-sheet-column-number/
 *
 * Solution: Problem is basically converting a string representing a base-26 number to corresponding integer except that "A" corresponds to 1 and not 0.
 */
public class LC171ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        int result = 0;
        for (char c : s.toCharArray()) {
            result = result * 26 + (c - 'A') + 1;
        }
        return result;
    }

    public int titleToNumberJava8(String s) {
        return s.chars().reduce(0, (result,c) -> result * 26 + c - 'A' + 1);
    }

    public static void main(String[] args) {
        LC171ExcelSheetColumnNumber excelSheetColumnNumber = new LC171ExcelSheetColumnNumber();
        System.out.println(excelSheetColumnNumber.titleToNumber("ZY"));
        System.out.println(excelSheetColumnNumber.titleToNumberJava8("ZY"));
    }
}
