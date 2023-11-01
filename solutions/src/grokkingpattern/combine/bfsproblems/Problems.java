package grokkingpattern.combine.bfsproblems;

import grokking.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problems {

  public static void main(String[] args) {
    TreeNode root = new TreeNode("12");
    root.left = new TreeNode("7");
    root.right = new TreeNode("1");
    root.left.left = new TreeNode("9");
    root.right.left = new TreeNode("10");
    root.right.right = new TreeNode("5");
    root.print();
  /*List<List<String>> levelOrderLst = levelOrderTraversal(root);
  for(List<String> level: levelOrderLst) {
    System.out.print("[");
    level.stream().forEach((a) -> System.out.print(a + ","));
    System.out.print("] \n");
  }

  List<List<String>> reverseLevelOrderLst = reverseLevelOrderTraversal(root);
  for(List<String> level: reverseLevelOrderLst) {
    System.out.print("[");
    level.stream().forEach((a) -> System.out.print(a + ","));
    System.out.print("] \n");

    List<List<String>> zigZagOrder = zigZagLevelOrder(root);
    for (List<String> level : zigZagOrder) {
      System.out.print("[");
      level.stream().forEach((a) -> System.out.print(a + ","));
      System.out.print("] \n");
    }

    List<Integer> aveByLevel = averageByLevel(root);
    aveByLevel.stream().forEach((a) -> System.out.print(a + ","));
    System.out.print("\n");
    List<Integer> maxValue = largestValueInLevel(root);
    maxValue.stream().forEach((a) -> System.out.print(a + ","));

    //System.out.println(minimumDepthTree(root));
    //System.out.println(maximumDepthTree(root));
    //System.out.println(findSuccessor(root,9));
    System.out.println(findSuccessor(root,20));
    TreeNode connectLevelNode = connectLevelOrderSiblings(root);
    connectLevelNode.printTreeNextNode(connectLevelNode);

    TreeNode connectLevelNode = connectSibling(root);
    connectLevelNode.printTree(connectLevelNode);
   */
    rightViewNode(root).stream().forEach((a) -> System.out.print(a + ","));
  }

  //Binary Tree Right Side View
  public static List<String> rightViewNode(TreeNode root) {
    if(root == null) return null;
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    List<String> result = new ArrayList<>();
    treeNodeQueue.add(root);
    while (!treeNodeQueue.isEmpty()) {
      int size = treeNodeQueue.size();
      for(int i=0; i<size; i++) {
        TreeNode current = treeNodeQueue.remove();
        if(i==size-1) {
          result.add(current.value);
        }
        if(current.left!=null)
          treeNodeQueue.add(current.left);
        if(current.right!=null)
          treeNodeQueue.add(current.right);
      }
    }
    return result;
  }

  // Connect all Siblings
  public static TreeNode connectSibling(TreeNode root) {
    if(root == null) return null;
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    while (!treeNodeQueue.isEmpty()) {
      TreeNode previous = null;
      int size = treeNodeQueue.size();
      for(int i=0; i<size; i++) {
        TreeNode current = treeNodeQueue.remove();
        if(previous!= null) {
          previous.next= current;
        }
        previous = current;
        if(current.left!= null) {
          treeNodeQueue.add(current.left);
        }
        if(current.right!= null){
          treeNodeQueue.add(current.right);
        }
      }
    }
    return root;
  }
  // Connect Level Order Siblings
  public static TreeNode connectLevelOrderSiblings(TreeNode root) {
    if (root == null) {
      return null;
    }
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    while (!treeNodeQueue.isEmpty()) {
      TreeNode previous = null;
      int size = treeNodeQueue.size();
      for (int i = 0; i < size; i++) {
        TreeNode current = treeNodeQueue.remove();
        if(previous!=null) {
          current.next = previous;
        }
        previous = current;
        if (current.right != null) {
          treeNodeQueue.add(current.right);
        }
        if (current.left != null) {
          treeNodeQueue.add(current.left);
        }
      }
    }
    return root;
  }

  //Level Order Successor
  public static String findSuccessor(TreeNode root, int key) {
    if (root == null) {
      return null;
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
      if (Integer.parseInt(current.value) == key) {
        break; // if key match to current node value
      }
    }
    if (!treeNodeQueue.isEmpty()) {
      return treeNodeQueue.peek().value;
    }
    return null;
  }

  //Maximum Depth of a Binary Tree
  public static int maximumDepthTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    int maxDepth = 0;
    while (!treeNodeQueue.isEmpty()) {
      maxDepth++;
      for (int i = 0; i < treeNodeQueue.size(); i++) {
        TreeNode current = treeNodeQueue.remove();
        if (current.left != null) {
          treeNodeQueue.add(current.left);
        }
        if (current.right != null) {
          treeNodeQueue.add(current.right);
        }
      }
    }
    return maxDepth;
  }

  //Minimum Depth of a Binary Tree
  public static int minimumDepthTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    int minDepth = 0;
    while (!treeNodeQueue.isEmpty()) {
      minDepth++;
      for (int i = 0; i < treeNodeQueue.size(); i++) {
        TreeNode current = treeNodeQueue.remove();
        if (current.left == null && current.right == null) {
          return minDepth;
        }
        if (current.left != null) {
          treeNodeQueue.add(current.left);
        }
        if (current.right != null) {
          treeNodeQueue.add(current.right);
        }
      }
    }
    return 0;
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
        max = Math.max(max, Integer.parseInt(current.value));
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

  //Level Averages in a Binary Tree
  public static List<Integer> averageByLevel(TreeNode root) {
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    treeNodeQueue.add(root);
    while (!treeNodeQueue.isEmpty()) {
      int sum = 0;
      int size = treeNodeQueue.size();
      for (int i = 0; i < size; i++) {
        TreeNode current = treeNodeQueue.remove();
        sum += Integer.parseInt(current.value);
        if (current.left != null) {
          treeNodeQueue.add(current.left);
        }
        if (current.right != null) {
          treeNodeQueue.add(current.right);
        }
      }
      result.add(sum / size);
    }
    return result;
  }

  //Binary Tree Zigzag Level Order Traversal
  public static List<List<String>> zigZagLevelOrder(TreeNode root) {
    List<List<String>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    boolean isLeftRight = false;
    while (!treeNodeQueue.isEmpty()) {
      List<String> levelLst = new ArrayList<>();
      for (int i = treeNodeQueue.size(); i > 0; i--) {
        TreeNode current = treeNodeQueue.remove();
        if (current.left != null) {
          treeNodeQueue.add(current.left);
        }
        if (current.right != null) {
          treeNodeQueue.add(current.right);
        }
        if (isLeftRight) {
          levelLst.add(0, current.value);
        } else {
          levelLst.add(current.value);
        }
      }
      isLeftRight = !isLeftRight;
      result.add(levelLst);
    }
    return result;
  }

  //Reverse Level Order Traversal
  public static List<List<String>> reverseLevelOrderTraversal(TreeNode root) {
    List<List<String>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    while (!treeNodeQueue.isEmpty()) {
      List<String> levelLst = new ArrayList<>();
      int size = treeNodeQueue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = treeNodeQueue.remove(); // remove visited node
        levelLst.add(node.value);
        if (node.left != null) {
          treeNodeQueue.add(node.left); // adding left
        }
        if (node.right != null) {
          treeNodeQueue.add(node.right); // adding right
        }
      }
      result.add(0, levelLst);
    }
    return result;
  }

  // Binary Tree Level Order Traversal
  public static List<List<String>> levelOrderTraversal(TreeNode root) {
    List<List<String>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);
    while (!treeNodeQueue.isEmpty()) {
      List<String> levelLst = new ArrayList<>();
      int size = treeNodeQueue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = treeNodeQueue.remove(); // remove visited node
        levelLst.add(node.value);
        if (node.left != null) {
          treeNodeQueue.add(node.left); // adding left
        }
        if (node.right != null) {
          treeNodeQueue.add(node.right); // adding right
        }
      }
      result.add(levelLst);
    }
    return result;
  }
}
