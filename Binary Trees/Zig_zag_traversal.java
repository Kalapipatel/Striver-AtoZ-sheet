class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        fun(root, ans);

        return ans;
    }

    public int flag = 1;

    public void fun(TreeNode root, List<List<Integer>> ans){
        if(root == null) return;

        Queue<TreeNode> q = new LinkedList<>();        
        q.add(root);

        while(!q.isEmpty()){
            int num = q.size();
            List<Integer> list = new ArrayList<>();

            for(int i=0; i<num; i++){
                TreeNode curr = q.remove();

                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
                if(flag < 0){        // adding at front
                    list.add(0, curr.val);
                }else{               // adding at last
                    list.add(curr.val);
                }
            }

            // if(flag < 0){
            //     Collections.reverse(list);
            // }                    // we can also do reverse but it will take more time
            ans.add(list);
            flag = flag*-1;
        }
        
    }
}
