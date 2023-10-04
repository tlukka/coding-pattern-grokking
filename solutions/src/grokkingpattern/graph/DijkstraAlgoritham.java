package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgoritham {
    public static void main(String[] args) {
        DVertex v0 = new DVertex("A");
        DVertex v1 = new DVertex("B");
        DVertex v2 = new DVertex("C");
        DVertex v3 = new DVertex("D");
        DVertex v4 = new DVertex("E");
        DVertex v5 = new DVertex("F");
        DVertex v6 = new DVertex("G");
        DVertex v7 = new DVertex("H");

        v0.addNegihbor(new DEdge(5, v0, v1));
        v0.addNegihbor(new DEdge(9, v0, v4));
        v0.addNegihbor(new DEdge(8, v0, v7));

        v1.addNegihbor(new DEdge(12, v1, v2));
        v1.addNegihbor(new DEdge(15, v1, v3));
        v1.addNegihbor(new DEdge(4, v1, v7));

        v2.addNegihbor(new DEdge(3, v2, v3));
        v2.addNegihbor(new DEdge(11, v2, v6));

        v3.addNegihbor(new DEdge(9, v3, v6));

        v4.addNegihbor(new DEdge(4, v4, v5));
        v4.addNegihbor(new DEdge(20, v4, v6));
        v4.addNegihbor(new DEdge(5, v4, v7));

        v5.addNegihbor(new DEdge(1, v5, v2));
        v5.addNegihbor(new DEdge(13, v5, v7));

        v7.addNegihbor(new DEdge(7, v7, v2));
        v7.addNegihbor(new DEdge(6, v7, v5));

        DijkstraAlgoritham algo = new DijkstraAlgoritham();
        algo.computePath(v0);

        System.out.println(algo.shortestPath(v6));

    }

    public List<DVertex> shortestPath(DVertex target) {
        List<DVertex> resultPath = new ArrayList<>();
        for (DVertex vertex = target; vertex != null; vertex = vertex.getPredecessor()) {
            resultPath.add(0, vertex);
        }
        return resultPath;
    }

    public void computePath(DVertex source) {
        source.setDistance(0);
        PriorityQueue<DVertex> queue = new PriorityQueue<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            DVertex current = queue.poll();
            for (DEdge edge : current.getNeighbors()) {
                DVertex u = edge.getStart();
                DVertex v = edge.getDestination();
                double dist = current.getDistance() + edge.getWeight();
                if (dist < v.getDistance()) {
                    queue.remove(v);
                    v.setDistance(dist);
                    v.setPredecessor(current);
                    queue.add(v);
                }
            }
        }
    }
}

class DVertex implements Comparable<DVertex> {
    @Override
    public String toString() {
        return name + "-" + distance;
    }

    private String name;
    // previous node for shortest path
    private DVertex predecessor;

    public DVertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(DVertex predecessor) {
        this.predecessor = predecessor;
    }

    public List<DEdge> getNeighbors() {
        return adjacencyList;
    }

    public void addNegihbor(DEdge edge) {
        this.adjacencyList.add(edge);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private List<DEdge> adjacencyList;
    private double distance;

    public DVertex(String name) {
        this.name = name;
        this.distance = Double.MAX_VALUE;
        this.adjacencyList = new ArrayList<>();
    }

    @Override
    public int compareTo(DVertex other) {
        // Heap implementation
        return Double.compare(this.distance, other.getDistance());
    }
}

class DEdge {
    public DEdge(double weight, DVertex start, DVertex destination) {
        this.weight = weight;
        this.start = start;
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    private double weight;

    public DVertex getStart() {
        return start;
    }

    public void setStart(DVertex start) {
        this.start = start;
    }

    public DVertex getDestination() {
        return destination;
    }

    public void setDestination(DVertex destination) {
        this.destination = destination;
    }

    private DVertex start;
    private DVertex destination;

}
