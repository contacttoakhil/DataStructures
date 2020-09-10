package main.java.problems.arrays;

import java.util.Arrays;

/***
 * You are given an n r n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Examples
 * [                                            [
 *      [1,2,3],                                    [7,4,1],
 *      [4,5,6],             == rotate ==>          [8,5,2],
 *      [7,8,9]                                     [9,6,3]
 * ],                                           ]
 *
 * [                                            [
 *      [5, 1, 9, 11],                              [15, 13, 2, 5],
 *      [2, 4, 8, 10],       == rotate ==>          [14, 3, 4, 1],
 *      [13, 3, 6, 7],                              [12, 6, 8, 9],
 *      [15, 14, 12, 16]                            [16, 7, 10, 11]
 * ]                                            ]
 *
 * https://leetcode.com/problems/rotate-image/
 *
 */
public class LC48RotateImage {
    public void rotate(int[][] matrix) {
        int matrixSize = matrix.length - 1;
        for(int i = 0; i < matrix.length/2; i++) {          // loop over for all layers
            int first = i;                      // first element in this layer
            int last = matrixSize - i;               // last element in this layer
            for (int j = first; j < last; ++j) {
                // exchange four elements
                int t1 =  matrix[matrixSize - j][i];
                int t2 = matrix[matrixSize - i][matrixSize - j];
                int t3 = matrix[j][matrixSize - i];
                int t4 =  matrix[i][j];

                matrix[i][j] = t1;                    //left to top
                matrix[matrixSize - j][i] = t2;    //bottom to left
                matrix[matrixSize - i][matrixSize - j] = t3;    //right to bottom
                matrix[j][matrixSize - i] = t4;                                    //top to right
            }
        }
    }

    public void rotate2(int[][] matrix) {
        transpose(matrix);
        flipColumns(matrix);
    }

    /***
     * [                                            [
     *      [5, 1, 9, 11],                              [5, 2, 13, 15],
     *      [2, 4, 8, 10],       == transpose ==>       [1, 4, 3, 14],
     *      [13, 3, 6, 7],                              [9, 8, 6, 12],
     *      [15, 14, 12, 16]                            [11, 10, 7, 16]
     * ]                                            ]
     *  *
     */
    private void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }
    /***
     * [                                            [
     *      [5, 2, 13, 15],                             [15, 13, 2, 5],
     *      [1, 4, 3, 14],       == flip columns ==>    [14, 3, 4, 1],
     *      [9, 8, 6, 12],                              [12, 6, 8, 9],
     *      [11, 10, 7, 16]                            [16, 7, 10, 11]
     * ]                                            ]
     *  *
     */
    private void flipColumns(int[][] matrix) {
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                swap(matrix, i, j, i, matrix.length-1-j);
            }
        }
    }
    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }
    public static void main(String[] args) {
        LC48RotateImage rotateImage = new LC48RotateImage();
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        System.out.println(Arrays.deepToString(matrix));
        rotateImage.rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
