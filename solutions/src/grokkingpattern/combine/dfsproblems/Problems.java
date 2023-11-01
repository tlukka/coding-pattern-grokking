package grokkingpattern.combine.dfsproblems;

import grokking.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problems {

  public static void main(String[] agrs) {
    /*TreeNode root = new TreeNode("12");
    root.left = new TreeNode("7");
    root.right = new TreeNode("1");
    root.left.left = new TreeNode("9");
    root.right.left = new TreeNode("10");
    root.right.right = new TreeNode("5");
    root.print();
    System.out.println(hasPathSum(root,23));
    System.out.println(hasPathSum(root,16));
    System.out.println(hasPathSum(root,18)); */

    /*TreeNode root = new TreeNode("5");
    root.left = new TreeNode("4");
    root.right = new TreeNode("8");
    root.left.left = new TreeNode("11");
    root.left.left.left = new TreeNode("7");
    root.left.left.right = new TreeNode("2");
    root.right.left = new TreeNode("13");
    root.right.right = new TreeNode("4");
    root.right.right.left = new TreeNode("5");
    root.right.right.right = new TreeNode("1");
    root.print();
    /*List<List<String>> allPaths = findPaths(root, 22);
    for (List<String> path : allPaths) {
      System.out.print("[");
      path.stream().forEach((a) -> System.out.print(a + ","));
      System.out.println("]");
    }
    binaryTreePaths(root).stream().forEach((a) -> System.out.print(a + "\n")); */
    //System.out.println(maxPathSum(root));
    //System.out.println(sumNumbers(root));
    //System.out.println(isSequencceExitsInTree(root,new int[] {5,4,11,7}));

    TreeNode root1 = new TreeNode("5");
    root1.left = new TreeNode("4");
    root1.right = new TreeNode("8");
    root1.left.left = new TreeNode("3");
    root1.print();
    //System.out.println(sumNumbers(root1));
    System.out.println(pathSumIII(root1, 12));

  }

  // Tree Diameter

  // Check if there is a root to leaf path with given sequence
  static boolean isSequencceExitsInTree(TreeNode root, int[] sequence) {
    if(root== null) return sequence.length == 0;
    return isSequencceExitsByRecursion(root, sequence, 0);
  }

  static boolean isSequencceExitsByRecursion(TreeNode root, int[] sequence, int index) {
    if(root == null) return true;
    int len= sequence.length;
    // index more than length or not match at given index
    if(index>= len || Integer.parseInt(root.value) != sequence[index]) return false;
    // root is a leaf.
    if(root.left== null && root.right == null && len-1==index) return true;

    // check left or right
    return isSequencceExitsByRecursion(root.left, sequence, index+1) ||
        isSequencceExitsByRecursion(root,sequence, index+1);
  }

  //Sum Root to Leaf Numbers
  static int sumNumbers(TreeNode root) {
    return sumPathNumbers(root,0);
  }

  static int sumPathNumbers(TreeNode root, int pathSum) {
    if(root == null) return 0;
    pathSum = pathSum*10 + Integer.parseInt(root.value);
    if(root.left== null && root.right==null)
      return pathSum;
    return sumPathNumbers(root.left, pathSum) + sumPathNumbers(root.right, pathSum);
  }
  static int maxPathSum(TreeNode root) {
    int result = Integer.MIN_VALUE;
    findMaxSumPath(root,result);
    return result;
  }

  static int findMaxSumPath(TreeNode root, int max) {
    if(root == null) return 0;
    int leftMax = Math.max(findMaxSumPath(root.left, max),0);
    int rightMax = Math.max(findMaxSumPath(root.right, max), 0);
    max = Math.max(max, leftMax+rightMax+Integer.parseInt(root.value));
    return Math.max(leftMax,rightMax) + Integer.parseInt(root.value);
  }

  //all root-to-leaf paths
  static List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    treePath(root, "", result);
    return result;
  }

  static void treePath(TreeNode root, String str, List<String> treePaths) {
    if (root == null) {
      return;
    }
    if (root != null && root.left == null && root.right == null) {
      str = str.length() == 0 ? "" + root.value : str + "->" + root.value;
      treePaths.add(str);
      return;
    }
    String leftStr = str.length() == 0 ? "" + root.value : str + "->" + root.value;
    treePath(root.left, leftStr, treePaths);
    String rightStr = str.length() == 0 ? "" + root.value : str + "->" + root.value;
    treePath(root.right, rightStr, treePaths);
  }

  // Path Sum III
  static int pathSumIII(TreeNode root, int sum) {
   if(root== null) return 0;
   return pathSumIIIRec(root,sum, new ArrayList<>());
  }

  static int pathSumIIIRec(TreeNode root, int targetSum, List<String> currentPath) {
    if(root == null) return 0;
    currentPath.add(root.value);
    int pathCount =0, pathSum =0;
    //find the sums of all aub-paths in the current path list
    for(int i=currentPath.size(); i>=0; i--) {
      pathSum += Integer.parseInt(root.value);
      if(targetSum == pathSum) {
        pathCount++;
      }
    }

    pathCount += pathSumIIIRec(root.left,targetSum, currentPath);
    pathCount += pathSumIIIRec(root.right,targetSum,currentPath);
    currentPath.remove(currentPath.size()-1);
    return pathCount;
  }

  //All Paths for a Sum
  static List<List<String>> findPaths(TreeNode root, int sum) {
    List<List<String>> allPaths = new ArrayList<>();
    if (root == null) {
      return allPaths;
    }
    List<String> currentPath = new ArrayList<>();
    findPath(root, sum, currentPath, allPaths);
    return allPaths;
  }

  static void findPath(TreeNode root, int sum, List<String> currentPath, List<List<String>> allPaths) {
    if (root == null) {
      return;
    }
    currentPath.add(root.value);
    if (Integer.parseInt(root.value) == sum && root.left == null && root.right == null) {
      allPaths.add(new ArrayList<>(currentPath));
    }
    findPath(root.left, sum - Integer.parseInt(root.value), currentPath, allPaths);
    findPath(root.right, sum - Integer.parseInt(root.value), currentPath, allPaths);
    currentPath.remove(currentPath.size() - 1);
  }

  //Binary Tree Path Sum
  static boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    //start DFS with the root of the tree
    //if the current node is a leaf and it's value and equal to the sum then we've found a path
    if (Integer.parseInt(root.value) == sum && root.left == null && root.right == null) {
      return true;
    }
    return hasPathSum(root.left, sum - Integer.parseInt(root.value)) || hasPathSum(root.right,
        sum - Integer.parseInt(root.value));
  }

}
