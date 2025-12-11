/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
*/

// recursion relation
/*
up = g[i][j] + fun(i-1, j)
left = g[i][j] + fun(i, j-1)
*/

// ---- space optmization solution  --------
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int prev[] = new int[n];

        for(int i=0; i<m; i++){
            int curr[] = new int[n];
            for(int j=0; j<n; j++){
                if(i == 0 && j == 0) curr[j] = grid[i][j];
                else{
                    int up = grid[i][j];
                    int left = grid[i][j];

                    if(i > 0) up += prev[j];
                    else up = Integer.MAX_VALUE;
                    if(j > 0) left += curr[j-1];
                    else left = Integer.MAX_VALUE;
                    curr[j] = Math.min(up, left);
                }
            }

            prev = curr;
        }

        return prev[n-1];
    }
}
