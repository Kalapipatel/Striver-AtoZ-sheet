/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 
Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
*/

// Memoization
class Solution {
    public int fun(int idx, int prev, int arr[], int dp[][]){
        if(idx == arr.length) return 0;

        if(dp[idx][prev+1] != -1) return dp[idx][prev+1];
        int len = 0 + fun(idx+1, prev, arr, dp);

        if(prev == -1 || arr[idx] > arr[prev]){
            len = Math.max(len, 1 + fun(idx+1, idx, arr, dp));
        }

        return dp[idx][prev+1] = len;
    }
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n+1][n+1];

        for(int x[] : dp){
            Arrays.fill(x , -1);
        }
        
        return fun(0, -1, nums, dp);
    }
}


// Tabulation
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n+1][n+1];

        for(int i=n-1; i>=0; i--){
            for(int prev = i-1; prev>=-1; prev--){
                int len = 0 + dp[i+1][prev+1];

                if(prev == -1 || nums[i] > nums[prev] ){
                    len = Math.max(len, 1 + dp[i+1][i+1]); // fun(idx+1, idx)
                }

                dp[i][prev+1] = len;
            }
        }

        return dp[0][0];
    }
}

// Space Optmization
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int next[] = new int[n+1];

        for(int i=n-1; i>=0; i--){
            int curr[] = new int[n+1];
            for(int prev = i-1; prev>=-1; prev--){
                int len = 0 + next[prev+1];

                if(prev == -1 || nums[i] > nums[prev] ){
                    len = Math.max(len, 1 + next[i+1]); // fun(idx+1, idx)
                }

                curr[prev+1] = len;
            }
            next = curr;
        }

        return next[0];
    }
}
