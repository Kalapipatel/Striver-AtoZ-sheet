class Solution {
    static class pair{
        Node node;
        int hd;
        
        pair(Node n, int hd){
            this.node = n;
            this.hd = hd;
        }
    }
    
    public static int min = 0;
    public static int max = 0;
    public static void fun(Node root, Map<Integer, Node> map){
        Queue<pair> q = new LinkedList<>();
        
        q.add(new pair(root, 0));
        
        while(!q.isEmpty()){
            pair curr = q.remove();
            
            if(!map.containsKey(curr.hd)){
                map.put(curr.hd, curr.node);
            }
            if(curr.node.left != null){
                q.add(new pair(curr.node.left, curr.hd-1));
                min = Math.min(min, curr.hd-1);
            }
            if(curr.node.right != null){
                q.add(new pair(curr.node.right, curr.hd+1));
                max = Math.max(max, curr.hd+1);
            }
        }
    }
    
    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Map<Integer, Node> map = new HashMap<>();
        
        fun(root, map);
        
        for(int i=min; i<=max; i++){
            if(map.get(i) != null) ans.add(map.get(i).data);
        }
        
        return ans;
    }
}
