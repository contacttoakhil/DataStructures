package main.java.problems.arrays;

public class LC123BestTimeBuySellStocksIII {
    public int maxProfit(int[] prices) {
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for(int i = 0; i < prices.length; i++){
            //we set prices to negative, so the calculation of profit will be convenient
            oneBuy = Math.max(oneBuy, -prices[i]);
            oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
            //we can buy the second only after first is sold
            twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);
            twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
        }
        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }
}
