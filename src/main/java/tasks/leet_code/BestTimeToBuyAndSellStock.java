package tasks.leet_code;

public class BestTimeToBuyAndSellStock {

    /*
Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
     */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            int potentialProfit = price - minPrice;

            if (potentialProfit > maxProfit) {
                maxProfit = potentialProfit;
            }
        }

        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int length = prices.length;

        if (length == 0) {
            return 0;
        }

        int min_buy = prices[0];
        int max_sell = prices[0];

        if (length == 2 && prices[1] > prices[0]) {
            return prices[1] - prices[0];
        }

        for (int d = 0; d < length; d++) {
            if (min_buy >= prices[d]) {
                min_buy = prices[d];
                max_sell = prices[d];
                for (int i = d + 1; i < length; i++) {
                    if (max_sell <= prices[i]) {
                        max_sell = prices[i];
                    }
                }
            }
        }

//        System.out.println("min = " + min_buy);
//        System.out.println("max = " + max_sell);

        if (min_buy == prices[length - 1]
                || length < 2
                || min_buy == 10000
                || max_sell == -1) {
            return 0;
        }

        return max_sell - min_buy; //profit
    }
}
