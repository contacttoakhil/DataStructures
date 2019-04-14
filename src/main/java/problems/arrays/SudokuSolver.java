package main.java.problems.arrays;

import static java.lang.Math.sqrt;

public class SudokuSolver {

    private int[][] board;
    private int boardSize;

    public static final int EMPTY_VALUE = 0;
    private static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 9;


    public SudokuSolver(int[][] board) {
        this.boardSize = board.length;
        //if(boardSize != board[0].length) throw new RuntimeException("Invalid Board");
        this.board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    private boolean solve() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if(board[row][col] == EMPTY_VALUE) {
                    for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                        board[row][col] = k;
                        if(isValid(row, col, k) && solve()) {
                            return true;
                        }
                        board[row][col] = EMPTY_VALUE;
                    }
                }
            }
            return false;
        }
        return true;  // Sudoku solved so return true
    }

    private boolean numIsInRow(int row, int col, int num) {
        for (int c = 0; c < boardSize; c++) {
            if(board[row][c] == num)
                return true;
        }
        return false;
    }

    private boolean numIsInCol(int row, int col, int num) {
        for (int r = 0; r < boardSize; r++) {
            if(board[r][col] == num)
                return true;
        }
        return false;
    }

    private boolean numIsInBlock(int row, int col, int num) {
        //int r = row - row % (int) sqrt(boardSize);
        //int c = col - col % (int) sqrt(boardSize);
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r+3; i++) {
            for (int j = c; j < c+3; j++) {
                if(board[i][j] == num)
                    return true;
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int num) {
        return !numIsInRow(row, col,num) && !numIsInCol(row, col, num) && !numIsInBlock(row, col, num);
    }

    private void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*int[][] board = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };*/

        int[][] board = {
                {9,0,0,1,0,0,0,0,5},
                {0,0,5,0,9,0,2,0,1},
                {8,0,0,0,4,0,0,0,0},
                {0,0,0,0,8,0,0,0,0},
                {0,0,0,7,0,0,0,0,0},
                {0,0,0,0,2,6,0,0,9},
                {2,0,0,3,0,0,0,0,6},
                {0,0,0,2,0,0,9,0,0},
                {0,0,1,9,0,4,5,7,0},
        };

        SudokuSolver ss = new SudokuSolver(board);
        ss.print();
        if(ss.solve()) {
            System.out.println("Solution available");
            ss.print();
        }
        else
            System.out.println("No Solution");
    }
}

//https://medium.com/@ssaurel/build-a-sudoku-solver-in-java-part-1-c308bd511481
//https://www.baeldung.com/java-sudoku