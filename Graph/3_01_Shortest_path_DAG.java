// User function Template for Java
class Solution {
    
    class pair {
        int node;
        int dist;

        pair(int n, int d) {
            this.node = n;
            this.dist = d;
        }
    }

    public void topo(ArrayList<ArrayList<pair>> graph, int node, Stack<Integer> st, boolean vis[]) {
        vis[node] = true;

        for (pair next : graph.get(node)) {
            if (vis[next.node] == false) {
                topo(graph, next.node, st, vis);
            }
        }

        st.push(node);
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        boolean vis[] = new boolean[V];
        ArrayList<ArrayList<pair>> graph = new ArrayList<>();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int edge[] : edges) {
            int x = edge[0];
            int y = edge[1];
            int d = edge[2];

            graph.get(x).add(new pair(y, d));

        }

        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                topo(graph, i, st, vis);
            }
        }

        int ans[] = new int[V];
        for (int i = 0; i < V; i++) {
            ans[i] = Integer.MAX_VALUE;
        }
        while (st.peek() != 0) {
            ans[st.peek()] = -1;
            st.pop();
        }

        ans[0] = 0;
        while (!st.isEmpty()) {
            int curr = st.pop();

            for (pair next : graph.get(curr)) {
                int dist = Math.min(ans[curr] + next.dist, ans[next.node]);
                ans[next.node] = dist;
            }
        }

        return ans;

    }
}
