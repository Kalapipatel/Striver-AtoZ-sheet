import java.util.*;

class Main{
    
    public static void createGraph(List<List<Integer>> graph, int V){
        for(int i=0; i<=V; i++){
          graph.add(new ArrayList<>());
        }
        
        graph.get(1).add(2);
        graph.get(1).add(6);
        
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(4);
        
        graph.get(3).add(2);
        
        graph.get(4).add(2);
        graph.get(4).add(5);
        
        graph.get(5).add(4);
        graph.get(5).add(8);
        
        graph.get(8).add(5);
        graph.get(8).add(7);
        
        graph.get(7).add(6);
        graph.get(7).add(8);
        
        graph.get(6).add(1);
        graph.get(6).add(7);
        graph.get(6).add(9);
        
        graph.get(9).add(6);
        
    }
    
    public static void fun(List<List<Integer>> graph, int node, boolean vis[], List<Integer> list){
        
        vis[node] = true;
        list.add(node);

        
        for(int x : graph.get(node)){
          if(vis[x] == false){
            fun(graph, x, vis, list);
          }
        }
    }
    
    public static List<Integer> dfs(List<List<Integer>> graph, int V){
        boolean vis[] = new boolean[V+1];
        vis[0] = true;
        List<Integer> dfsList = new ArrayList<>();
        
        fun(graph, 1, vis, dfsList);
        
        
        return dfsList;
    }
    
    public static void main(String args[]){
        List<List<Integer>> graph = new LinkedList<>();
        int V = 9;
        createGraph(graph, V);
        
        System.out.println(graph);
        
        System.out.println(dfs(graph, V));
    }
}
