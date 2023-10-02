package grokkingpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TreeSolutions {
    public static void main(String[] args) {
        // Tree root Initialization
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        //root.print();

        // BFS Solutions Test
        BFSSolutions bfsSolutions = new BFSSolutions();
        /*List<List<Integer>> levelOrders = bfsSolutions.levelOrder(root);
        for (List<Integer> level : levelOrders) {
            System.out.println(Arrays.toString(level.stream().mapToInt(Integer::intValue).toArray()));
        }

        List<List<Integer>> zigzagOrders = bfsSolutions.zigzagLevelOrder(root);
        for (List<Integer> level : zigzagOrders) {
            System.out.println(Arrays.toString(level.stream().mapToInt(Integer::intValue).toArray()));
        }

        List<Double> aveLevels = bfsSolutions.averageOfLevels(root);
        System.out.println(Arrays.toString(aveLevels.stream().mapToDouble(Double::doubleValue).toArray()));

         */
        TreeNode maxLevelSumRoot = new TreeNode(1);
        maxLevelSumRoot.left = new TreeNode(7);
        maxLevelSumRoot.right = new TreeNode(0);
        maxLevelSumRoot.left.left = new TreeNode(7);
        maxLevelSumRoot.left.right = new TreeNode(-8);
        System.out.println(bfsSolutions.maxLevelSum(maxLevelSumRoot));
        TreeNode maxLevelSumRoot1 = new TreeNode(11);
        maxLevelSumRoot1.left = new TreeNode(7);
        maxLevelSumRoot1.right = new TreeNode(2);

        System.out.println(bfsSolutions.maxLevelSum(maxLevelSumRoot1));
        // DFS Solutions Test
        DFSSolutions dfsSolutions = new DFSSolutions();
        List<List<Integer>> dfsLevels = dfsSolutions.levelOrder(root);
        for (List<Integer> level : dfsLevels) {
            System.out.println(Arrays.toString(level.stream().mapToInt(Integer::intValue).toArray()));
        }

        List<List<Integer>> zigzagDfsOrders = dfsSolutions.zigzagLevelOrder(root);
        for (List<Integer> level : zigzagDfsOrders) {
            System.out.println(Arrays.toString(level.stream().mapToInt(Integer::intValue).toArray()));
        }

        List<Double> aveLevelDfss = dfsSolutions.aveLevelOrder(root);
        System.out.println(Arrays.toString(aveLevelDfss.stream().mapToDouble(Double::doubleValue).toArray()));

    }
}

class BFSSolutions {

    // Print Level order Top down or Bottom Down
    List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            result.add(currentLevel);      // Top down
            //result.add(0, currentLevel); // Bottom - Up
        }
        return result;
    }

    // Given the root of a binary tree, return the zigzag level order traversal of its nodes' values
    List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        boolean reverse = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
                if (reverse)
                    currentLevel.add(current.val);
                else
                    currentLevel.add(0, current.val);
            }
            result.add(currentLevel);
            reverse = !reverse;
        }
        return result;
    }


    List<Double> averageOfLevels(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Double> aveList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                sum += (double) current.val;
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            aveList.add(sum / size);
        }
        return aveList;
    }

    //Maximum Level Sum of a Binary Tree
    // Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
    int maxLevelSum(TreeNode root) {
        if (root == null)
            return -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxSum = root.val, level = 1, maxLevel = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            int currentLevelSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                currentLevelSum += current.val;
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }

    // Minimum Depth of Binary Tree
    // Given a binary tree, find its minimum depth.
    //The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

    int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minDepth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left == null && current.right == null)
                    return minDepth;
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            minDepth++;
        }
        return -1;
    }

    int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int count = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            count++;
        }

        return count;
    }

    //

    List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                //if it is the last node of this level add it to the result
                if (i == size - 1)
                    result.add(current.val);

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;

    }

    List<Integer> leftSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                //if it is the first node of this level, add it to the result
                if (i == 0)
                    result.add(current.val);

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;

    }

    //Level Maximum in a Binary Tree
    public static List<Integer> largestValueInLevel(TreeNode root) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()) {
            int levelSize = treeNodeQueue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = treeNodeQueue.remove();
                max = Math.max(max, current.val);
                if (current.left != null) {
                    treeNodeQueue.add(current.left);
                }
                if (current.right != null) {
                    treeNodeQueue.add(current.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    //Level Order Successor
    // Given a binary tree and a node, find the level order successor of the given node in the tree.
    // The level order successor is the node that appears right after the given node in the level order traversal.
    public static int findSuccessor(TreeNode root, int key) {
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode current = treeNodeQueue.remove();
            if (current.left != null) {
                treeNodeQueue.add(current.left);
            }
            if (current.right != null) {
                treeNodeQueue.add(current.right);
            }
            if (current.val == key) {
                break; // if key match to current node value
            }
        }
        if (!treeNodeQueue.isEmpty()) {
            return treeNodeQueue.peek().val;
        }
        return -1;
    }
}

class DFSSolutions {
    List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        addLevelOrderNode(root, 0, result);
        return result;
    }

    void addLevelOrderNode(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null)
            return; // Base case.
        if (level == result.size())
            result.add(new ArrayList<>()); // Top down
        // result.add(0, new ArrayList<>());  // Bottom - Up
        result.get(level).add(root.val); // Top down
        //result.get(result.size() - level - 1).add(root.val);  // Bottom - Up

        addLevelOrderNode(root.left, level + 1, result);
        addLevelOrderNode(root.right, level + 1, result);
    }

    List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        zigzagLevelOrderNode(root, 0, result);
        return result;
    }

    void zigzagLevelOrderNode(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null)
            return; // Base case.
        if (level == result.size())
            result.add(new ArrayList<>()); // Top down
        if (level % 2 == 1)
            result.get(level).add(0, root.val); // reverse order
        else
            result.get(level).add(root.val);

        zigzagLevelOrderNode(root.left, level + 1, result);
        zigzagLevelOrderNode(root.right, level + 1, result);
    }

    List<Double> aveLevelOrder(TreeNode root) {
        if (root == null)
            new ArrayList<>();
        List<Double> aveList = new ArrayList<>();
        List<double[]> storeSumNoCount = new ArrayList<>();
        traverseSumWithLevel(root, 0, storeSumNoCount);
        for (double[] store : storeSumNoCount) {
            aveList.add(store[0] / store[1]);
        }
        return aveList;
    }

    void traverseSumWithLevel(TreeNode root, int level, List<double[]> stores) {
        if (root == null)
            return;
        if (stores.size() <= level)
            stores.add(new double[2]);
        // update sum and num of nodes
        stores.get(level)[0] += root.val;
        stores.get(level)[1]++;

        traverseSumWithLevel(root.left, level + 1, stores);
        traverseSumWithLevel(root.right, level + 1, stores);
    }

    int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == targetSum)
            return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        dfsFindPathSum(root, targetSum, new ArrayList<>(), allPaths);
        return allPaths;
    }

    void dfsFindPathSum(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (root == null) return;
        currentPath.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            dfsFindPathSum(root.left, sum - root.val, currentPath, allPaths);
            dfsFindPathSum(root.right, sum - root.val, currentPath, allPaths);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    int count = 0;

    public int pathSumIII(TreeNode root, int targetSum) {
        if (root == null)
            return 0;


        dfsPathSum(root, targetSum, 0);
        pathSumIII(root.left, targetSum);
        pathSumIII(root.right, targetSum);

        return count;

    }

    void dfsPathSum(TreeNode root, int targetSum, long currentSum) {
        if (root == null)
            return;
        currentSum += root.val;
        if (currentSum == targetSum)
            count++;
        dfsPathSum(root.left, targetSum, currentSum);
        dfsPathSum(root.right, targetSum, currentSum);
    }


    List<List<Integer>> paths = new ArrayList<>();

    public List<List<Integer>> pathSumIIIPrint(TreeNode root, int sum) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>());
        dfsPathSumIIIPrint(root, 0, sum, new ArrayList<>(), map);
        return paths;
    }

    void dfsPathSumIIIPrint(TreeNode root, int currSum, int target, List<Integer> currList, Map<Integer, List<Integer>> map) {
        if (root == null)
            return;

        currSum += root.val;
        currList.add(root.val);
        if (map.containsKey(currSum - target)) {
            List<Integer> prevPath = map.get(currSum - target);
            List<Integer> temp = new ArrayList<>();
            for (int i = prevPath.size(); i < currList.size(); i++)
                temp.add(currList.get(i));
            paths.add(temp);
        }
        map.put(currSum, new ArrayList<>(currList));
        dfsPathSumIIIPrint(root.left, currSum, target, currList, map);
        dfsPathSumIIIPrint(root.right, currSum, target, currList, map);
        currList.remove(currList.size() - 1);
    }

    int diameter = 0;

    int diameterOfTree(TreeNode root) {
        dfsDiameter(root);
        return diameter;
    }

    int dfsDiameter(TreeNode root) {
        if (root == null)
            return 0;
        int left = dfsDiameter(root.left);
        int right = dfsDiameter(root.right);
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }

    // Sum Root to Leaf Numbers
    //Return the total sum of all root-to-leaf numbers.
    int sumNumbers(TreeNode root) {
        return dfsSumNumber(root, 0);
    }

    int dfsSumNumber(TreeNode root, int tempSum) {
        if (root == null)
            return 0;
        tempSum = tempSum * 10 + root.val;
        if (root.left == null && root.right == null)
            return tempSum;
        return dfsSumNumber(root.left, tempSum) + dfsSumNumber(root.right, tempSum);
    }

    List<String> list = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePaths(root, "");
        return list;
    }

    void binaryTreePaths(TreeNode root, String str) {
        if (root == null)
            return;
        str += root.val + "->";
        binaryTreePaths(root.left, str);
        binaryTreePaths(root.right, str);
        if (root.left == null && root.right == null)
            list.add(str.substring(0, str.length() - 2));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void print() {
        print(" ", this, false);
    }

    private void print(String prefix, TreeNode n, boolean isLeft) {
        if (n != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + n.val);
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }
}