/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 
*/

class Solution {
    public void fun(int i, int j, int idx, char board[][], String word, int x[], int y[], int n, int m, boolean ans[], boolean vis[][]){
        // Base Case: Match failure
        if(board[i][j] != word.charAt(idx)) return;
        
        // Base Case: Word found
        if(idx == word.length() - 1){
            ans[0] = true;
            return;
        }

        // Mark current cell as visited
        vis[i][j] = true;

        for(int k=0; k<4; k++){
            int row = i + x[k];
            int col = j + y[k];

            if(row >= 0 && row < n && col >= 0 && col < m && !vis[row][col]){
                fun(row, col, idx + 1, board, word, x, y, n, m, ans, vis);
                // Early exit if solution is found
                if(ans[0]) return;
            }
        }

        // Backtrack: Unmark current cell for other potential paths
        vis[i][j] = false;
    }

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        int x[] = {-1, 0, 1, 0};
        int y[] = {0, 1, 0, -1};
        boolean ans[] = new boolean[1];
        boolean vis[][] = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // Start recursion if the first character matches
                if(board[i][j] == word.charAt(0)){
                    fun(i, j, 0, board, word, x, y, n, m, ans, vis);
                    if(ans[0]) return true;
                }
            }
        }

        return false;
    }
}
