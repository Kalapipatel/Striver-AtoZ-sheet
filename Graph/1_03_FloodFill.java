class Solution {

  public void dfs(int image[][], int ans[][], int delrow[], int delcol[],
  int newcol, int precol, int sr, int sc) {

        ans[sr][sc] = newcol;
        int n = image.length;
        int m = image[0].length;

        for(int i=0; i<4; i++){
            int row = sr + delrow[i];
            int col = sc + delcol[i];

            if(row>=0 && row<n && col>=0 && col<m &&
            image[row][col] == precol && ans[row][col] != newcol){
                dfs(image, ans, delrow, delcol, newcol, precol, row, col);
            }
        }
  }
    
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int n = image.length;
    int m = image[0].length;
    int ans[][] = image;
    
    int delrow[] = {-1, 0, 1, 0};
    int delcol[] = {0, 1, 0, -1};
    int initialcol = image[sr][sc];
    
    dfs(image, ans, delrow, delcol, color, initialcol, sr, sc);
    
    return ans;
  }

}
