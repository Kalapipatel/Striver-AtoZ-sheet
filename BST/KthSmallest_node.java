class Solution {

    int ans = 0;
    int cnt = 0;
    public void inOrder(TreeNode root, int k){
        if(root == null) return;

        inOrder(root.left, k);
        cnt++;
        if(cnt == k) ans = root.val;
        inOrder(root.right, k);
    }
        
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return ans;
    }
}
