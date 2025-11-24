/*
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]Output: 2

Input: heights = [[1,2,3],[3,8,4],[5,3,5]]Output: 1

Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]Output: 0
*/


class Solution {
    class pair{
        int diff;
        int x;
        int y;

        pair(int d, int x, int y){
            this.diff = d;
            this.x = x;
            this.y = y;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<pair> pq = new PriorityQueue<>((x, y) -> x.diff - y.diff);
        pq.add(new pair(0, 0, 0));
        int n = heights.length;
        int m = heights[0].length;
        int ans = 0;

        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};

        int dist[][] = new int[n][m];
        for(int[] x: dist){
            Arrays.fill(x, Integer.MAX_VALUE);
        }

        while(!pq.isEmpty()){
            pair curr = pq.remove();

            if(curr.x == n-1 && curr.y == m-1) return curr.diff;

            for(int k=0; k<4; k++){
                int row = curr.x + delrow[k];
                int col = curr.y + delcol[k];

                if(row>=0 && row<n && col>=0 && col<m){
                    int newEffort = Math.max(curr.diff, Math.abs(heights[row][col] - dist[curr.x][curr.y]));

                    if(newEffort < dist[row][col]){
                        dist[row][col] = newEffort;
                        pq.add(new pair(newEffort, row, col));
                    }
                }
            }
        }

        return 0;
    }
}
