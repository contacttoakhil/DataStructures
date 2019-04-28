package main.java.problems.dp;

import java.util.Arrays;

/***
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
 *
 * https://leetcode.com/problems/knight-probability-in-chessboard/
 *
 * Solution:
 * Given a position [r,c] a knight can move to any of the 8 directions so probability is 1/8. We have two base cases:
 * a) If knight is outside the board then the probability that it is with in the board is zero.
 * b) If K = 0 then we have no more moves left and if knight's position [r,c] is within the board then probability is one and we return 1.
 *
 * Recursion
 * We can define the probability for K as the summation of all the probabilities at K-1 divided by 8 (each equally likely to take move to current position at Kth step).
 *
 *                          i=8
 * probability(r,c,K) -> ⎲   probability(nxt_r, nxt_c, K-1)/8 where nxt_r, nxt_c are possible next positions for knight at [r,c].
 *                       ⎳i=1
 *
 * At [r.c] possible moves are:
 * [r+1, c+2]
 * [r+2, c+1]
 * [r+2, c-1]
 * [r+1, c-2]
 * [r-1, c-2]
 * [r-2, c-1]
 * [r-2, c+1]
 * [r-1, c+2]
 *
 * Considering the directions we can keep the dir array:
 * int[] dx = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };  // possible directions to move for row
 * int[] dy = new int[] { -1, -2, -2, -1, 1, 2, 2, 1 };  // possible directions to move for col
 *
 * How to solve using DP?
 * The idea is to keep track for each number of moves the probability that the knight will be on a certain square. Initially the probability is 1 (100%) that it will be on the start square [r,c] and 0 on all other squares.
 * After one move, the probability is 0.125 that it will be on any of the eight squares a knight distance away from the start, and 0 on all the other squares.
 * If any of these 8 squares is outside the board, it's simply ignored - it doesn't affect the probability for any of the other squares.
 *
 *
 */
public class LC688KnightProbabilityInChessboard {

    /***
     * returns probability that knight will be inside the board post K moves.
     * @param N NxN chess board
     * @param K K steps to take
     * @param r starting row
     * @param c starting col
     * @return probability that knight will be inside the board post K moves
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = initializeGrid(N,K);
        for(int step = 1; step <= K; step++) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if(dp[step][row][col] == 0)
                        dp[step][row][col] = computeProbability(dp, row, col, step, N);
                }
            }
        }
        return dp[K][r][c];
    }

    double[][][] initializeGrid(int N, int K) {
        double[][][] dp = new double[K+1][N][N];
        // for zero steps each pos will have probability of 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[0][i][j] = 1;
            }
        }
        return dp;
    }

    boolean inValidCoordinates(int r, int c, int N) {
        return (r < 0 || r > N - 1 || c < 0 || c > N - 1);
    }

    double computeProbability(double[][][] dp, int row, int col, int step, int N) {
        double prob = 0;
        int[] dx = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };  // possible directions to move for row
        int[] dy = new int[] { -1, -2, -2, -1, 1, 2, 2, 1 };  // possible directions to move for col
        for(int dir = 0; dir < 8; dir++) {
            int next_r = row + dx[dir];   int next_c = col + dy[dir];
            if(inValidCoordinates(next_r, next_c, N)) continue;
            prob = prob + 0.125 * dp[step - 1][next_r][next_c];
        }
        return prob;
    }

    public static void main(String[] args) {
        LC688KnightProbabilityInChessboard knightProbabilityInChessboard = new LC688KnightProbabilityInChessboard();
        System.out.println(knightProbabilityInChessboard.knightProbability(3,2,0,0)); // expected output is 0.0625
    }
}
