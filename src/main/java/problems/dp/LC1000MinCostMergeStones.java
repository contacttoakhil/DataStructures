package main.java.problems.dp;

/***
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 * Example 2:
 *
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 * Note:
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 *
 * Solution:
 * There are
 *
 *
 *
 *
 *
 *
 *
 * In the example above we had initially 5(N) piles: [3,5,1,2,6] and after merge 3(K) piles we are left with 2 (N - (K-1)) piles and eventually we must get 1 pile so it means if (N-1) mod (K-1)==0 then it's possible to merge those piles into 1.
 * =>  if ((n - 1) % (K - 1) > 0) return -1;
 *
 * dp[i][j] : minimum cost needed to merge stones[i] ~ stones[j] as much as possible.
 *
 *
 *
 *
 *
 *
 * dp[j][i] :  minimal cost to merge the piles in interval [i,i+j) where we merge piles as much as possible.
 * If we merge stones[i] ~ stones[j] as much as possible finally (j - i) % (K - 1) + 1 piles will be left.
 *
 * So when j < 1+(K-1) we don't merge any piles, so dp[i][i+j] = 0;
 *    => when 1+(K-1) <= j < 1+2(K - 1) we merge once;
 *    => when 1+2(K-1) <= j < 1+3(K-1), we merge twice, and so on so forth.
 *
 * Let's see for a certain interval length j how can we get dp[l][i]. After all mergings, if we consider the leftmost pile in interval [i,i+l), then the status of this pile will show as below:
 *
 * No merging happens in this pile, so it contains 1 original pile, let k = 1, then the cost is dp[k][i]+dp[l-k][i+k].
 * One merging happens in this pile, so it contains 1+(K-1) original piles, let k = 1+(K-1), then the cost is dp[k][i]+dp[l-k][i+k].
 * Two mergings happen in this pile, so it contains 1+2(K-1) original piles, let k = 1+2(K-1), then the cost is dp[k][i]+dp[l-k][i+k].
 * .......
 * When (l-1) mod (K-1)==0, we can see all piles in interval [i,i+l) can be finally merged into one pile, and the cost of the last merging is sum(stones[j]) for j in [i,i+l), regardless of the merging choices before the last one. And this "last cost" happens if and only if (l-1) mod (K-1)==0
 */
public class LC1000MinCostMergeStones {
    public int mergeStones(int[] stones, int K) {
        int n  = stones.length;
        if(n == 1) return 0;
        if ((n - 1) % (K - 1) > 0) return -1;
        int[] prefix = new int[n+1]; // prefix-sum
        for (int i = 1; i <=  n; i++)
            prefix[i] = prefix[i-1] + stones[i-1];  /* prefix = [0,3,8,9,11,17] */

        int[][] dp = new int[n+1][n+1];
        for (int j = K-1; j < n; ++j) {
            for (int i = j - K; i >= 0; --i) {
                compute(dp, i, j, K);
                if ((j - i) % (K - 1) == 0)
                    dp[i][j] += prefix[j] - prefix[i - 1];
            }
        }
        return dp[1][n];
    }

    public int mergeStones2(int[] stones, int K) {
        int n  = stones.length;
        if(n == 1) return 0;
        if ((n - 1) % (K - 1) > 0) return -1;
        int[] prefix = new int[n+1]; // prefix-sum
        for (int i = 1; i <=  n; i++)
            prefix[i] = prefix[i-1] + stones[i-1];  /* prefix = [0,3,8,9,11,17] */

        int[][] dp = new int[n+1][n+1];
        for (int j = K; j <= n; ++j) {
            for (int i = j - K + 1; i > 0; --i) {
                compute(dp, i, j, K);
                if ((j - i) % (K - 1) == 0)
                    dp[i][j] += prefix[j] - prefix[i - 1];
            }
        }
        return dp[1][n];
    }


    /* min cost of merging stones[start] ~ stones[end] would be   */
    private void compute(int[][] dp, int i, int j, int k) {
        dp[i][j] = Integer.MAX_VALUE;
        for (int p = i; p < j; p += k - 1)
            dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j]);

    }
    public static void main(String[] args) {
        LC1000MinCostMergeStones minCostMergeStones = new LC1000MinCostMergeStones();
        System.out.println(minCostMergeStones.mergeStones(new int[]{3,5,1,2,6},3)); //25
        System.out.println(minCostMergeStones.mergeStones2(new int[]{3,5,1,2,6},3)); //25
    }
}

