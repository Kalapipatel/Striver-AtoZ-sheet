/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
*/


// Memoization
class Solution {
    public int fun(int idx, int buy, int prices[], int dp[][]){
        if(idx == prices.length) return 0;

        int profit = 0;
        if(dp[idx][buy] != -1) return dp[idx][buy];
        if(buy == 1){
            profit = Math.max(-prices[idx] + fun(idx+1, 0, prices, dp),
                                0 + fun(idx+1, 1, prices, dp));
        }
        else{
            profit = Math.max(prices[idx] + fun(idx+1, 1, prices, dp),
                                0 + fun(idx+1, 0, prices, dp));
        }

        return dp[idx][buy] = profit;
    }

    public int maxProfit(int[] prices) {
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

    public int maxProfit(int[] prices) {
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
                    profit = Math.max(prices[i] + dp[i+1][1],
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

    public int maxProfit(int[] prices) {
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
                    profit = Math.max(prices[i] + ahed[1],
                                        0 + ahed[0]);
                }

                curr[j] = profit;
            }

            ahed = curr;
        }

        return ahed[1];
    }
}
