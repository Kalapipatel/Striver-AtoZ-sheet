/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
*/

class Solution {
    public int fun(int x, int y, int m, int n, int dp[][]){
        if(x == m-1 && y == n-1){
            return 1;
        }

        int right = 0;
        int left = 0;

        if(dp[x][y] != -1) return dp[x][y];

        if(x < m-1)
            right += fun(x+1, y, m, n, dp);

        if(y < n-1)
            left += fun(x, y+1, m, n, dp);

        return dp[x][y] = right + left;
    }

    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        return fun(0,0,m,n, dp);
    }
}


------------
// space optimazied
  class Solution {
    public int uniquePaths(int m, int n) {
        int prev[] = new int[n];

        for(int i=0; i<m; i++){
            int curr[] = new int[n];
            for(int j=0; j<n; j++){
                if(i == 0 && j== 0) curr[j] = 1;
                else{
                    int up = 0;
                    int left = 0;
                    if(i > 0) up = prev[j];
                    if(j > 0) left = curr[j-1];

                    curr[j] = up + left; 
                }
            }

            prev = curr;
        }

        return prev[n-1];
    }
}
