package main.java.problems.dp;

/**
 * A table composed of N x M cells, each having a certain quantity of apples, is given. You start from the upper-left corner. At each step you can go down or right one cell. Find the maximum number of apples you can collect.
 */
public class CountingApples {
    int m,n;
    int[][] apples;
    int[][] collect;

    public CountingApples(int m, int n, int[][] apples) {
        this.m = m;
        this.n = n;
        this.apples = apples;
        this.collect = new int[m][n];
    }

    private int computeMaxApples() {
        for(int i=0 ;i<m; i++) {
            for(int j=0 ;j<n; j++) {
                collect[i][j] = apples[i][j] + Math.max((i>0) ? collect[i-1][j] : 0, (j>0)? collect[i][j-1] : 0);
            }
        }
        return collect[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] apples = {
                {1, 2, 3, 4},
                {1, 1, 1, 1},
                {9, 9, 9, 9}
        };
        CountingApples ca = new CountingApples(3,4, apples);
        System.out.println("Max apples: " + ca.computeMaxApples());
    }
}

