package main.java.problems.others;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class LC118PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < i + 1 ; j++) {
                if(j == 0 || j == i) {
                    list.add(1); continue;
                }
                int a = result.get(i - 1).get(j - 1);
                int b = result.get(i - 1).get(j);
                list.add(a + b);
            }
            result.add(list);
        }
        return result;
    }

}
