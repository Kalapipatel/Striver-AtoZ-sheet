/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
*/

// Memoization


import java.util.Arrays;

class StockProfit {
    static int getAns(int[] Arr, int n, int ind, int buy, int cap, int[][][] dp) {
        // Base case: If we have processed all stocks or have no capital left, return 0 profit
        if (ind == n || cap == 0)
            return 0;

        // If the result for this state is already calculated, return it
        if (dp[ind][buy][cap] != -1)
            return dp[ind][buy][cap];

        int profit;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(Arr, n, ind + 1, 0, cap, dp),
                    -Arr[ind] + getAns(Arr, n, ind + 1, 1, cap, dp));
        }

        if (buy == 1) { // We can sell the stock
            profit = Math.max(0 + getAns(Arr, n, ind + 1, 1, cap, dp),
                    Arr[ind] + getAns(Arr, n, ind + 1, 0, cap - 1, dp));
        }

        // Store the calculated profit in the dp array and return it
        return dp[ind][buy][cap] = profit;
    }

    static int maxProfit(int[] prices) {
        int n = prices.length;

        // Creating a 3D dp array of size [n][2][3]
        int[][][] dp = new int[n][2][3];

        // Initialize the dp array with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // Calculate and return the maximum profit
        return getAns(prices, n, 0, 0, 2, dp);
    }

}


// Tabulation
import java.util.*;

public class StockProfit {

    public int maxProfit(int[] prices, int n) {
        // DP table: dp[ind][buy][cap]
        // ind → current index
        // buy → 0 = can buy, 1 = can sell
        // cap → remaining transactions (max 2)
        int[][][] dp = new int[n + 1][2][3];

        // Base case: initialized to 0 → no profit if no transactions left or no days left

        // Fill DP table from the last day to the first
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // cap starts from 1 because 0 means no transactions left
                    if (buy == 0) {
                        // Option 1: skip buying
                        // Option 2: buy today → subtract price, keep cap same
                        dp[ind][buy][cap] = Math.max(
                            0 + dp[ind + 1][0][cap],
                            -prices[ind] + dp[ind + 1][1][cap]
                        );
                    } else {
                        // Option 1: skip selling
                        // Option 2: sell today → add price, reduce cap by 1
                        dp[ind][buy][cap] = Math.max(
                            0 + dp[ind + 1][1][cap],
                            prices[ind] + dp[ind + 1][0][cap - 1]
                        );
                    }
                }
            }
        }

        // Final result: start at index 0, can buy, with 2 transactions left
        return dp[0][0][2];
    }

    public static void main(String[] args) {
        StockProfit sp = new StockProfit();
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("The maximum profit that can be generated is " +
                sp.maxProfit(prices, prices.length));
    }
}

