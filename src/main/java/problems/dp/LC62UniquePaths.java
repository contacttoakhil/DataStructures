package main.java.problems.dp;

/****
 * A robot is located at the top-left corner of a m r n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 * https://leetcode.com/problems/unique-paths/
 */
public class LC62UniquePaths {

    public int uniquePaths(int m, int n) {
        if(m==0 || n==0) return 0;
        if(m==1 || n==1) return 1;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++)      // count of paths to reach any cell in first column is 1
            dp[i][0] = 1;
        for(int j=0; j<n; j++)      // count of paths to reach any cell in first row is 1
            dp[0][j] = 1;
        for(int i=1; i<m; i++)
            for(int j=1; j<n; j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        LC62UniquePaths uniquePaths = new LC62UniquePaths();
        System.out.println("Total paths: " + uniquePaths.uniquePaths(7,3));
    }
}
