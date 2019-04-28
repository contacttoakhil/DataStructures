package main.java.problems.dp;

/***
 * Given a m r n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 * https://leetcode.com/problems/minimum-path-sum/
 */
public class LC64MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length==0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];

        // initialize top row
        for(int i=1; i<cols; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        // initialize left column
        for(int j=1; j<rows; j++){
            dp[j][0] = dp[j-1][0] + grid[j][0];
        }

        // fill up the dp table
        for(int i=1; i<rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? (dp[i][j - 1] + grid[i][j]) : (dp[i - 1][j] + grid[i][j]);
            }
        }
        return dp[rows-1][cols-1];
    }
}
