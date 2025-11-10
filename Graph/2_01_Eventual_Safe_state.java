/*
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D 
integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i 
to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that 
node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
*/

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
