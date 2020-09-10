package main.java.problems.dp;

/***
 * There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 *
 * Solution: Two adjacent houses can have same color. Suppose F(N) be the number of ways for N houses then we can say:
 * F(N) = (k-1) *  F(N-1) + F(N-2)
 *
 * As number of ways we can paint two adjacent houses on left are: F(N-1) + F(N-2) and if they use same color then we have k-1 colors available for painting Nth house.
 *
 */
public class LC276PaintFence {
    public int numWays(int n, int k) {
        if(n == 0 || k == 0) return 1;
        int[] dp = {0, k , k*k, 0};
        if( n< 3) return dp[n];
        for (int i = 2; i <n ; i++) {
            dp[3] = (dp[1] + dp[2]) * (k-1);
            dp[1] = dp[2];
            dp[2] = dp[3];
        }
        return dp[3];
    }

}
//https://www.programcreek.com/2014/05/leetcode-pain-fence-java/