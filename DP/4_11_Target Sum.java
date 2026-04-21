/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
*/

class Solution {
    public int perfectSum(int[] nums, int target) { // count subset whoes sum = k
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

    public int findTargetSumWays(int[] nums, int target) { // same code as Dp 18 code
        int sum = 0;
        for(int x : nums) sum += x;

      // we will divide into 2 sets. set1 - set2 = diff.
      // s1 = total - s2
      // total - s2 - s2 = diff
      // s2 = (diff - total)/2

        if(sum - target < 0 || (sum-target) % 2 != 0) return 0;

        return perfectSum(nums, (sum - target) / 2);
    }
}
