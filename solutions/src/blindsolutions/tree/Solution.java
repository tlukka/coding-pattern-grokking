package blindsolutions.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    // Print parents of all leaves in a BST
    // Input : TreeNode Root which is root of the entire BST.
    //          500
    //	      /  \
    //       300    600
    //        / \
    //     100  350
    //         /   \
    //	  320    360
    //
    //Answer : 500,300,350
    //Reason : 500 is parent of leaf 600. 300 is parent of leaf 100. 350 is parent of leaves 320 and 360.

    List<Integer> parentOfLeafNodes(TreeNode root) {
        List<Integer> parents = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if ((current.left != null && current.left.left == null && current.left.right == null)
                    || (current.right != null && current.right.left == null && current.right.right == null)) {
                parents.add(current.val);
            }
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return parents;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}