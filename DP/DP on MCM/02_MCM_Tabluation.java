
class Solution {
    
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        
        for(int i=n-1; i>=1; i--){
            for(int j=i+1; j<n; j++){
                int mini = (int)1e9;
        
                for(int k=i; k<j; k++){
                    int steps = (arr[i-1] * arr[k] * arr[j]) + 
                    dp[i][k] + dp[k+1][j];
                    
                    mini = Math.min(mini, steps);
                }
                dp[i][j] = mini;
            }
        }
        
        return dp[1][n-1];
    }
}
