class Solution {
    public boolean dfs(List<List<Integer>> graph, boolean vis[], int node, int parent){
        
        vis[node] = true;
        
        for(int x : graph.get(node)){
            if(vis[x] == false){
                if(dfs(graph, vis, x, node) == true) return true;
                
            }
            else if(x != parent) return true;
        }
        
        return false;
    }
    
    public boolean isCycle(int V, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        boolean vis[] = new boolean[V];
        
        for(int i=0 ; i<V; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] arr : edges){
            int i = arr[0];
            int j = arr[1];
            
            graph.get(i).add(j);
            graph.get(j).add(i);
        }
        
        for(int i=0; i<V; i++){
            if(vis[i] == false){
                if(dfs(graph, vis, i, -1) == true) return true;
            }
        }
        
        return false;
        
    }
}
