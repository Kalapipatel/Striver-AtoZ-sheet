/*
Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected
1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not 
equal to another (not rotated or reflected).

Example 1:

Input:
grid[][] = [[1, 1, 0, 0, 0],
            [1, 1, 0, 0, 0],
            [0, 0, 0, 1, 1],
            [0, 0, 0, 1, 1]]
Output: 1
Explanation:
grid[][] = [[1, 1, 0, 0, 0], 
            [1, 1, 0, 0, 0], 
            [0, 0, 0, 1, 1], 
            [0, 0, 0, 1, 1]]
Same colored islands are equal. We have 2 equal islands, so we have only 1 distinct island.

Example 2:

Input:
grid[][] = [[1, 1, 0, 1, 1],
            [1, 0, 0, 0, 0],
            [0, 0, 0, 0, 1],
            [1, 1, 0, 1, 1]]
Output: 3
Explanation:
grid[][] = [[1, 1, 0, 1, 1], 
            [1, 0, 0, 0, 0], 
            [0, 0, 0, 0, 1], 
            [1, 1, 0, 1, 1]]
Same colored islands are equal.
We have 4 islands, but 2 of them
are equal, So we have 3 distinct islands.

Your Task: You don't need to read or print anything. Your task is to complete the function countDistinctIslands() which takes 
the grid as an input parameter and returns the total number of distinct islands.
*/

class Solution {
    public String toString(int x, int y){
      return Integer.toString(x) + " " + Integer.toString(y);
    }
    
    public void dfs(int grid[][], boolean vis[][], List<String> list, int delrow[], 
    int delcol[], int sr, int sc, int n, int m, int base_sr, int base_sc){
        vis[sr][sc] = true;
        int x = sr - base_sr;
        int y = sc - base_sc;
        String s = toString(x, y);
        list.add(s);
        
        for(int k=0; k<4; k++){
            int row = sr + delrow[k];
            int col = sc + delcol[k];
            
            if(row>=0 && row<n && col>=0 && col<m &&
            grid[row][col] == 1 && vis[row][col] == false){
                dfs(grid, vis, list, delrow, delcol, row, col, n, m, base_sr, base_sc);
            }
        }
    }

    public int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        Set<List<String>> set = new HashSet<>();
        
        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(vis[i][j] == false && grid[i][j] == 1){
                    List<String> list = new ArrayList<>();
                    dfs(grid, vis, list, delrow, delcol, i, j, n, m, i, j);
                    // if(!set.contains(list)){
                      set.add(list);
                    
                }
            }
        }
        
        int ans = set.size();
        
        return ans;
    }
}
