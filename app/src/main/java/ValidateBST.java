import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 *
 * @author Alexey Shurygin
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class ValidateBST {
    record K(TreeNode n, Integer lt, Integer gt) {
    }

    public boolean isValidBST(TreeNode root) {
        Queue<K> q = new ArrayDeque<>();
        q.add(new K(root, null, null));
        while (!q.isEmpty()) {
            K e = q.remove();
            if (e.lt != null && e.lt <= e.n.val)
                return false;
            if (e.gt != null && e.gt >= e.n.val)
                return false;
            if (e.n.left != null)
                q.add(new K(e.n.left, e.n.val, e.gt));
            if (e.n.right != null)
                q.add(new K(e.n.right, e.lt, e.n.val));
        }
        return true;
    }
}
