package main.java.problems.arrays;

public class SpiralArray {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        spiralMatrix(matrix);
        // 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
    }
    static void spiralMatrix(int[][] matrix) {
        int i, k=0, l=0;
        int m = matrix.length;
        int n = matrix[0].length;

        while(k< m && l<n) {
            //print first row
            for(i=l; i<n; ++i) {
                System.out.print(matrix[k][i] + " ");
            }
            k++;
            //print lat column for other cols
            for(i=k; i<m; ++i) {
                System.out.print(matrix[i][n-1] + " ");
            }
            n--;
            // print last row for remaining rows
            if( k<m) {
                for(i=n-1; i>=l; --i) {
                    System.out.print(matrix[m-1][i] + " ");
                }
                m--;
            }
            if(l<n) {
                for (i = m-1; i >= k ; --i) {
                    System.out.print(matrix[i][l] + " ");
                }
                l++;
            }
        }
    }
}
