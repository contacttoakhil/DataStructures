package main.java.problems.dp;

import java.util.Arrays;

/***
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 * https://leetcode.com/problems/coin-change/
 */
public class LC322CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] table = new int[amount+1];
        Arrays.fill(table, amount+1); // amount+1 is max value here.
        table[0] = 0;
        for(int i=0; i<coins.length; i++) {
            for(int j=coins[i]; j<=amount; j++) {
                table[j] = Math.min(table[j], table[j-coins[i]]+1);
            }
        }
        return(table[amount] > amount) ? -1 : table[amount];
    }
}
