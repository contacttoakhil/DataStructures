package main.java.problems.arrays;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a matrix of m r n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * https://leetcode.com/problems/spiral-matrix/
 */
public class LC54SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();

        if (matrix.length == 0) {
            return result;
        }

        int startingRow = 0;        int endingRow = matrix.length-1;
        int startingCol = 0;        int endingCol = matrix[0].length - 1;

        while (startingRow <= endingRow && startingCol <= endingCol) {
            for (int c = startingCol; c <= endingCol; c ++) {                       // Traverse Right
                result.add(matrix[startingRow][c]);
            }
            startingRow++;

            for (int c = startingRow; c <= endingRow; c ++) {                       // Traverse Down
                result.add(matrix[c][endingCol]);
            }
            endingCol--;

            if (startingRow <= endingRow) {
                for (int c = endingCol; c >= startingCol; c --) {                   // Traverse Left
                    result.add(matrix[endingRow][c]);
                }
            }
            endingRow--;

            if (startingCol <= endingCol) {
                for (int c = endingRow; c >= startingRow; c --) {                   // Traverse Up
                    result.add(matrix[c][startingCol]);
                }
            }
            startingCol ++;
        }

        return result;
    }
}
