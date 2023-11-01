package grokkingpattern.combine.inplaceproblems;

import grokking.Node;

public class Problems {

  public static void main(String[] args) {

    Node head = new Node(2);
    head.next = new Node(4);
    head.next.next = new Node(6);
    head.next.next.next = new Node(8);
    head.next.next.next.next = new Node(10);
    head.next.next.next.next.next = new Node(12);
    head.next.next.next.next.next.next = new Node(14);
    head.next.next.next.next.next.next.next = new Node(16);
    head.next.next.next.next.next.next.next.next = new Node(18);
    head.next.next.next.next.next.next.next.next.next = new Node(20);
    //System.out.println("original list " + head.printList());
    //System.out.println("reverse list " + reverse(head).printList());
    //System.out.println("reverse sublist " + reverseEveryKElement(head, 3).printList());
    //System.out.println("reverse sublist " + reverseAlernatKElement(head, 3).printList());
    System.out.println("reverse sublist " + rotateLinkedList(head, 3).printList());

  }

  //rotate the LinkedList to the right by ‘k’ nodes
  static Node rotateLinkedList(Node head, int k) {

    if(k<=0 || head == null) return head;
    Node lastNode = head;
    int length= 1;
    while (lastNode.next != null) {
      lastNode = lastNode.next;
      length++;
    }
    //connect the last node with the head to make it a circular list
    lastNode.next = head;

    int skipLength= length - k;
    Node lastNodeOfRotatedList = head;
    for(int i=0; i<skipLength-1; i++) {
        lastNodeOfRotatedList = lastNodeOfRotatedList.next;
    }

    //lastNodeOfRotatedList.next is pointing to the sub-list of k ending nodes
    head = lastNodeOfRotatedList.next;
    lastNodeOfRotatedList.next = null;
    return head;
  }

  //reverse every alternating ‘k’
  static Node reverseAlernatKElement(Node head, int k) {
    if(k<=1 || head== null) return head;
    Node previous = null;
   Node current = head;
   while (current!=null) {
     Node lastNodeFirstPart = previous;
     Node lastNodeSubList = current;
     Node next = null;
     int i=0;
     while (i<k && current != null) {
       next = current.next;
       current.next = previous;
       previous = current;
       current = next;
       i++;
     }
     if(lastNodeFirstPart != null) {
       lastNodeFirstPart.next = previous;
     } else {
       head = previous;
     }
     lastNodeSubList.next = current;
     //skip k nodes
     i = 0;
     while (current != null && i < k){
       previous = current;
       current = current.next;
       i++;
     }
   }
   return head;
  }
  // reverse every ‘k’ sized sub-list
  static Node reverseEveryKElement(Node head, int k) {
    // edge case
    if(k<=1 || head== null) return head;
    Node previous = null;
    Node current = head;

    while (true) {
      Node lastNodeOfFirstPart = previous;
      //after reversing the LL current will become the last node of the sublist
      Node lastNodeOfSubList = current;
      Node next = null;
      int i=0;
      while (i<k && current != null) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
        i++;
      }
      //connect with the previous part
      if(lastNodeOfFirstPart !=null) {
        lastNodeOfFirstPart.next = previous;
      } else {
        head = previous;
      }
      //connect with the next part
      lastNodeOfSubList.next = current;

      if(current == null) {
        break;
      }
      previous = lastNodeOfSubList;
    }
    return head;
  }


  //Reverse the first k elements of a given LinkedList.
  //Given a LinkedList with n nodes, reverse it based on its size in the following way:
      //If n is even, reverse the list in a group of n/2 nodes.
      //If n is odd, keep the middle node as it is, reverse the first n/2 nodes and reverse the last n/2 nodes.
      /*When n is even we can perform the following steps:

    Reverse first n/2 nodes: head = reverse(head, 1, n/2)
    Reverse last n/2 nodes: head = reverse(head, n/2 + 1, n)
    When n is odd, our algorithm will look like:

    head = reverse(head, 1, n/2)
    head = reverse(head, n/2 + 2, n) Please note the function call in the second step.
     We’re skipping two elements as we will be skipping the middle element.
    */
  static Node reverseSubList(Node head, int left, int right) {

    int i=0;
    Node current = head;
    Node previous = null;
    // moved until left index.
    while (i<left-1 && current!=null) {
      previous= current;
      current = current.next;
      i++;
    }

    Node lastNodeOfFirstPart = previous;
    Node lastNodeOfLastPart = current;
    i=0;
    Node next = null;
    // reverse between left & right
    while (i<right-left+1 && current != null) {
      next = current.next;
      current.next = previous;
      previous =current;
      current = next;
      i++;
    }
    //connect with the first part
    if(lastNodeOfFirstPart != null) {
      //previous is now the first node of the sub list
      //this means left === 1 i.e., we are changing the first node(head) of the LL
      lastNodeOfFirstPart.next = previous;
    } else {
      head =previous;
    }
    lastNodeOfLastPart.next = current;
    return head;
  }

  static Node reverse(Node head) {
    Node current = head;
    Node previous = null;
    while (current!=null) {
      // temp store
      Node next = current.next;
      //reverse the current node
      current.next = previous;
      // before we move to the next node and point previous to the current node
      previous = current;
      current = next;
    }
    return previous;
  }
}
