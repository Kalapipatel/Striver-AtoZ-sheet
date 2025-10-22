/*
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
Example 2:


Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.
*/


class Solution {
    public void dfs(int grid[][], int delrow[], int delcol[], int sr, int sc, int n, int m, boolean vis[][]){
        vis[sr][sc] = true;

        for(int k=0; k<4; k++){
            int row = sr + delrow[k];
            int col = sc + delcol[k];

            if(row>=0 && row<n && col>=0 && col<m && 
            grid[row][col] == 1 && vis[row][col] == false){
                vis[row][col] = true;
                dfs(grid, delrow, delcol, row, col, n, m, vis);
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];

        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if( (i == 0 || i == n-1 || j == 0 || j == m-1) && grid[i][j]==1){
                    dfs(grid, delrow, delcol, i, j, n, m, vis);
                }
            }
        }

        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!(i == 0 || i == n-1 || j == 0 || j == m-1) && 
                grid[i][j] == 1 && vis[i][j] == false){
                    count++;
                }
            }
        }

        return count;
    }
}
