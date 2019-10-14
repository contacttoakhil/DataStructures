package main.java.problems.arrays;

import java.util.Arrays;

/***
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Example:
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 *
 * https://leetcode.com/problems/game-of-life/
 */
public class LC289GameOfLife {


    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col, board);
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3))    // Rule 1 or Rule 3 (-1 signifies the cell is now dead but originally was live)
                    board[row][col] = -1;
                if (board[row][col] == 0 && liveNeighbors == 3)                           // Rule 4: 2 signifies the cell is now live but was originally dead.
                    board[row][col] = 2;
            }
        }
        // Get the final representation for the newly updated board.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = (board[row][col] > 0) ? 1 : 0;
            }
        }
    }

    private int countLiveNeighbors(int r, int c, int[][] boardCopy) {
        int rows = boardCopy.length;
        int cols = boardCopy[0].length;
        int[] neighbors = {0, 1, -1};
        int liveNeighbors = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(neighbors[i] == 0 && neighbors[j] == 0) continue;
                int nr = (r + neighbors[i]);
                int nc = (c + neighbors[j]);
                if ((nr < rows && nr >= 0) && (nc < cols && nc >= 0) && (Math.abs(boardCopy[nr][nc]) == 1)) {
                    liveNeighbors += 1;
                }
            }
        }
        return liveNeighbors;
    }

    private int[][] makeCopy(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] copyBoard = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }
        return copyBoard;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        LC289GameOfLife gameOfLife = new LC289GameOfLife();
        gameOfLife.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}
