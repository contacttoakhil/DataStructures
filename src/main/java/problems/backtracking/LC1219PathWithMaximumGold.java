package main.java.problems.backtracking;

/**
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * Example 2:
 *
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 *
 * More: https://leetcode.com/tag/backtracking/
 */
public class LC1219PathWithMaximumGold {
    public int getMaximumGold(int[][] grid) {
        int result=0;
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                result = Math.max(result, dfs(grid, i, j));
        return result;
    }
    private int dfs(int[][] grid, int r, int c) {
        if(grid[r][c]==0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int result=0;
        int temp=grid[r][c];
        grid[r][c]=0;
        if(r > 0) result = Math.max(result, temp + dfs(grid,r-1, c));
        if(c > 0) result = Math.max(result, temp + dfs(grid,r,c-1));
        if(r < rows-1) result = Math.max(result, temp + dfs(grid,r+1, c));
        if(c < cols-1) result = Math.max(result, temp + dfs(grid, r,c+1));
        grid[r][c] = temp; // restore value
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0,6,0},
                {5,8,7},
                {0,9,0}
        };
        LC1219PathWithMaximumGold pathWithMaximumGold = new LC1219PathWithMaximumGold();
        System.out.println(pathWithMaximumGold.getMaximumGold(grid));
    }
}
