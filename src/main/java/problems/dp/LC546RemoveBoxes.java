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
 *
 * Ref: https://algorithmist.com/q/leetcode/546/remove-boxes
 * http://startleetcode.blogspot.com/2017/03/leetcode-546-remove-boxes.html
 * https://www.cnblogs.com/Dylan-Java-NYC/p/11660827.html
 */
public class LC546RemoveBoxes {

    private static final int MAX = 105;
    private int[][][] dp = new int[MAX][MAX][MAX];
    private int[] boxes;

    int getMaxScore(int left, int right, int streak) {
        int max = 0, score, i;
        if (left > right) return 0;
        if (dp[left][right][streak] > 0)  return dp[left][right][streak];

        /* we're done with this colour, removing boxes[left] */
        max = getMaxScore(left + 1, right, 0) + (streak+1) * (streak+1);

        for (i = left + 1; i <= right; i++) {
            if (boxes[i] == boxes[left]) {
            /* we use the "i-th" box as part of the streak, and
                then continue on to the right, while we start a new
                a new chain with the new left-most block */
                score = getMaxScore(left + 1, i - 1, 0) +
                        getMaxScore(i, right, streak + 1);
                if (score > max) {
                    max = score;
                }
            }
        }
        return dp[left][right][streak] = max;
    }

    int removeBoxes2(int[] boxes)  {
        return getMaxScore(0, boxes.length - 1, 0);
    }

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
        System.out.println(removeBoxes.removeBoxes2(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }
}
