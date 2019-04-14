package main.java.problems.arrays;

/***
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * https://leetcode.com/problems/rotate-image/
 *
 */
public class LC48RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n<=1)    return;
        for(int i=0; i<n/2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int t = matrix[i][j];
                //left to top
                matrix[i][j] = matrix[n - 1 - j][i];
                //bottom to left
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                //right to bottom
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                //top to right
                matrix[j][n - 1 - i] = t;
            }
        }
    }
}
