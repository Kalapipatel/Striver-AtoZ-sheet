/*
Given a positive integer n, count all possible distinct binary strings of length n such that there are no consecutive 1’s.

Examples :

Input: n = 3
Output: 5
Explanation: 5 strings are ("000", "001", "010", "100", "101").
Input: n = 2
Output: 3
Explanation: 3 strings are ("00", "01", "10").
Input: n = 1
Output: 2
Constraints:
1 ≤ n ≤ 44

Expected Complexities
Time Complexity: O(n)
Auxiliary Space: O(n)
*/

// Memoization
class Solution {
    public int fun(int idx, int one, int n, int dp[][]){
        if(idx == n){
            return 1;   
        }
        
        if(dp[idx][one] != -1) return dp[idx][one];
        if(one == 1){
            dp[idx][one] = fun(idx+1, 1, n, dp) + fun(idx+1, 0, n, dp);
            return dp[idx][one];
        }
        else{
            return dp[idx][one] = fun(idx+1, 1, n, dp);
        }
        
    }
    int countStrings(int n) {
        int dp[][] = new int[n][2];
        for(int x[] : dp){
            Arrays.fill(x, -1);
        }
        
        return fun(0, 1, n, dp);
        
    }
}
