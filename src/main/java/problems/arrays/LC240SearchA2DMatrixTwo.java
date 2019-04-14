package main.java.problems.arrays;

/***
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 *
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class LC240SearchA2DMatrixTwo {

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if(rows == 0) return false;

        int cols = matrix[0].length - 1;
        int r = rows - 1;
        int c = 0;

        while(r >= 0 && c <= cols){
            if(target < matrix[r][c]){
                r--;
            }else if(target > matrix[r][c]){
                c++;
            }else{
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LC240SearchA2DMatrixTwo searchA2DMatrixTwo = new LC240SearchA2DMatrixTwo();
        int[][] matrix = new int[][] {
                {1,  4,  7, 11, 15},
                {2,  5,  8, 12, 19},
                {3,  6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(searchA2DMatrixTwo.searchMatrix(new int[][]{}, 20));
        System.out.println(searchA2DMatrixTwo.searchMatrix(matrix, 5));
        System.out.println(searchA2DMatrixTwo.searchMatrix(matrix, 20));
    }
}
