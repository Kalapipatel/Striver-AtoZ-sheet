class Solution {
    int res = 0;

    public int fun(TreeNode root){
        if(root == null) return 0;

        int left = fun(root.left);
        int right = fun(root.right);

        res = Math.max(res, left+right);
        
        return 1 + Math.max(left, right);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        fun(root);
        return res;
    }
}
