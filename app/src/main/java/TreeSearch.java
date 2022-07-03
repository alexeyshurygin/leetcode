import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Alexey Shurygin
 */
class PrintTree {


//
//     5
//             / \
//             3   5
//             / \   \
//             1   6   6
//             \
//             9
//
//             5
//             3 5
//             1 6 6
//             9

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }


    void levels(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        TreeNode last = root;
        nodes.add(root);
        System.out.println(root.val);
        TreeNode lastLevel = null;
        while (!nodes.isEmpty()) {
            boolean printed = false;
            TreeNode n = nodes.poll();
            if (n.left != null) {
                nodes.add(n.left);
                printed = true;
                System.out.print(n.left.val);
                lastLevel = n.left;
            }
            if (n.right != null) {
                nodes.add(n.right);
                if (printed)
                    System.out.print(' ');
                System.out.print(n.right.val);
                lastLevel = n.right;
            }
            if (n == last) {
                System.out.println();
                if (lastLevel != null) {
                    last = lastLevel;
                    lastLevel = null;
                }
            }
        }
    }
}
