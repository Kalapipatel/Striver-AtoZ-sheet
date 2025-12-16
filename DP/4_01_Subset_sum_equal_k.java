/*
Given an array of positive integers arr[] and a value sum, determine if there is a subset of arr[] with sum equal to given sum. 

Examples:

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
Output: true 
Explanation: Here there exists a subset with target sum = 9, 4+3+2 = 9.
Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 30
Output: false
Explanation: There is no subset with target sum 30.
Input: arr[] = [1, 2, 3], sum = 6
Output: true
Explanation: The entire array can be taken as a subset, giving 1 + 2 + 3 = 6.
Constraints:
1 <= arr.size() <= 200
1<= arr[i] <= 200
1<= sum <= 104

*/

  // -----
  class Solution {
    
    public static boolean fun(int idx, int k, int arr[], int dp[][]){
        if(k == 0) return true;
        if(idx == 0) return arr[0] == k;
        
        if(dp[idx][k] != -1){
            if(dp[idx][k] == 1) return true;
            else return false;
        }
        boolean notpick = fun(idx-1, k, arr, dp);
        boolean pick = false;
        
        if(k >= arr[idx]){
            pick = fun(idx-1, k - arr[idx], arr, dp);
        }
        
        if(notpick || pick == true) dp[idx][k] = 1; 
        else dp[idx][k] = 0;
        
        return notpick || pick;
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int n = arr.length;
        int dp[][] = new int[n][sum+1];
        
        for(int x[] : dp){
            Arrays.fill(x, -1);
        }
        
        return fun(n-1, sum, arr, dp);
    }
}

// --------- Tabulation

class Solution {

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int n = arr.length;
        boolean dp[][] = new boolean[n][sum+1];
        
        for(int i=0; i<n; i++) dp[i][0] = true;
        for(int t=1; t<=sum; t++){
            if(t == arr[0]) dp[0][t] = true;
        }
        
        for(int i=1; i<n; i++){
            for(int target=1; target<=sum; target++){
                
                boolean notpick = dp[i-1][target];
                boolean pick = false;
                
                if(target >= arr[i]){
                    pick = dp[i-1][target - arr[i]];
                }
                
                dp[i][target] = pick || notpick;
            }
        }
        
        return dp[n-1][sum];
    }
}

