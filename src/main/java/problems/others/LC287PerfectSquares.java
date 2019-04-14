package main.java.problems.others;

import java.util.Arrays;

/***
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * https://leetcode.com/problems/perfect-squares/
 *
 * Solution: The maximum perfect square less then n will be √n. So, we can check for each numbers in j=1 to √n whether we can break n into two parts such that one part is a perfect square j*j and
 * the remaining part n-j*j can be broken into perfect squares in similar manner. Clearly it has a recurrence relation:
 * ps(n) = j*j + ps(n-j*j), for all possible 1≤j≤√n.
 * We need to find such j that minimizes number of perfect squares generated.
 *
 */
public class LC287PerfectSquares {

    public int numSquares(int n) {
        if(n <= 0){
            return 0;
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;

        //to compute least perfect for n we compute top down for each possible value sum from 2 to n
        for(int i = 2; i<=n; i++){
            //for i we can say it is sum of a perfect square j*j and all perfect squares from solution of the remainder (i-j*j)
            for(int j = 1; j*j <= i; j++){
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);
            }
        }

        return dp[n];
    }

}
