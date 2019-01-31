package main.java.problems.backtracking;

public class LC79WordSearch {

    public boolean exists(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(compute(board, word, r, c, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean compute(char[][] board, String word, int r, int c, int idx) {
        if(invalidCoordinates(board, r, c)) return false;

        if(board[r][c] == word.charAt(idx)) {
            char temp = board[r][c];
            board[r][c] = '#';
            if(idx == word.length() - 1)
                return true;
            else if (compute(board, word, r+1, c, idx+1) || compute(board, word, r-1, c, idx+1) || compute(board, word, r, c+1, idx+1) || compute(board, word, r, c-1, idx+1))
                return true;
            board[r][c] = temp;
        }

        return false;
    }

    private boolean invalidCoordinates(char[][] board, int r, int c) {
        int rows = board.length;
        int cols = board[0].length;
        boolean result = (r<0 || r>=rows || c<0 || c>=cols) ? true : false;
        return result;
    }

    public static void main(String[] args) {
        LC79WordSearch lc79WordSearch = new LC79WordSearch();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println("ABCCED Exists? " + lc79WordSearch.exists(board, "ABCCED"));
    }
}
