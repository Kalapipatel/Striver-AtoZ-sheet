class Solution {
    public boolean dfs(int[][] graph, int vis[], int node, int col){
        
        vis[node] = col;

        for(int x : graph[node]){
            if(vis[x] == 0){
                if(dfs(graph, vis, x, col*-1) == false) return false;
            }
            else if(vis[x] == col) return false; 
        }

        return true;        
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int vis[] = new int[n];

        for(int i=0; i<n; i++){
            if(vis[i] == 0){
                if(dfs(graph, vis, i, -1) == false) return false;
            }
        }

        return true;
    }
}
