/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/
----------- in the solution -> all 8 directions are allowed for land ------------

import java.util.*;

class Main{
    static class pair{
      int x;
      int y;
      
      pair(int x, int y){
        this.x = x;
        this.y = y;
      }
    }
  
    public static void printArray(boolean arr[][]){
      for(int i=0; i<arr.length; i++){
        for(int j=0; j<arr[0].length; j++){
          System.out.print(arr[i][j] +" ");
        }
        System.out.println();
      }
    }  
  
    public static void bfs(char[][] grid, boolean vis[][], int i, int j){
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(i, j));
        vis[i][j] = true;
        int n = grid.length;
        int m = grid[0].length;
        
        
        while(!q.isEmpty()){
          pair curr = q.remove();
          int x = curr.x;
          int y = curr.y;
          
          for(int delrow=-1; delrow<=1; delrow++){
            for(int delcol=-1; delcol<=1; delcol++){
              int newrow = x + delrow;
              int newcol = y + delcol;
              
              if(newrow >= 0 && newrow <n && newcol >= 0 && newcol < m
              && grid[newrow][newcol] == '1' && vis[newrow][newcol] == false ){
                vis[newrow][newcol] = true;
                q.add(new pair(newrow, newcol));
              }
              
            }
          }
          
        }
        
    }
    
    public static int numIslands(char[][] grid){
      int n = grid.length;
      int m = grid[0].length;
      boolean vis[][] = new boolean[n][m];
      
        int count = 0;
        
        for(int i=0; i<n; i++){
          for(int j=0; j<m; j++){
            if(vis[i][j] == false && grid[i][j] == '1'){
              bfs(grid, vis, i, j);
              count++;
            }
          }
        }
        
      return count;
    }
    
    public static void main(String args[]){
        char arr[][] = {{'0', '1', '1', '0'},
                        {'0', '1', '1', '0'},
                        {'0', '0', '1', '0'},
                        {'0', '0', '0', '0'},
                        {'1', '1', '0', '1'} };
        
        System.out.println("ans is "+numIslands(arr));
    }
}


----------- my initial solution (for only 4 direction)--------------------

class Solution {
    class pair{
      int x;
      int y;
      
      pair(int x, int y){
        this.x = x;
        this.y = y;
      }
    }
  
    public void bfs(char[][] grid, boolean vis[][], int i, int j){
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(i, j));
        int n = grid.length;
        int m = grid[0].length;
        
        while(!q.isEmpty()){
          pair curr = q.remove();
          int x = curr.x;
          int y = curr.y;
          
          
            if(y+1 < m &&  grid[x][y+1] == '1' && vis[x][y+1] == false){
              q.add(new pair(x, y+1));
              vis[x][y+1] = true;
            
            } 
            if(x+1 < n && grid[x+1][y] == '1' && vis[x+1][y] == false){
              q.add(new pair(x+1, y));
              vis[x+1][y] = true;
            
            } 
            if(y-1 >=0 && grid[x][y-1] == '1' && vis[x][y-1] == false){
              q.add(new pair(x, y-1));
              vis[x][y-1] = true;
            
            } 
            if(x-1 >=0 && grid[x-1][y] == '1' && vis[x-1][y] == false){
              q.add(new pair(x-1, y));
              vis[x-1][y] = true;
            
            } 
          
        }
        
    }
    
    public int numIslands(char[][] grid){
      int n = grid.length;
      int m = grid[0].length;
      boolean vis[][] = new boolean[n][m];
      
        int count = 0;
        
        for(int i=0; i<n; i++){
          for(int j=0; j<m; j++){
            if(vis[i][j] == false && grid[i][j] == '1'){
              System.out.println();
              System.out.println("in main loop x=" + i + " y=" + j);
              bfs(grid, vis, i, j);
              count++;
            }
          }
        }
        
      return count;
    }
}
