package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgoritham {
    public static void main(String[] args) {
        List<KVertex> vertexList = new ArrayList<>();
        vertexList.add(new KVertex("A"));
        vertexList.add(new KVertex("B"));
        vertexList.add(new KVertex("C"));
        vertexList.add(new KVertex("D"));
        vertexList.add(new KVertex("E"));
        vertexList.add(new KVertex("F"));
        vertexList.add(new KVertex("G"));
        vertexList.add(new KVertex("H"));

        List<KEdge> edgeList = new ArrayList<>();
        edgeList.add(new KEdge(vertexList.get(0), vertexList.get(1), 3));
        edgeList.add(new KEdge(vertexList.get(0), vertexList.get(2), 2));
        edgeList.add(new KEdge(vertexList.get(0), vertexList.get(3), 5));
        edgeList.add(new KEdge(vertexList.get(1), vertexList.get(5), 13));
        edgeList.add(new KEdge(vertexList.get(1), vertexList.get(3), 2));
        edgeList.add(new KEdge(vertexList.get(2), vertexList.get(4), 5));
        edgeList.add(new KEdge(vertexList.get(2), vertexList.get(3), 2));
        edgeList.add(new KEdge(vertexList.get(3), vertexList.get(4), 4));
        edgeList.add(new KEdge(vertexList.get(3), vertexList.get(5), 6));
        edgeList.add(new KEdge(vertexList.get(3), vertexList.get(6), 3));
        edgeList.add(new KEdge(vertexList.get(4), vertexList.get(6), 6));
        edgeList.add(new KEdge(vertexList.get(5), vertexList.get(6), 2));
        edgeList.add(new KEdge(vertexList.get(5), vertexList.get(7), 3));
        edgeList.add(new KEdge(vertexList.get(6), vertexList.get(7), 6));

        KruskalAlgoritham algoritham = new KruskalAlgoritham();
        algoritham.runAlgoritham(vertexList, edgeList);
    }


    public void runAlgoritham(List<KVertex> vertexList, List<KEdge> edgeList) {
        DisjointSet disjointSet = new DisjointSet(vertexList);
        Collections.sort(edgeList);
        List<KEdge> mst = new ArrayList<>();
        for (KEdge edge : edgeList) {
            KVertex u = edge.getStart();
            KVertex v = edge.getDestination();
            if (disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())) {
                mst.add(edge);
            }
            disjointSet.union(u.getNode(), v.getNode());
        }
        // show edge
        mst.forEach(e -> System.out.println(e));

    }
}

class DisjointSet {
    public DisjointSet(List<KVertex> vertexList) {
        vertexList.forEach(v -> {
            Node node = new Node(0, null);
            v.setNode(node);
        });
    }

    public Node find(Node node) {
        Node actual = node;

        // find representive
        while (actual.getParent() != null) {
            actual = actual.getParent();
        }

        Node root = actual;
        // Path compression to make sure time to look for is O(1)
        while (root != actual) {
            Node temp = actual.getParent();
            actual.setParent(root);
            actual = temp;
        }
        return root;
    }

    public void union(Node node1, Node node2) {
        Node root1 = find(node1);
        Node root2 = find(node2);
        if (root1 == root2)
            return;

        if (root1.getRank() < root2.getRank()) {
            root1.setParent(root2);
        } else if (root2.getRank() < root1.getRank()) {
            root2.setParent(root1);
        } else {
            root2.setParent(root1);
            root1.setRank(root1.getRank() + 1);
        }
    }
}

class KVertex {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return name;
    }

    String name;
    // node in disjoint set
    Node node;

    public KVertex(String name) {
        this.name = name;
    }

}

class Node {
    private int rank;


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    private Node parent;

    public Node(int rank, Node parent) {
        this.rank = rank;
        this.parent = parent;
    }
}

class KEdge implements Comparable<KEdge> {
    public KEdge(KVertex start, KVertex destination, double weight) {
        this.weight = weight;
        this.start = start;
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    private double weight;

    public KVertex getStart() {
        return start;
    }

    public void setStart(KVertex start) {
        this.start = start;
    }

    public KVertex getDestination() {
        return destination;
    }

    public void setDestination(KVertex destination) {
        this.destination = destination;
    }

    private KVertex start;
    private KVertex destination;

    @Override
    public int compareTo(KEdge o) {
        return Double.compare(this.weight, o.getWeight());
    }

    @Override
    public String toString() {
        return start + "-" + destination;
    }
}

