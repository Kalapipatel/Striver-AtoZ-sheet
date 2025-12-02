/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*/

// Recursion
 public int rec(int arr[], int idx, int[] dp){
        if(idx == 0) return arr[idx];
        if(idx < 0) return 0;

        if(dp[idx] != -1) return dp[idx];
        int pick = arr[idx] + rec(arr, idx-2, dp);
        int notPick = rec(arr, idx-1, dp);

        return dp[idx] = Math.max(pick, notPick);
 }

// tabulaiton
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
       
        for(int i=1; i<n; i++){
            int take = nums[i];
            if(i > 1) take += dp[i-2];
            int nottake = dp[i-1];

            dp[i] = Math.max(take, nottake);
        }

        return dp[n-1];
    }
}
