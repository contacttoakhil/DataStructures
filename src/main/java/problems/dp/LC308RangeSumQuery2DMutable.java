package main.java.problems.dp;

/***
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * This problem can be solved using segment tree also, check: LC308RangeSumQuery2DMutableUsingSegmentTree
 */
public class LC308RangeSumQuery2DMutable {
    private int[][] dp;
    private int[][] matrix;
    private int rows;
    private int cols;

    public LC308RangeSumQuery2DMutable(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        rows = matrix.length;
        cols = matrix[0].length;
        this.dp = new int[rows+1][cols+1];
        this.matrix = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                update(r, c, matrix[r][c]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (rows == 0 || cols == 0) return;
        int delta = val - matrix[row][col];
        matrix[row][col] = val;
        for (int i = row + 1; i <= rows; i += i & (-i)) {
            for (int j = col + 1; j <= cols; j += j & (-j)) {
                dp[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (rows == 0 || cols == 0) return 0;
        return sum(row2+1, col2+1)  - sum(row1, col2+1) - sum(row2+1, col1) + sum(row1, col1);
    }

    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += dp[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}
        };
        LC308RangeSumQuery2DMutable rangeSumQuery2DMutable = new LC308RangeSumQuery2DMutable(matrix);
        System.out.println(rangeSumQuery2DMutable.sumRegion(2,1,4,3));      //8
        rangeSumQuery2DMutable.update(3, 2, 2);
        System.out.println(rangeSumQuery2DMutable.sumRegion(2,1,4,3));      //10
    }
}
