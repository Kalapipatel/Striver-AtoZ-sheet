class Solution {
    class pair{
        TreeNode node;
        int level;

        pair(TreeNode n, int l){
            this.node = n;
            this.level = l;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Map<Integer, Integer> map = new HashMap<>();
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(root, 0));
        int max = 0;
        
        while(!q.isEmpty()){
            pair curr = q.remove();
            
            // if(!map.containsKey(curr.level)){
              map.put(curr.level, curr.node.val);
            // }
            if(curr.node.left != null){
              q.add(new pair(curr.node.left, curr.level+1));
              max = Math.max(max, curr.level+1);
            }
            if(curr.node.right != null){
              q.add(new pair(curr.node.right, curr.level+1));
              max = Math.max(max, curr.level+1);
            }
        }
        
        for(int i=0; i<=max; i++){
          ans.add(map.get(i));
        }

        return ans;
    }
}
