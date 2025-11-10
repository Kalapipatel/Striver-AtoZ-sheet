class Solution {
    public void dfs(int node, boolean vis[], Stack<Integer> st, ArrayList<ArrayList<Integer>> graph){
        vis[node] = true;
        
        for(int x : graph.get(node)){
            if(vis[x] == false){
                dfs(x, vis, st, graph);
            }
        }
        st.push(node);
    }
    
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        boolean vis[] = new boolean[V];
        Stack<Integer> st = new Stack<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] edge: edges){
            int x = edge[0];
            int y = edge[1];
            
            graph.get(x).add(y);
            
        }
        
        for(int i=0; i<V; i++){
            if(vis[i] == false){
                dfs(i, vis, st, graph);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        int i=0;
        while(!st.isEmpty()){
            ans.add(st.pop());
        }
        
        return ans;
    }
}
