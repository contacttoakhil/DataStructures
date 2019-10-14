package main.java.problems.dp;

import main.java.problems.arrays.LC121BestTimeBuySellStocks;

public class LC188BestTimeBuySellStocksIV {
    public int maxProfit(int K, int[] prices) {
        int len = prices.length;
        if (K >= len / 2) return quickSolve(prices);

        int[][] dp = new int[K + 1][len];
        for (int k = 1; k <= K; k++) {
            int tmpMax =  -prices[0];
            for (int d = 1; d < len; d++) {
                dp[k][d] = Math.max(dp[k][d - 1], prices[d] + tmpMax);
                tmpMax =  Math.max(tmpMax, dp[k - 1][d - 1] - prices[d]);
            }
        }
        return dp[K][len - 1];
    }


    private int quickSolve(int[] prices) {
        return new LC121BestTimeBuySellStocks().maxProfit(prices);
    }
}
