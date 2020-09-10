package main.java.problems.dp;

import java.util.Arrays;

/***
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times.
 * Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 *
 * https://leetcode.com/articles/out-of-boundary-paths/
 *
 * Apporach One : Brute Force
 * In the brute force approach, we try to take one step in every direction and decrement the number of pending moves for each step taken. Whenever we reach out of the boundary while taking the steps, we deduce that one extra path is
 * available to take the ball out.
 *
 * Approach Two: Recursion with Memoization
 * In the brute force approach, while going through the various branches of the recursion tree, we could reach the same position with the same number of moves left. Thus, a lot of redundant function calls are made with the same set
 * of parameters leading to a useless increase in runtime. We can remove this redundancy by making use of a memoization array, memo such that memo[i][j][k] is used to store the number of possible moves leading to a path out
 * of the boundary if the current position is given by the indices (i, j)(i,j) and number of moves left is kk.
 */
public class LC576OutOfBoundaryPaths {
    private int M = 1000000007;

    // Approach One : Brute Force       Time: O(4^N)  Space: O(N)
    public int findPathsBF(int m, int n, int N, int i, int j) {
        if (i == m || j == n || i < 0 || j < 0) return 1;
        if (N == 0) return 0;
        return findPathsBF(m, n, N - 1, i - 1, j)
                + findPathsBF(m, n, N - 1, i + 1, j)
                + findPathsBF(m, n, N - 1, i, j - 1)
                + findPathsBF(m, n, N - 1, i, j + 1);
    }

    // Approach One : Brute Force       Time complexity : O(mnN). We need to fill the memo array once with dimensions m×n×N. Here, m, n refer to the number of rows and columns of the given grid respectively. N refers to the total number of allowed moves.
    // Space: O(mnN)
    public int findPathsRM(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N + 1];
        for (int[][] l : memo) for (int[] sl : l) Arrays.fill(sl, -1);
        return findPathsRMHelper(m, n, N, i, j, memo);
    }

    public int findPathsRMHelper(int m, int n, int N, int i, int j, int[][][] memo) {
        if (i == m || j == n || i < 0 || j < 0) return 1;
        if (N == 0) return 0;
        if (memo[i][j][N] >= 0) return memo[i][j][N];
        int hor = (findPathsRMHelper(m, n, N - 1, i - 1, j, memo) + findPathsRMHelper(m, n, N - 1, i + 1, j, memo)) % M;
        int ver = (findPathsRMHelper(m, n, N - 1, i, j - 1, memo) + findPathsRMHelper(m, n, N - 1, i, j + 1, memo)) % M;
        memo[i][j][N] = (hor + ver) % M;
        return memo[i][j][N];
    }

    // Approach Three: DP
    public int findPathsDP(int m, int n, int N, int x, int y) {
        int M = 1000000000 + 7;
        int dp[][] = new int[m][n];
        dp[x][y] = 1;
        int count = 0;
        for (int moves = 1; moves <= N; moves++) {
            int[][] temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == m - 1) count = (count + dp[i][j]) % M;
                    if (j == n - 1) count = (count + dp[i][j]) % M;
                    if (i == 0) count = (count + dp[i][j]) % M;
                    if (j == 0) count = (count + dp[i][j]) % M;
                    temp[i][j] = ( (dp[i - 1][j] + dp[i + 1][j]) % M + (dp[i][j - 1] + dp[i][j + 1])%M ) % M;
/*
                    temp[i][j] = (
                            ((i > 0 ? dp[i - 1][j] : 0) + (i < m - 1 ? dp[i + 1][j] : 0)) % M +
                                    ((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M
                    ) % M;
*/
                }
            }
            dp = temp;
        }
        return count;
    }

    public static void main(String[] args) {
        LC576OutOfBoundaryPaths outOfBoundaryPaths = new LC576OutOfBoundaryPaths();
        System.out.println(outOfBoundaryPaths.findPathsBF(2,2,2,0,0));
        System.out.println(outOfBoundaryPaths.findPathsRM(2,2,2,0,0));
        System.out.println(outOfBoundaryPaths.findPathsDP(2,2,2,0,0));
    }
}
