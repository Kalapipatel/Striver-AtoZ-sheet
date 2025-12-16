Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100

// --------------------------

class Solution {
    public Boolean isSubsetSum(int arr[], int sum) {
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

    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int x : nums) total += x;

        if(total % 2 != 0) return false;
        else return isSubsetSum(nums, total/2);
    }
}
  
