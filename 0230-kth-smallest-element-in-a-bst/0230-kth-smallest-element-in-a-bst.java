class Solution {
    private int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        return traverse(root, k);
    }

    private int traverse(TreeNode root, int k) {
        if (root == null) return -1;

        int left = traverse(root.left, k);
        if (left != -1) return left;

        count++;
        if (count == k) return root.val;

        // Traverse right subtree
        return traverse(root.right, k);
    }
}
