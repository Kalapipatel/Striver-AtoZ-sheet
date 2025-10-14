class Solution {
    class pair{
        int x;
        int y;

        pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int bfs(int grid[][], int arr[][], int delrow[], int delcol[]){
        Queue<pair> q = new LinkedList<>();
        int n = arr.length;
        int m = arr[0].length;
        int ones = 0;
        int zeros = 0;
        int twos = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1) ones++;
                if(grid[i][j] == 0) zeros++;
                if(grid[i][j] == 2){
                    q.add(new pair(i, j));
                    twos++;
                }
            }
        }

        if(ones == 0) return 0;
        if(twos == 0) return 0;
        
        int count = -1;
        while(!q.isEmpty()){
            
            int len = q.size();
            for(int i=0; i<len; i++){
                pair curr = q.remove();
                int x = curr.x;
                int y = curr.y;

                for(int k=0; k<4; k++){
                    int row = x + delrow[k];
                    int col = y + delcol[k];

                    if(row>=0 && row<n && col>=0 && col<m && arr[row][col] == 1){
                        q.add(new pair(row, col));
                        arr[row][col] = 2;
                    }
                }
                

            }

            count++;
        }

        return count;
    }

    public int orangesRotting(int[][] grid) {
        int arr[][] = grid;
        int n = grid.length;
        int m = grid[0].length;
        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};

        int ans = bfs(grid, arr, delrow, delcol);
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr[i][j] == 1){
                    ans = -1;
                    break;
                }
            }
        }

        return ans;
    }
}
