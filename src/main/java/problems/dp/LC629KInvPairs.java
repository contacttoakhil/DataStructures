package main.java.problems.dp;


/***
 * Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.
 *
 * We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.
 *
 * Since the answer may be very large, the answer should be modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation:
 * Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.
 *
 *
 * Example 2:
 *
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation:
 * The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 *
 * Note:
 *
 * The integer n is in the range [1, 1000] and k is in the range [0, 1000].
 *
 */
public class LC629KInvPairs {
    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        // zeroth row is zero as if n=0 no inverse pairs exist i.e. dp[0][k]=0 by default all rows are zero.
        // zeroth col is one as If k=0 only one arrangement is possible, which is all numbers sorted in ascending order. Thus, dp[n][0]=1.
        setColumn(dp, 0,1);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int p = 0; p <= Math.min(j, i - 1); p++)
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
            }
        }
        return dp[n][k];
    }
    private void setColumn(int[][] matrix, int col, int val) {
        for (int r=0; r<matrix.length; r++) {
            matrix[r][col] = val;
        }
    }

    public static void main(String[] args) {
        LC629KInvPairs kInvPairs = new LC629KInvPairs();
        System.out.println(kInvPairs.kInversePairs(3,0));
        System.out.println(kInvPairs.kInversePairs(3,1));
    }
}
