class Solution {

    public boolean fun(TreeNode root, long min, long max){
        if(root == null) return true;

        if(root.val >= max || root.val <= min) return false;

        return fun(root.left, min, root.val) && fun(root.right, root.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return fun(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
