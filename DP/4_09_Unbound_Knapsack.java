// Unbound Knapsack

class Solution {
    public int knapSack(int val[], int wt[], int maxWeight) {
        int n = wt.length;
      int dp[][] = new int[n][maxWeight+1];
      
      for(int w=0; w<=maxWeight; w++){
        dp[0][w] = val[0] * (w / wt[0]);
      }
      
      for(int i=1; i<n; i++){
        for(int W=0; W<=maxWeight; W++){
          int nottake = 0 + dp[i-1][W];
          int take = Integer.MIN_VALUE;
          if(wt[i] <= W){
            take = val[i] + dp[i][W - wt[i]];
          }
          
          dp[i][W] = Math.max(take, nottake);
        }
      }

      return dp[n-1][maxWeight];
        
    }
}
