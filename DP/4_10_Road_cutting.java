// Road Cutting

class Solution {
    public int cutRod(int[] price) {
        int n = price.length;
        int dp[][] = new int[n][n+1];
        
        for(int N=0; N<=n; N++){
            dp[0][N] = N * price[0];
        }
        
        for(int i=1; i<n; i++){
            for(int N=0; N<=n; N++){
                int notTake = 0 + dp[i-1][N];
                int take = Integer.MIN_VALUE;
                int roadlen = i+1;
                
                if(roadlen <= N){
                    take = price[i] + dp[i][N - roadlen];
                }
                
                dp[i][N] = Math.max(take, notTake);
            }
        }
        
        return dp[n-1][n];
    }
}
