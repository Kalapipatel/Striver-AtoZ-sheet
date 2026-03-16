/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0
 

Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
*/

// Memoization
class Solution {
    public int fun(int idx, int buy, int[] prices, int[][] dp){
        if(idx >= prices.length) return 0;

        if(dp[idx][buy] != -1) return dp[idx][buy];
        if(buy == 1){
            return dp[idx][buy] = Math.max(-prices[idx] + fun(idx+1, 0, prices, dp),
                            0 + fun(idx+1, 1, prices, dp));
        }
        else{
            return dp[idx][buy] = Math.max(prices[idx] + fun(idx+2, 1, prices, dp),
                            0 + fun(idx+1, 0, prices, dp));
        }
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n][2];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        return fun(0, 1, prices, dp);
    }
}

// Tabulation
static int stockProfit(int[] Arr) {
        int n = Arr.length;
        int dp[][] = new int[n + 2][2];
        
        // Iterate through the array backwards
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;

                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 2][0]);
                }

                dp[ind][buy] = profit;
            }
        }

        // The maximum profit is stored in dp[0][0]
        return dp[0][0];
    }


// Space Optmization
static int stockProfit(int[] Arr) {
        int n = Arr.length;
        int[] cur = new int[2];
        int[] front1 = new int[2];
        int[] front2 = new int[2];
        
        // Iterate through the array backwards
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;

                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + front1[0], -Arr[ind] + front1[1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + front1[1], Arr[ind] + front2[0]);
                }

                cur[buy] = profit;
            }

            // Update front1 and front2 arrays
            System.arraycopy(front1, 0, front2, 0, 2);
            System.arraycopy(cur, 0, front1, 0, 2);
        }

        // The maximum profit is stored in cur[0]
        return cur[0];
    }
