package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC51NQueens {
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n == 0 || n == 2 || n == 3)
            return result;
        backtrack(0, new int[n]);
        return result;
    }

    private void backtrack(int row, int[] cols) {
        int n = cols.length;
        if(row == n){
            result.add(populateSolution(cols));
            return;
        }
        for (int c = 0; c < n; c++) {
            if(isValid(row, c, cols)){
                cols[row] = c;
                backtrack(row+1, cols);
            }
        }
    }

    // Till current row no queen should be in column 'col'
    private boolean isValid(int row, int col, int[] cols) {
        for (int i = 0; i < row; i++) {
            if(cols[i] == col || row - i == Math.abs(col - cols[i]))
                return false;
        }
        return true;
    }

    private List<String> populateSolution(int[] cols) {
        int n = cols.length;
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (cols[i] == j) {
                    temp.append('Q');
                } else {
                    temp.append('.');
                }
            }
            solution.add(temp.toString());
        }
        return solution;
    }

    public static void main(String[] args) {
        LC51NQueens nq = new LC51NQueens();
        System.out.println(nq.solveNQueens(8));
    }
}
