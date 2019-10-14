package main.java.problems.arrays;

/***
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on children day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * Solution:
 * buy[i]: Max profit till index i. The series of transaction is ending with a buy.
 * sell[i]: Max profit till index i. The series of transaction is ending with a sell.
 *
 * Now we can define the recursion as:
 * buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1, or sell at/before i - 2, then buy at i.
 * We cannot sell at i - 1, then buy at i, because of cooldown.
 * sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1, or buy at/before i - 1, then sell at i.
 *
 * So we get the following formula:
 * buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
 * sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
 *
 * bi0 = Math.max( bi1, si2 - prices[i])
 * si0 = Math.max(si1, bi1 + prices[i]);
 */
public class LC308BestTimeToBuyAndSellStockWithCoolDown {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;

        int bi0 = -prices[0], bi1 = bi0;
        int si0 = 0, si1 = 0, si2 = 0;

        for(int i = 1; i < prices.length; i++) {
            bi0 = Math.max(bi1, si2 - prices[i]);
            si0 = Math.max(si1, bi1 + prices[i]);
            bi1 = bi0; si2 = si1; si1 = si0;
        }
        return si0;
    }
}
