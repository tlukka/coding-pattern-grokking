package grokkingpattern.combine.fastslow;

import grokking.Node;

public class CycleInList {

  public static void main(String[] args) {



    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(5);
    head.next.next.next.next.next = new Node(6);
    System.out.println("first problem");
    printNode(head);
    System.out.println(hasCycle(head));
    System.out.println(findLengthOfCycle(head));
    System.out.println(startCycleNode(head).value);
    System.out.println(middleNode(head).value);
    head.next.next.next.next.next.next = head.next.next;
    System.out.println("2nd problem");
    System.out.println(hasCycle(head));
    System.out.println(findLengthOfCycle(head));
    System.out.println(startCycleNode(head).value);
    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("3rd problem");
    System.out.println(hasCycle(head));
    System.out.println(findLengthOfCycle(head));
    System.out.println(startCycleNode(head).value);
    System.out.println(isHappy(23));
    System.out.println(isHappy(81));
    System.out.println(isHappy(1));
  }

  static Node middleNode(Node node) {
    Node slow = node;
    Node fast = node;
    while (fast != null && fast.next!=null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  static boolean isHappy(int n) {
    int slow = n, fast = n;
    while (true) {
      slow = findSquareSum(slow);
      fast = findSquareSum(findSquareSum(fast));
      if (slow == fast) {
        break;
      }
    }
    return slow==1;
  }

  static int findSquareSum(int num) {
    int sqSum = 0;
    while (num > 0) {
      int digit = num % 10;
      sqSum += digit * digit;
      num = num / 10;
    }
    return sqSum;
  }

  static void printNode(Node head) {
    Node node = head;
    System.out.print("{");
    while (node!=null) {
      System.out.print(node.value + "->");
      node = node.next;
    }
    System.out.println("}");
  }

  static  boolean hasCycle(Node head) {
    Node slow = head, fast= head;
    while (fast!=null && fast.next!=null) {
      fast = fast.next.next;
      slow = slow.next;
      if(slow == fast) return true;
    }
    return false;
  }

  static int findLengthOfCycle(Node head) {
    Node slow= head, fast = head;
    while (fast!= null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if(slow == fast) {
        return lenCycle(slow);
      }
    }
    return 0;
  }

  static Node startCycleNode(Node head) {
    Node slow= head, fast = head;
    int lengthCycle =0;
    while (fast!= null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if(slow == fast) {
        lengthCycle = lenCycle(slow);
        break;
      }
    }
    return startCycle(head, lengthCycle);
  }

  static Node startCycle(Node head, int len) {
    Node p1 = head;
    Node p2 = head;
    while (len>0) {
      p2 = p2.next;
      len--;
    }
     // move both pointers and when they meet got start point
    while (p2 != p1) {
      p2 = p2.next;
      p1 = p1.next;
    }
    return p1;
  }

  static int lenCycle(Node node) {
    Node current = node;
    int length =0;
    while (current != null) {
      current = current.next;
      length ++;
      if(current == node) {
        break;
      }
    }
    return length;
  }
}
