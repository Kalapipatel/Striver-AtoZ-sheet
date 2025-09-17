class Solution {
    
    static class pair{
        Node node;
        int hd;
        
        pair(Node n, int h){
            this.node = n;
            this.hd = h;
        }
    }
    
    static ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(root, 0));
        
        while(!q.isEmpty()){
            pair curr = q.remove();
            
            map.put(curr.hd, curr.node.data);
            
            if(curr.node.left != null){
                q.add(new pair(curr.node.left, (curr.hd-1) ));
            }
            if(curr.node.right != null){
                q.add(new pair(curr.node.right, (curr.hd+1) ));
            }
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
    }
}
