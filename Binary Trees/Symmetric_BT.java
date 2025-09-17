class Solution {
    public boolean fun(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        else if(p == null || q == null) return false;
        
        return (p.val == q.val) && fun(p.left, q.right) && fun(p.right, q.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return fun(root.left, root.right);
    }
}
