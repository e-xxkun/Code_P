package xxkun;

public class LeetCode98 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode node, Integer lower, Integer higher) {
        if (node == null)
            return true;

        int val = node.val;
        if (lower != null && val <= lower)
            return false;
        if (lower != null && val >= higher)
            return false;

        if (!isValidBST(node.left, lower, val))
            return false;
        if (!isValidBST(node.right, val, higher))
            return false;

        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, new Integer[1]);
    }

    public boolean isValidBST2(TreeNode node,Integer[] tmp) {
        if(node==null)
            return true;
        if(!isValidBST2(node.left,tmp))
            return false;
        if(tmp[0]!=null&&tmp[0]> node.val){
            return false;
        }
        tmp[0]= node.val;
        if(!isValidBST2(node.right,tmp))
            return false;
        return true;
    }
}