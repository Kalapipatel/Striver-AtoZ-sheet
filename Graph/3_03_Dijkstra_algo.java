class Solution {
    
    class pair{
        int node;
        int dist;
        
        pair(int n, int d){
            this.node = n;
            this.dist = d;
        }
    }
    
    public int[] dijkstra(int V, int[][] edges, int src) {

        ArrayList<ArrayList<pair>> graph = new ArrayList<>();
        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }    
        for(int x[] : edges){
            graph.get(x[0]).add(new pair(x[1], x[2]));
            graph.get(x[1]).add(new pair(x[0], x[2]));
        }
        
        PriorityQueue<pair> pq = new PriorityQueue<pair>((x,y) -> x.dist - y.dist);
        int ans[] = new int[V];
        Arrays.fill(ans, Integer.MAX_VALUE);
        
        ans[src] =0;
        pq.add(new pair(src, 0));
        
        while(!pq.isEmpty()){
            pair curr = pq.remove();
            int node = curr.node;
            int dist = curr.dist;
            
            for(int i=0; i<graph.get(node).size(); i++){
                int adjnode = graph.get(node).get(i).node;
                int weight = graph.get(node).get(i).dist;
                
                if(dist + weight < ans[adjnode]){
                    ans[adjnode] = dist + weight;
                    pq.add(new pair(adjnode, ans[adjnode]));
                }
            }
        }
        
        return ans;
    }
}
