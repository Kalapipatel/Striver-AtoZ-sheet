class Solution {
    public boolean dfs(int graph[][], boolean vis[], boolean pathvis[], boolean check[], int node){
        vis[node] = true;
        pathvis[node] = true;
        check[node] = false;

        for(int x : graph[node]){
            if(vis[x] == false){
                if(dfs(graph, vis, pathvis, check, x) == true) return true;
            }
            else if(pathvis[x] == true) return true;

        }

        pathvis[node] = false;
        check[node] = true;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        boolean vis[] = new boolean[n];
        boolean pathvis[] = new boolean[n];
        boolean check[] = new boolean[n];
        List<Integer> ans = new ArrayList<>();

        for(int i=0; i<n; i++){
            if(vis[i] == false){
                dfs(graph, vis, pathvis, check, i);
            }            
        }

        for(int i=0 ;i<n; i++){
            if(check[i] == true) ans.add(i);
        }

        return ans;
    }
}
