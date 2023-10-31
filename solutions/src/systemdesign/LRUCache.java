package systemdesign;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node{
        public int key;
        public int val;
        public Node next;
        public Node prev;
        public Node(int key, int val) {
            this.key=key;
            this.val = val;
            prev=next=null;
        }
    }

    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int size;

    public LRUCache(int capacity) {
        size=capacity;
        map = new HashMap<>();
        head= new Node(-1,-1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev=head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node p = map.get(key);
        deleteNode(p);
        addNode(p);
        map.put(key, head.next);
        return head.next.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node c= map.get(key);
            deleteNode(c);
            c.val=value;
            addNode(c);
            map.put(key, head.next);
        } else {
            if(map.size()==size) {
                Node prev=tail.prev;
                deleteNode(prev);
                Node l = new Node(key, value);
                addNode(l);
                map.remove(prev.key);
                map.put(key, head.next);
            } else {
                Node l = new Node(key, value);
                addNode(l);
                map.put(key, head.next);
            }
        }
    }

    void  deleteNode(Node p) {
        Node prev = p.prev;
        Node nxt = p.next;
        prev.next =nxt;
        nxt.prev=prev;
    }

    void addNode(Node newNode) {
        Node temp = head.next;
        head.next= newNode;
        newNode.prev=head;
        newNode.next = temp;
        temp.prev=newNode;
    }
}