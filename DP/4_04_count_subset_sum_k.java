class Solution {
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        // code here
        int n = nums.length;
        int dp[][] = new int[n][target+1];
        
        if(nums[0] <= target) dp[0][nums[0]] = 1;
        for(int i=0; i<n; i++) dp[i][0] = 1;
        if(nums[0] == 0) dp[0][0] = 2;
        
        for(int i=1; i<n; i++){
            for(int k=0; k<=target; k++){
                int notpick = dp[i-1][k];
                int pick = 0;
                if(nums[i] <= k){
                    pick = dp[i-1][k - nums[i]];
                }
                
                dp[i][k] = pick + notpick;
            }
        }
        
        return dp[n-1][target];
    }
}
