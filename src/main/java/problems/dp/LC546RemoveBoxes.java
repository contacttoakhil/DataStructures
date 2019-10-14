package main.java.problems.dp;

/***
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 *
 * Example 1:
 * Input:
 *
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * Output:
 * 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Note: The number of boxes n would not exceed 100.
 */
public class LC546RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        for (int l = 0; l < n; l++)            // only one box left with k boxes on left
            for (int k = 0; k <= l; k++)
                dp[l][l][k] = (k + 1) * (k + 1);
        for (int left = 1; left < n; left++) {
            for (int right = left; right < n; right++) {
                update(dp, boxes, left, right);
            }
        }
        return (n == 0 ? 0 : dp[0][n - 1][0]);
    }
    // left, [], [], [], [] ...... [], [], right
    private void update(int[][][] dp, int[] boxes, int left, int right) {
        int d = right - left; // left=2 right=5 then d=3
        for (int k = 0; k <= d; k++) {   // k matching boxes on left
            int res = (k + 1) * (k + 1) + dp[d + 1][right][0];
            for (int m = d + 1; m <= right; m++) {
                if(boxes[m] == boxes[d])  // same color as box on left?
                    res = Math.max(res, dp[d + 1][m-1][0] + dp[m][right][k+1]);
            }
            dp[d][right][k] = res;
        }
    }

    public static void main(String[] args) {
        LC546RemoveBoxes removeBoxes = new LC546RemoveBoxes();
        System.out.println(removeBoxes.removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }
}
