package blindsolutions.lists;

import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {

    }

    void reOrderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;
        ListNode head1 = head;

        // reverse head2
        head2 = reverse(head2);
        ListNode curr = new ListNode(-1);
        while (head1 != null) {
            ListNode tmp = head1.next;
            curr.next = head1;
            head1.next = head2;
            curr = head2;
            head1 = tmp;
            if (head2 != null) {
                head2 = head2.next;
            }
        }

    }

    ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        ListNode first = dummy, second = dummy;
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // delete node ..
        second.next = second.next.next;
        return dummy.next;
    }

    ListNode removeNthNode(ListNode head, int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        dfsNode(newHead, n + 1);
        return newHead.next;

    }

    int dfsNode(ListNode node, int k) {
        if (node == null)
            return 0;
        int index = 1 + dfsNode(node.next, k);
        if (index == k) {
            node.next = node.next.next; // delete node at kth index;
        }
        return index;
    }

    ListNode mergeKListsWithSpace(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                priorityQueue.add(node);
                node = node.next;
            }
        }

        ListNode root = new ListNode();
        ListNode temp = root;
        while (!priorityQueue.isEmpty()) {
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = priorityQueue.poll().val;
        }
        return root.next;
    }

    ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        int length = lists.length;
        int interval = 1;
        while (interval < length) {
            for (int i = 0; i + interval < length; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1 != null)
            temp.next = list1;
        if (list2 != null)
            temp.next = list2;
        return head.next;

    }

    boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow)
                return true;
        }

        return false;
    }

    ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        return prev;
    }

    // Recursive apporach
    ListNode reverseList(ListNode head) {
        // special case
        if (head == null || head.next == null)
            return head;
        //create new node
        ListNode result = reverseList(head.next);
        // Set head node as head.next.next...
        head.next.next = head;
        //set head's next to be null...
        head.next = null;
        return result;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
