// solution 1 (not so optimal)

import java.util.*;

class Main{
    
    public static boolean isSafe(int row, int col, char[][] board, int n) {
        // Check upper-left diagonal
        int dubrow = row;
        int dubcol = col;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row--;
            col--;
        }

        // Check horizontal left
        row = dubrow;
        col = dubcol;
        while (col >= 0) {
            if (board[row][col] == 'Q') return false;
            col--;
        }

        // Check lower-left diagonal
        row = dubrow;
        col = dubcol;
        while (row < n && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row++;
            col--;
        }

        return true;
    }
    
    public static List<String> constructBoard(char[][] board, int n) {
        List<String> currentBoard = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            currentBoard.add(new String(board[i]));
        }
        return currentBoard;
    }
    
    public static void fun(int n, List<List<String>> ans, int col, char[][] board){
        
        if(col == n){
            ans.add(constructBoard(board, n));
            return;
        }
        
        for(int row=0; row<n; row++){
            if(isSafe(row, col, board, n)){
                char ch = 'Q';
                board[row][col] = 'Q';
                fun(n, ans, col + 1, board);
                board[row][col] = '.';
            }
        }
    }
    
    public static List<List<String>> nQueens(int n){
        List<List<String>> ans = new ArrayList<>();
        
        char[][] board = new char[n][n];

        // Initialize the board with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        fun(n, ans, 0, board);
        
        return ans;
    }
    
    public static void main(String args[]){
        int n = 4;
        
        List<List<String>> ans = new ArrayList<>();
        ans = nQueens(n);
        System.out.println(ans);
    }
}


// ----------------------------------------------------------------------------------------------------------------
// solution 2 (optimal)

import java.util.*;

class Main{
    
    public static List<String> charRowToString(char board[][], int n){
        List<String> currentBoard = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            currentBoard.add(new String(board[i]));
        }
        
        return currentBoard;
    }
    
    public static void fun(int n, int col, List<List<String>> ans, char board[][], boolean leftCol[], boolean upperDiagnol[], boolean lowerDiagnol[]){
        
        if(col == n){
            ans.add(charRowToString(board, n));
            return;
        }
        
        for(int row=0; row<n; row++){
            if(leftCol[row] == false && upperDiagnol[n-1+col-row] == false && lowerDiagnol[col+row] == false){
                
                board[row][col] = 'Q';
                leftCol[row] = true;
                upperDiagnol[n-1+col-row] = true;
                lowerDiagnol[row+col] = true;
                
                fun(n, col+1, ans, board, leftCol, upperDiagnol, lowerDiagnol);
                
                board[row][col] = '.';
                leftCol[row] = false;
                upperDiagnol[n-1+col-row] = false;
                lowerDiagnol[row+col] = false;
            }
        }
    
    }
    
    public static List<List<String>> nQueens(int n){
        List<List<String>> ans = new ArrayList<>();
        
        char board[][] = new char[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(board[i], '.');
        }
        
        boolean leftCol[] = new boolean[n];
        boolean upperDiagnol[] = new boolean[2*n-1];
        boolean lowerDiagnol[] = new boolean[2*n-1];
        
        fun(n, 0, ans, board, leftCol, upperDiagnol, lowerDiagnol);
        
        return ans;
    }
    
    public static void main(String args[]){
        int n = 4;
        
        List<List<String>> ans = new ArrayList<>();
        ans = nQueens(n);
        
        System.out.println(ans);
    }
}
