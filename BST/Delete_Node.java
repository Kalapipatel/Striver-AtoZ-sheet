class Solution {

    public TreeNode rightMost(TreeNode root){
        if(root.right == null) return root;
        return rightMost(root.right);
    }

    public TreeNode helper(TreeNode root){
        if(root.left == null) return root.right; // left tree not there then return right
        else if(root.right == null) return root.left;

        TreeNode rightChild = root.right;
        TreeNode lastRight = rightMost(root.left);
        lastRight.right = rightChild;

        return root.left;
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val == key) return helper(root);

        TreeNode dummy = root;
        while(root != null){
            if(key < root.val){
                if(root.left != null && root.left.val == key){
                    root.left = helper(root.left);
                    break;
                }
                else{
                    root = root.left;
                }
            }
            else{
                if(root.right != null && root.right.val == key){
                    root.right = helper(root.right);
                    break;
                }
                else{
                    root = root.right;
                }
            }
        }

        return dummy;
    }
}
