package main.java.problems.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 */
public class LC36ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] grid = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    continue;
                }
                int num = board[r][c] - '1';
                int x = 3 * (r / 3) + num / 3;
                int y = 3 * (c / 3) + num % 3;
                if (rows[r][num] || cols[num][c] || grid[x][y]) {
                    return false;
                }
                rows[r][num] = cols[num][c] = grid[x][y] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC36ValidSudoku validSudoku = new LC36ValidSudoku();
        char[][] sudokuGrid = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        System.out.println(validSudoku.isValidSudoku(sudokuGrid));
    }
}
