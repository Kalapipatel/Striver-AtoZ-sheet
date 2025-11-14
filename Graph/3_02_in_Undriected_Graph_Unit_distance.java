class Solution {
    public int[] shortestPath(int V, int[][] edges, int src) {

        // Step 1: Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (u >= 0 && u < V && v >= 0 && v < V) { // safety check
                adj.get(u).add(v);
                adj.get(v).add(u); // undirected graph
            }
        }

        // Step 2: Initialize distance array
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Invalid source safety check
        if (src < 0 || src >= V) return dist;

        // Step 3: BFS setup
        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0;
        q.add(src);

        // Step 4: BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbor : adj.get(node)) {
                if (neighbor < 0 || neighbor >= V) continue;
                if (dist[neighbor] == Integer.MAX_VALUE) {
                    dist[neighbor] = dist[node] + 1;
                    q.add(neighbor);
                }
            }
        }

        // Step 5: Replace unreachable nodes with -1
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }

        return dist;
    }
}
