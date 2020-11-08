package xxkun;

public class LeetCode226 {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode leftTmp=root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(leftTmp);

        return  root;
    }

    public static void main(String[] args) {

    }
}
