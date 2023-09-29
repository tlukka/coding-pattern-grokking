package blindpatternsolutions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> neighbors) {
            this.neighbors = neighbors;
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }

    Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Node newNode = new Node(node.val);
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, newNode);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            List<Node> newNeighbors = map.get(current).neighbors;
            for (Node n : current.neighbors) {
                if (!map.containsKey(n)) {
                    Node tempNode = new Node(n.val);
                    map.put(n, tempNode);
                    queue.add(n);
                }
                newNeighbors.add(map.get(n));
            }
        }
        return newNode;
    }


    Node cloneGraphWithDfs(Node node) {
        //step 1
        return cloneDFS(node, new HashMap<>());
    }

    public Node cloneDFS(Node cur, Map<Node, Node> visited) {
        //step 2
        if (cur == null) return null;
        //step 3
        if (visited.containsKey(cur)) return visited.get(cur);
        //step 4
        Node newNode = new Node(cur.val);
        visited.put(cur, newNode);
        //step 5
        for (Node n : cur.neighbors) {
            newNode.neighbors.add(cloneDFS(n, visited));
        }
        //step 6
        return newNode;
    }
}
