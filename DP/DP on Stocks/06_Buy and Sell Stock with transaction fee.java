/*
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 

Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
*/


// Memoization
class Solution {
    public int fun(int idx, int buy, int prices[], int fee , int dp[][]){
        if(idx == prices.length) return 0;

        int profit = 0;
        if(dp[idx][buy] != -1) return dp[idx][buy];
        if(buy == 1){
            profit = Math.max(-prices[idx] + fun(idx+1, 0, prices, dp),
                                0 + fun(idx+1, 1, prices, dp));
        }
        else{
            profit = Math.max(prices[idx] - fee + fun(idx+1, 1, prices, dp),
                                0 + fun(idx+1, 0, prices, dp));
        }

        return dp[idx][buy] = profit;
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int dp[][] = new int[n][2];

        for(int x[] : dp){
            Arrays.fill(x, -1);
        }

        return fun(0, 1, prices, dp);
    }
}


// Tbaulation
class Solution {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int dp[][] = new int[n+1][2];
        dp[n][0] = dp[n][1] = 0;

        for(int i=n-1; i>=0; i--){
            for(int j=0; j<=1; j++){
                int profit = 0;

                if(j == 1){
                    profit = Math.max(-prices[i] + dp[i+1][0],
                                0 + dp[i+1][1]);
                }
                else{
                    profit = Math.max(prices[i] - fee + dp[i+1][1],
                                        0 + dp[i+1][0]);
                }

                dp[i][j] = profit;
            }
        }

        return dp[0][1];
    }
}


// space optimization
class Solution {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int ahed[] = new int[2];
        ahed[0] = ahed[1] = 0;

        for(int i=n-1; i>=0; i--){
            int curr[] = new int[2];
            for(int j=0; j<=1; j++){
                int profit = 0;

                if(j == 1){
                    profit = Math.max(-prices[i] + ahed[0],
                                0 + ahed[1]);
                }
                else{
                    profit = Math.max(prices[i] - fee + ahed[1],
                                        0 + ahed[0]);
                }

                curr[j] = profit;
            }

            ahed = curr;
        }

        return ahed[1];
    }
}
