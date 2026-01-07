public class Main {
  
  public static int knapsack(int wt[], int val[], int maxWeight){
    int n = wt.length;
      int dp[][] = new int[n][maxWeight+1];
      
      for(int i=wt[0]; i<=maxWeight; i++){
        dp[0][i] = val[0];
      }
      
      for(int i=1; i<n; i++){
        for(int W=0; W<=maxWeight; W++){
          int nottake = 0 + dp[i-1][W];
          int take = Integer.MIN_VALUE;
          if(wt[i] <= W){
            take = val[i] + dp[i-1][W - wt[i]];
          }
          
          dp[i][W] = Math.max(take, nottake);
        }
      }
}
      return dp[n-1][maxWeight];
  }
