// User function Template for Java

class Solution {
    
    class pair{
        int steps;
        int x;
        int y;
        
        pair(int s, int x, int y){
            this.steps = s;
            this.x = x;
            this.y = y;
        }
        
    }

    int shortestPath(int[][] grid, int[] source, int[] dest) {

        PriorityQueue<pair> pq = new PriorityQueue<>((x,y) -> x.steps - y.steps);
        int min = -1;
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
                
        pq.add(new pair(0, source[0], source[1]));
        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};
        
        while(!pq.isEmpty()){
            pair curr = pq.remove();
            
            if(curr.x == dest[0] && curr.y == dest[1]){
                min = curr.steps;
                break;
            }
            
            for(int k=0; k<4; k++){
                int row = curr.x + delrow[k];
                int col = curr.y + delcol[k];
                
                if(row>=0 && row<n && col>=0 && col<m && grid[row][col]==1 && vis[row][col] == false){
                    vis[row][col] = true;
                    pq.add(new pair(curr.steps+1, row, col));
                    
                }
            }
        }
        
        return min;
    }
}
