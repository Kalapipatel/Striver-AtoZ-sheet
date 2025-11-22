/*
You are given a weighted undirected graph with n vertices numbered from 1 to n and m edges along with their weights. Find the shortest path between vertex 1 and vertex n. Each edge is given as {a, b, w}, denoting an edge between vertices a and b with weight w.

If a path exists, return a list of integers where the first element is the total weight of the shortest path, and the remaining elements are the nodes along that path (from 1 to n). If no path exists, return a list containing only {-1}.

Note: The driver code will internally verify your returned list.

If both the path and its total weight are valid, only the total weight will be displayed as output.
If your list contains only -1, the output will be -1.
If the returned list is invalid, the output will be -2.

Input: n = 5, m= 6, edges = [[1, 2, 2], [2, 5, 5], [2, 3, 4], [1, 4, 1], [4, 3, 3], [3, 5, 1]]
Output: 5
Explanation: Shortest path from 1 to n is by the path 1 4 3 5 whose weight is 5. 

Input: n = 2, m= 1, edges = [[1, 2, 2]]
Output: 2
Explanation: Shortest path from 1 to 2 is by the path 1 2 whose weight is 2. 

Input: n = 2, m= 0, edges = [ ]
Output: -1
Explanation: Since there are no edges, so no answer is possible.
*/

import java.util.*;

class Solution {
    
    class pair{
        int node;
        int dist;
        
        pair(int n, int d){
            this.node = n;
            this.dist = d;
        }
    }
    
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        List<Integer> path = new ArrayList<>();
        
        // Create adjacency list
        ArrayList<ArrayList<pair>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }    
        for(int x[] : edges){
            graph.get(x[0]).add(new pair(x[1], x[2]));
            graph.get(x[1]).add(new pair(x[0], x[2]));
        }
        
        PriorityQueue<pair> pq = new PriorityQueue<pair>((x,y) -> x.dist - y.dist);
        int parent[] = new int[n+1];
        int ans[] = new int[n+1];
        
        for(int i=0; i<=n; i++){
            parent[i] = i;
            ans[i] = Integer.MAX_VALUE;
        }
        
        pq.add(new pair(1, 0)); 
        ans[1] = 0;
        
        while(!pq.isEmpty()){
            pair curr = pq.remove();
            
            int node = curr.node; 
            int dist = curr.dist; 
            
            for(pair x : graph.get(node)){
                int next = x.node;
                int weight = x.dist;
                
                if(dist + weight < ans[next]){
                    int tdist = dist + weight;
                    ans[next] = tdist;
                    pq.add(new pair(next, tdist)); // Note: pass (node, dist)
                    parent[next] = node;
                }
            }
        }
        
        // If destination is unreachable
        if(ans[n] == Integer.MAX_VALUE){
            path.add(-1);
            return path; 
        }
        
        // Path reconstruction
        int node = n;
        while(parent[node] != node){
            path.add(node);
            node = parent[node];
        }
        path.add(1); 
        
        Collections.reverse(path);
        
        path.add(0, ans[n]);
        
        return path;
    }
}
