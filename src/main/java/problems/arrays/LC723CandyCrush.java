package main.java.problems.arrays;

import java.util.Arrays;

public class LC723CandyCrush {
    public int[][] candyCrush(int[][] board) {
        int rows = board.length, cols = board[0].length;
        boolean todo = false;

        // check horizontally
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c + 2 < cols; ++c) {
                int v = Math.abs(board[r][c]);
                if (v!=0 && v == Math.abs(board[r][c+1]) &&
                        v == Math.abs(board[r][c+2])) {
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    todo = true;
                }
            }
        }

        // check vertically
        for (int r = 0; r + 2 < rows; ++r) {     // check vertically
            for (int c = 0; c < cols; ++c) {
                int v = Math.abs(board[r][c]);
                if (v!=0 && v == Math.abs(board[r+1][c]) &&
                        v == Math.abs(board[r+2][c])) {
                    board[r][c] = board[r+1][c] = board[r+2][c] = -v;
                    todo = true;
                }
            }
        }

        // crush
        for (int c = 0; c < cols; ++c) {   // gravity step
            int wr = rows - 1;
            for (int r = rows-1; r >= 0; --r){
                if (board[r][c] > 0)
                    board[wr--][c] = board[r][c];
            }
            while (wr >= 0)
                board[wr--][c] = 0;
        }
        return todo ? candyCrush(board) : board;
    }

    public static void main(String[] args) {
        LC723CandyCrush candyCrush = new LC723CandyCrush();
        int[][] board = new int[][] {
                {110, 5, 112, 113, 114},
                {210, 211, 5, 213, 214},
                {310, 311, 3, 313, 314},
                {410, 411, 412, 5, 414},
                {5, 1, 512, 3, 3},
                {610, 4, 1, 613, 614},
                {710, 1, 2, 713, 714},
                {810, 1, 2, 1, 1},
                {1, 1, 2, 2, 2},
                {4, 1, 4, 4, 1014}
        };
        candyCrush.candyCrush(board);
        System.out.println(Arrays.deepToString(board));
        /*
        [ [0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0],
          [110, 0, 0, 0, 114],
          [210, 0, 0, 0, 214],
          [310, 0, 0, 113, 314],
          [410, 0, 0, 213, 414],
          [610, 211, 112, 313, 614],
          [710, 311, 412, 613, 714],
          [810, 411, 512, 713, 1014]]
         */
    }
}
