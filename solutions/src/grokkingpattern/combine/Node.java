package grokkingpattern.combine;

public class Node {
  public int value;
  public Node next;
  public Node(int value) {
    this.value = value;
  }

  public String printList() {
      String result = "";
      Node tempNode = this;
      while (tempNode != null) {
        result += tempNode.value + ",";
        tempNode = tempNode.next;
      }
      return result;
  }
}
