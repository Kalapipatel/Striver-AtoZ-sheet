import java.utili.*;

class Main{
  public static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int pathvis[]){
    vis[node] = 1;
    pathvis[node] = 1;
    
    for(int it: adj.get(node)){
      if(vis[it] == 0){
        if(dsf(it, adj, vis, pathvis) == true){
          return true;
        }
        else if(pathvis[it] == 1){
          return true;
        }
      }
    }
    
    pathvis[node] = 0;
    return false;
  }
  
  public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj){
    int vis[] = new int[V];
    int pathvis[] = new int[V];
    
    for(int i=0; i<V; i++){
      if(vis[i] == 0){
        if(dfs(i, adj, vis, pathvis) == true) return true;
      }
    }
    
    return false;
  }
}
