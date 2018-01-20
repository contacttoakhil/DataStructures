package main.java.problems.graphs;

import java.util.Objects;

/**
 *
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. For example, the below matrix contains 5 islands
 Input : grid[][] = {{1, 1, 0, 0, 0},
 {0, 1, 0, 0, 1},
 {1, 0, 0, 1, 1},
 {0, 0, 0, 0, 0},
 {1, 0, 1, 0, 1}
 }
 Output : 5
 *
 * At a particular find where we have land we can merge land from 8 directions (left, right, top, bottom, top-left, bottom-left, top-right and bottom-right)
 *
 * Hint: DFS
 * Ref: https://www.geeksforgeeks.org/find-number-of-islands/
 * https://leetcode.com/problems/number-of-islands/description/
 */
public class FindIslands {
    private int m,n;
    private char[][] grid;

    public FindIslands(char[][] grid) {
        validateGrid(grid);
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
    }
    public int compute() {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    count++;
                    compute(grid, i, j);
                }
            }
        }
        return count;
    }

    private void compute(char[][] grid, int i, int j) {
        if( invalidInput(i,j)) {
            return;
        }
        grid[i][j] = 'X';
        compute(grid, i-1, j);  // left
        compute(grid, i+1, j);  // right
        compute(grid, i, j-1);  // up
        compute(grid, i, j+1);  // down
        compute(grid, i-1, j-1);
        compute(grid, i-1, j+1);
        compute(grid, i+1, j-1);
        compute(grid, i+1, j+1);
    }

    private boolean invalidInput(int i, int j) {
        return ((i<0) || (j<0) || (i>=m) || (j>=n) ||(grid[i][j] != 1) );
    }

    private void validateGrid(char[][] grid) {
        Objects.requireNonNull(grid);
        if( (grid.length == 0) || (grid[0].length == 0) ) {
            throw new IllegalArgumentException(" Grid is empty");
        }
    }

    public static void main(String[] args) {
        char grid[][] = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println(new FindIslands(grid).compute());
    }
}
