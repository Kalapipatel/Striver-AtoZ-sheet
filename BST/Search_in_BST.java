class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        
        while(root != null){
            if(root.val == val) return root;
            else root = (val < root.val) ? root.left : root.right;
        }
        return root;
    }
}
