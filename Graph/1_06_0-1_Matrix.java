/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
  
Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]  
*/

class Solution {
    class pair{
      int x;
      int y;
      int time;
      
      pair(int x, int y, int t){
        this.x = x;
        this.y = y;
        this.time = t;
      }
    }
    
    public void bfs(int mat[][], int ans[][], boolean vis[][] ){
      int n = mat.length;
      int m = mat[0].length;
      
      Queue<pair> q = new LinkedList<>();
      
      for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
          if(mat[i][j] == 0){
            q.add(new pair(i, j, 0));
            vis[i][j] = true;
          }
        }
      }
      
      int delrow[] = {-1, 0, 1, 0};
      int delcol[] = {0, 1, 0, -1};
      
      while(!q.isEmpty()){
        pair curr = q.remove();
        ans[curr.x][curr.y] = curr.time;
        
        for(int k=0; k<4; k++){
          int row = curr.x + delrow[k];
          int col = curr.y + delcol[k];
          
          if(row>=0 && row<n && col>=0 && col<m && vis[row][col] == false){
            vis[row][col] = true;
            q.add(new pair(row, col, curr.time+1));
          }
        }
      }
      
      
      
    }
    
    public int[][] updateMatrix(int[][] mat){
      int n = mat.length;
      int m = mat[0].length;
      int ans[][] = new int[n][m];
      boolean vis[][] = new boolean[n][m];
      
      bfs(mat, ans, vis);
      
      return ans;
    }
    
}
