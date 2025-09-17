class Solution {
    class pair {
        TreeNode node;
        int col;
        int level;

        pair(TreeNode n, int x, int y){
            this.node = n;
            this.col = x;
            this.level = y;
        }

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Map<Integer, TreeMap<Integer, PriorityQueue<Integer>> > map = new TreeMap<>();
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(root, 0 ,0));

        while(!q.isEmpty()){
            pair curr = q.remove();
            TreeNode node = curr.node;
            int col = curr.col;
            int level = curr.level;

            // checking by col
            if(!map.containsKey(col)){
                map.put(col, new TreeMap<>());
            } // then checking by level
            if(!map.get(col).containsKey(level)){
                map.get(col).put(level, new PriorityQueue<>());
            }
            // per col per row -> only one pq which is already added.
            // time more than 1 node can enter in pq like [5,6], 
            // both have (0,2)(col, level) cordinates so final res is (0, {2, [5,6]})
            map.get(col).get(level).add(node.val);

            // Level order steps(adding left and right nodes)
            if(node.left != null){
                q.add(new pair(node.left, col-1, level+1));
            }
            if(node.right != null){
                q.add(new pair(node.right, col+1, level+1));
            }
        }

        for(TreeMap<Integer, PriorityQueue<Integer>> subTree : map.values()){
            List<Integer> list = new ArrayList<>();

            for(PriorityQueue<Integer> pq : subTree.values()){
                while(!pq.isEmpty()){
                    list.add(pq.remove());
                }
            }

            ans.add(list);
        }

        return ans;
    }
}
