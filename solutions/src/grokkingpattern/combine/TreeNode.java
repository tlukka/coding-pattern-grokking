package grokkingpattern.combine;

public class TreeNode {
  public String value;
  public TreeNode left;
  public TreeNode right;
  public TreeNode next;
  public TreeNode(String  value) {
    this.value = value;
  }

  public TreeNode(String value, TreeNode left, TreeNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public TreeNode(String value, TreeNode left, TreeNode right, TreeNode next) {
    this.value = value;
    this.left = left;
    this.right = right;
    this.next = next;
  }

  public void print() {
    print(" ", this, false);
  }
  private void print(String prefix, TreeNode n, boolean isLeft) {
    if (n != null) {
      System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.value);
      print(prefix + (isLeft ? "|   " : "    "), n.left, true);
      print(prefix + (isLeft ? "|   " : "    "), n.right, false);
    }
  }

  public void printTree(TreeNode root) {
    TreeNode node = root;
    String result = " ";
    while (node != null) {
      result = node.value + ",";
      node = node.next;
    }
    System.out.println(result);
  }
  public void printTreeNextNode(TreeNode node) {
    System.out.println("Level order traversal using next pointer");
    TreeNode nextRootNode= node;
    while (nextRootNode!=null) {
      TreeNode current = nextRootNode;
      nextRootNode = null;
      while (current!= null) {
        System.out.print(current.value + ",");
        if(nextRootNode== null) {
          if(current.left!=null)
            nextRootNode = current.left;
          else if(current.right!=null)
            nextRootNode = current.right;
        }
      }
      nextRootNode = current.next;
    }
  }
}
