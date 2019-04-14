package main.java.problems.backtracking;

public class LC51NQueens {
    public boolean solve(boolean[][] board, int column) {

        int numOfRows = board.length;

        if(column >= numOfRows) return true;    // <- base case

        for(int row = 0; row < numOfRows; row++) {          // <- for all choices
            if(isSafe(board, row, column)) {                // if current choice works?
                placeQueen(board, row, column);             // do it
                if(solve(board, column + 1)) return true;   // we can move forward? great
                removeQueen(board, row, column);                    // else revert or compute
            }
        }
        return false;
    }

    private void removeQueen(boolean[][] board, int row, int column) {
        board[row][column] = false;
    }

    private void placeQueen(boolean[][] board, int row, int column) {
        board[row][column] = true;
    }

    private boolean isSafe(boolean[][] board, int row, int column) {
        int i, j;
        int numOfRows = board.length;

        for (i = 0; i < column; i++)  // For this row, verify all cols on left (as we are iterating over rows (in solve method) to keep one queen in a row).
            if (board[row][i])
                return false;

        for (i=row, j=column; i>=0 && j>=0; i--, j--)       // left upper diagonal
            if (board[i][j])
                return false;

        for (i=row, j=column; j>=0 && i<numOfRows; i++, j--)    // left lower diagonal
            if (board[i][j])
                return false;

        return true;
    }

    public void printSolution(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print("  "  + (board[i][j] ? "Q" : "*"));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        boolean[][] board = {{false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };
        LC51NQueens nq = new LC51NQueens();
        boolean solutionExists = nq.solve(board, 0);
        if(solutionExists)
            nq.printSolution(board);
        else
            System.out.println("No Solution");
    }
}
