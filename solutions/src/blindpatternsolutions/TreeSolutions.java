package blindpatternsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TreeSolutions {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Kth Smallest in Binary Tree
    int kthSmallestBST(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0)
                return root.val;
            root = root.right;
        }
    }

    // Valid Binary Search Tree
    boolean isValidBST(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        TreeNode prev = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null && prev.val >= curr.val)
                return false;
            prev = curr;
            curr = curr.right;
        }
        return true;
    }

    // Construct Binary Tree from Preorder and Inorder Traversal
    int preIndex = 0;
    HashMap<Integer, Integer> buildMap = new HashMap<>();

    TreeNode buildTree(int[] preOrder, int[] inorder) {
        if (preOrder == null || preOrder.length == 0)
            return null;
        for (int i = 0; i < inorder.length; i++) {
            buildMap.put(inorder[i], i);
        }
        return buildTree(preOrder, 0, inorder.length - 1);
    }

    TreeNode buildTree(int[] preorder, int start, int end) {
        if (start > end)
            return null;
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, start, buildMap.get(rootVal) - 1);
        root.right = buildTree(preorder, buildMap.get(rootVal) + 1, end);
        return root;
    }

    // Subtree of Another tree
    boolean isSubTree(TreeNode root, TreeNode subRoot) {
        if (root == null)
            return false;
        if (root.val == subRoot.val && isSameTree(root, subRoot))
            return true;
        return isSameTree(root.left, subRoot) || isSameTree(root.right, subRoot);
    }

    // Serialize and Deserialize Binary Tree
    String serilaize(TreeNode root) {
        if (root == null)
            return "#";
        String left = serilaize(root.left);
        String right = serilaize(root.right);
        return root.val + "," + left + "," + right;
    }

    TreeNode deserilaize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfsDeserilaize(queue);
    }

    TreeNode dfsDeserilaize(Queue<String> queue) {
        String data = queue.poll();
        if (data.equals("#"))
            return null;
        TreeNode node = new TreeNode(Integer.valueOf(data));
        node.left = dfsDeserilaize(queue);
        node.right = dfsDeserilaize(queue);
        return node;
    }

    // Level order printing in Binary Tree
    List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            while (size > 0) {
                TreeNode temp = queue.poll();
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
                currentLevel.add(temp.val);
                size--;
            }
            result.add(currentLevel);
        }
        return result;
    }

    //Binary Tree Maximum Path Sum
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumDfs(root);
        return max;
    }

    int maxPathSumDfs(TreeNode root) {
        if (root == null)
            return 0;
        int leftSubTree = Math.max(0, maxPathSumDfs(root.left));
        int rightSubTree = Math.max(0, maxPathSumDfs(root.right));
        max = Math.max(max, root.val + leftSubTree + rightSubTree);
        return root.val + Math.max(leftSubTree, rightSubTree);
    }

    TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    // Same Tree
    boolean isSameTree(TreeNode p, TreeNode q) {
        return dfsSameTree(p, q);
    }

    boolean dfsSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return dfsSameTree(p.left, q.left) && dfsSameTree(p.right, q.right);
    }

    // Max Depth Binary Tree
    int maxDepth(TreeNode root) {
        return depthDfs(root);
    }

    int depthDfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = depthDfs(root.left);
        int right = depthDfs(root.right);
        return 1 + Math.max(right, left);
    }

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
