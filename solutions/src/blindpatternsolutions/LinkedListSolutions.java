package blindpatternsolutions;

import java.util.PriorityQueue;

public class LinkedListSolutions {

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

    public static void main(String[] args) {

    }

    void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head1 = head;
        ListNode head2 = slow.next;
        slow.next = null;

        head2 = reverse(head2);

        while (head2 != null) {
            ListNode temp1 = head1.next;
            ListNode temp2 = head2.next;
            head1.next = head2;
            head2.next = temp1;
            head1 = temp1;
            head2 = temp2;
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

    //https://leetcode.com/problems/palindrome-linked-list
    // Is Linked List Palindrome
    boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head, prev, temp;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        prev = slow;
        slow = slow.next;
        prev.next = null;

        // reverse 2nd of list...
        while (slow != null) {
            temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        fast = head;
        slow = prev;

        while (slow != null) {
            if (fast.val != slow.val)
                return false;
            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    // https://leetcode.com/problems/circular-array-loop
    //Circular Array Loop
    boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            float dir = Math.signum(nums[i]);
            int slow = i;
            int fast = i;
            do {

                slow = getNextIndex(nums, dir, slow);
                fast = getNextIndex(nums, dir, fast);

                if (fast != -1) {
                    fast = getNextIndex(nums, dir, fast);
                }
                if (fast == -1 || nums[slow] == 0 || nums[fast] == 0)
                    break;
            } while (slow != fast);

            if (slow != -1 && slow == fast)
                return true;
            nums[i] = 0;
        }

        return false;
    }

    int getNextIndex(int[] nums, float dir, int i) {
        float currentDir = Math.signum(nums[i]);
        if (currentDir * dir < 0)
            return -1;

        int n = nums.length;
        int nxtIdx = (i + nums[i]) % n;
        if (nxtIdx < 0)
            nxtIdx += n;

        return nxtIdx == i ? -1 : nxtIdx;
    }

}


