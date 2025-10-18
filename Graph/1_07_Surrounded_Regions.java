/*
You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.

 

Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:

In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]
Output: [["X"]]

  */

class Solution {
    
    public void dfs(char[][] board, boolean vis[][], int n, int m, int delrow[], int delcol[], int sr, int sc){
        vis[sr][sc] = true;

        for(int k=0; k<4; k++){
            int row = sr + delrow[k];
            int col = sc + delcol[k];

            if(row>=0 && row<n && col>=0 && col<m && board[row][col] == 'O' && vis[row][col] == false){
                dfs(board, vis, n, m, delrow, delcol, row, col);
            }
        }
        
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean vis[][] = new boolean[n][m];
        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if( (i==0 || i==(n-1) || j==0 || j==(m-1)) && board[i][j] == 'O'){
                    dfs(board, vis, n, m, delrow, delcol, i, j);
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(vis[i][j] == true){
                    board[i][j] = 'O';
                }
                else{
                    board[i][j] = 'X';
                }
            }
        }

    }
}
