// Khan's algorithm is nothing but Topological sort using BFS. We already solved it using DFS

class Solution {
    public void bfs(ArrayList<ArrayList<Integer>> graph, int V, int indg[], ArrayList<Integer> ans){
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<V; i++){
            if(indg[i] == 0) q.add(i);
        }
        
        while(!q.isEmpty()){
            int curr = q.remove();
            ans.add(curr);
            
            for(int next : graph.get(curr)){
                indg[next]--;
                
                if(indg[next] == 0){
                    q.add(next);
                }
            }
        }
    }
    
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        int indegree[] = new int[V];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int edge[] : edges){
            int x = edge[0];
            int y = edge[1];
            
            indegree[y]++;
            
            graph.get(x).add(y);
        }
        
        
        bfs(graph, V, indegree, ans);
        
        
        return ans;
    }
}
