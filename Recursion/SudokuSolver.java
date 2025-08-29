class Solution {

    public boolean fun(char[][] board, int n){
        for(int row=0; row<n; row++){
            for(int col=0; col<n; col++){

                if(board[row][col] == '.'){

                    for(char c= '1'; c<='9'; c++){
                        if(isValid(board, row, col, c)){
                            board[row][col] = c;

                            if(fun(board, n) == true) return true;
                            else 
                                board[row][col] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char c){

        for(int i=0; i<9; i++){
            if(board[i][col] == c) return false;
            if(board[row][i] == c) return false;

            if(board[3 * (row/3) + (i/3)][3 * (col/3) + (i%3)] == c) return false;
        }

        return true;
    }

    public void solveSudoku(char[][] board) {
        fun(board, board.length);

    }
}
