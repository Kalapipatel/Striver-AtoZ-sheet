class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int V = numCourses;
        int indegree[] = new int[V];

        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : prerequisites){
            int x = edge[1];
            int y = edge[0];

            indegree[y]++;
            graph.get(x).add(y);
        }

        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<V; i++){
            if(indegree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()){
            int curr = q.remove();
            ans.add(curr);

            for(int next : graph.get(curr)){
                indegree[next]--;

                if(indegree[next] == 0){
                    q.add(next);
                }
            }
        }

        if(ans.size() == V){  // return true dirctly if Order is not require
            int arr[] = new int[ans.size()];

            for(int i=0; i<ans.size(); i++){
                arr[i] = ans.get(i);
            }
            
            return arr;
        }
        else{
            return new int[0];
        }
    }
}
