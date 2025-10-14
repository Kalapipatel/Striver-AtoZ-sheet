class Solution {
    public void dfs(int node, int[][] graph, boolean vis[]){

        vis[node] = true;

        for(int i=0; i<graph.length; i++){
            if(vis[i] == false && graph[node][i] == 1){
                dfs(i, graph, vis);
            }
        }
    }

    public int findCircleNum(int[][] arr) {
        int len = arr.length;
        boolean vis[] = new boolean[len];
        int cnt = 0;

        for(int i=0; i<len; i++){
            if(!vis[i]){
                dfs(i, arr, vis);
                cnt++;
            }
        }

        return cnt;
    }
}
