class Solution {
    public boolean bfs(int[][] graph, int vis[], int sr){
        Queue<Integer> q = new LinkedList<>();
        q.add(sr);
        vis[sr] = -1;

        while(!q.isEmpty()){
            int curr = q.remove();
            int col = vis[curr];

            for(int x : graph[curr]){
                if(vis[x] == 0){
                   vis[x] =  col * -1;
                   q.add(x);
                }
                else if(vis[x] == col){
                    return false;
                }
            }
        }

        return true;        
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int vis[] = new int[n];

        for(int i=0; i<n; i++){
            if(vis[i] == 0){
                if(bfs(graph, vis, i) == false) return false;
            }
        }

        return true;
    }
}
