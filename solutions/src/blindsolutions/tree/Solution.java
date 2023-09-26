package blindsolutions.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    // Find all the paths that sum to a given value.
    //
    //The path does not need to start or end at the root or a leaf, but it must go downwards
    // (traveling only from parent nodes to child nodes).

    //Example:

    //      10
    //     /  \
    //    5   -3
    //   / \    \
    //  3   2   11
    // / \   \
    //3  -2   1

    //Return: [[5, 3], [5, 2, 1], [-3, 11]]
    List<List<Integer>> paths = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>());
        pathSum(root, 0, sum, new ArrayList<>(), map);
        return paths;
    }

    private void pathSum(TreeNode root, int currSum, int target, List<Integer> currList, Map<Integer, List<Integer>> map) {
        if (root == null)
            return;

        currSum += root.val;
        currList.add(root.val);
        if (map.containsKey(currSum - target)) {
            List<Integer> prevPath = map.get(currSum - target), temp = new ArrayList<>();
            for (int i = prevPath.size(); i < currList.size(); i++)
                temp.add(currList.get(i));
            paths.add(temp);
        }
        map.put(currSum, new ArrayList<>(currList));
        pathSum(root.left, currSum, target, currList, map);
        pathSum(root.right, currSum, target, currList, map);
        currList.remove(currList.size() - 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}