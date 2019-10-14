package main.java.problems.arrays;

import java.util.TreeSet;

/***
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 *
 * Example:
 *
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 *              and 2 is the max number no larger than k (k = 2).
 * Note:
 *
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 *
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 */
public class LC363MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix==null||matrix.length==0||matrix[0].length==0)
            return 0;
        int row=matrix.length;             int col=matrix[0].length;
        int m = Math.max(row, col);        int n = Math.min(row, col);
        boolean isRowLarger = row > col;
        int result = Integer.MIN_VALUE;
        for(int c1=0; c1<n; c1++){
            int[] each = new int[m];
            for(int c2=c1; c2>=0; c2--){
                for(int r=0; r<m; r++)
                    each[r]+=isRowLarger?matrix[r][c2]:matrix[c2][r];
                result = Math.max(result, getLargestSumCloseToK(each, k));
            }
        }

        return result;
    }

    public int getLargestSumCloseToK(int[] arr, int k){
        int sum=0;
        TreeSet<Integer> set = new TreeSet<>();
        int result=Integer.MIN_VALUE;
        set.add(0);

        for (int i1 : arr) {
            sum = sum + i1;

            Integer ceiling = set.ceiling(sum - k);
            if (ceiling != null) {
                result = Math.max(result, sum - ceiling);
            }

            set.add(sum);
        }

        return result;
    }
}
