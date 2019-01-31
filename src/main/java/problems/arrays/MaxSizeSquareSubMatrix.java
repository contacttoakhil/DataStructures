package main.java.problems.arrays;

public class MaxSizeSquareSubMatrix {
    static void compute(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            result[0][i] = matrix[0][i];
        }
        for (int j = 0; j < cols; j++) {
            result[j][0] = matrix[j][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                result[i][j] = (matrix[i][j] == 1) ? min(result[i-1][j], result[i-1][j-1], result[i][j-1]) : 0;
            }
        }

        int max = 0; int maxi = 0; int maxj = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(result[i][j] > max) {
                    max = result[i][j];     maxi = i;   maxj = j;
                }
            }
        }

        for (int i = maxi; i > (maxi - max); i--) {
            for (int j = maxj; j > (maxj - max) ; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    static int min(int a, int b, int c) {
        return Math.min(c, Math.min(a,b));
    }

    public static void main(String[] args) {
        int M[][] =  {{0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};
        compute(M);
    }
}
