/*
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
*/

class Solution {
    class pair {
        long dist; // Changed to long
        int node;

        pair(long d, int n) {
            this.dist = d;
            this.node = n;
        }
    }

    public int countPaths(int n, int[][] roads) {
        PriorityQueue<pair> pq = new PriorityQueue<>((x, y) -> Long.compare(x.dist, y.dist));
        pq.add(new pair(0, 0));

        List<List<pair>> graph = new ArrayList<>();
        long[] ans = new long[n]; // Changed to long
        int[] ways = new int[n];

        Arrays.fill(ans, Long.MAX_VALUE); 
        ans[0] = 0;
        ways[0] = 1;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            int w = road[2];

            graph.get(x).add(new pair(w, y));
            graph.get(y).add(new pair(w, x));
        }

        int mod = (int) (1e9 + 7);
        
        while (!pq.isEmpty()) {
            pair curr = pq.remove();

            // Optimization: If current distance is already worse than best found, skip
            if (curr.dist > ans[curr.node]) continue;

            for (pair next : graph.get(curr.node)) {
                long weight = next.dist; // weight is long
                int adjNode = next.node;

                long totalDist = weight + curr.dist; // Calculation in long

                if (totalDist < ans[adjNode]) {
                    ans[adjNode] = totalDist;
                    pq.add(new pair(totalDist, adjNode));
                    ways[adjNode] = ways[curr.node];
                } else if (totalDist == ans[adjNode]) {
                    ways[adjNode] = (ways[curr.node] + ways[adjNode]) % mod;
                }
            }
        }

        return ways[n - 1];
    }
}
