/*
Given an array arr[] and an integer diff, count the number of ways to partition the array into two subsets such that the difference between their sums is equal to diff.

Note: A partition in the array means dividing an array into two subsets say S1 and S2 such that the union of S1 and S2 is equal to the original array and each element is present in only one of the subsets.

Examples :

Input: arr[] = [5, 2, 6, 4], diff = 3
Output: 1
Explanation: There is only one possible partition of this array. Partition : [6, 4], [5, 2]. The subset difference between subset sum is: (6 + 4) - (5 + 2) = 3.
Input: arr[] = [1, 1, 1, 1], diff = 0 
Output: 6 
Explanation: We can choose two 1's from indices [0,1], [0,2], [0,3], [1,2], [1,3], [2,3] and put them in sum1 and remaning two 1's in sum2.
Thus there are total 6 ways for partition the array arr. 
Input: arr[] = [3, 2, 7, 1], diff = 4  
Output: 0
Explanation: There is no possible partition of the array that satisfy the given difference. 
*/

class Solution {
    
    // count seubset with target sum = k
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
    
    int mod = (int)(1e9+7);
    public int countPartitions(int[] arr, int diff) {
        int total = 0;
        for(int x : arr) total += x;
        
        if((total - diff < 0) || (total - diff) % 2 != 0) return 0;
        
        return perfectSum(arr, (total - diff)/2);
        
    }
}
