class Solution {
    class pair{
    int node;
    int parent;
    
    pair(int n, int p){
      this.node = n;
      this.parent = p;
    }
  }
  
  public boolean bfs(int src, int V, List<List<Integer>> graph, boolean vis[]){
      vis[src] = true;
      Queue<pair> q = new LinkedList<>();
      q.add(new pair(src, -1));
      
      while(!q.isEmpty()){
        pair curr = q.remove();
        int node = curr.node;
        int parent = curr.parent;
        
        for(int x : graph.get(node)){
          if(vis[x] == false){
            vis[x] = true;
            q.add(new pair(x, node));
          }
          
          else if(x != parent) return true;
        }
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
                if(bfs(i, V, graph, vis) == true) return true;
            }
        }
        
        return false;
        
    }
}
