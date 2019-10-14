package main.java.problems.dp;

import java.util.Arrays;

/***
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 * https://leetcode.com/problems/candy/
 */
public class LC135Candy {
    public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i <ratings.length ; i++) {
            if(ratings[i] > ratings[i-1])
                dp[i] = dp[i-1] + 1;
        }
        for (int i = ratings.length-2; i >= 0 ; i--) {
            if(ratings[i] > ratings[i+1])
                dp[i] = Math.max(dp[i], dp[i+1]+1);
        }
        int total = 0;
        for (int item : dp)
            total += item;
        return total;
    }

    public static void main(String[] args) {
        LC135Candy candy = new LC135Candy();
        System.out.println(candy.candy(new int[] {1, 0 , 2}));
        System.out.println(candy.candy(new int[] {1, 2 , 2}));
    }
}
