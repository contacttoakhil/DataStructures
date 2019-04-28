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

    public static void main(String[] args) {
        LC48RotateImage rotateImage = new LC48RotateImage();
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        System.out.println(Arrays.deepToString(matrix));
        rotateImage.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
