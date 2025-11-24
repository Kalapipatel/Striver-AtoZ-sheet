class Solution {
    class pair{
        int stops;
        int node;
        int dist;

        pair(int s, int n, int d){
            this.stops = s;
            this.node = n;
            this.dist = d;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(0, src, 0));

        int ans[] = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[src] = 0;

        List<List<pair>> graph = new ArrayList<>();
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(int arr[] : flights){
            int x = arr[0];
            int y = arr[1];
            int cost = arr[2];

            graph.get(x).add(new pair(0, y, cost));
        }

        while(!q.isEmpty()){
            pair curr = q.remove();

            if(curr.stops > k) continue;
            for(pair next : graph.get(curr.node)){
                int adjNode = next.node;
                int adjWeight = next.dist;

                if(curr.dist + adjWeight  < ans[adjNode] && curr.stops <= k){
                    ans[adjNode] =  curr.dist + adjWeight;
                    q.add(new pair(curr.stops + 1, adjNode, curr.dist + adjWeight));
                }
            }
        }

        if(ans[dst] == Integer.MAX_VALUE) return -1;
        return ans[dst];
    }
}


