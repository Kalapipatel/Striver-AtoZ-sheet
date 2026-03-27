/*
312. Burst Balloons
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10
 

Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100
*/

class Solution {
    public int fun(int i, int j, int a[], int dp[][]){
        if(i > j) return 0;
        int maxi = Integer.MIN_VALUE;

        if(dp[i][j] != -1) return dp[i][j];
        for(int idx = i; idx<=j; idx++){
            int cost = a[i-1]*a[idx]*a[j+1] + fun(i, idx-1, a, dp) +
            fun(idx+1, j, a, dp);

            maxi = Math.max(maxi, cost);
        }

        return dp[i][j] = maxi;
    }
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n+2];

        arr[0] = 1;
        arr[n+1] = 1;
        int k = 1;
        for(int x : nums) arr[k++] = x;
        
        int dp[][] = new int[n+1][n+1];
        for(int x[] : dp){
            Arrays.fill(x, -1);
        }

        return fun(1, n, arr, dp);
    }
}

// Tabuluation
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n+2];

        arr[0] = 1;
        arr[n+1] = 1;
        int k = 1;
        for(int x : nums) arr[k++] = x;
        
        int dp[][] = new int[n+2][n+2];

        for(int i=n; i>=1; i--){
            for(int j=i; j<=n; j++){
                int maxi = Integer.MIN_VALUE;
                for(int idx = i; idx<=j; idx++){
                    int cost = arr[i-1]*arr[idx]*arr[j+1] + dp[i][idx-1] +
                    dp[idx+1][j];

                    maxi = Math.max(maxi, cost);
                }
                dp[i][j] = maxi;
            }
        }

        return dp[1][n];
    }
}
