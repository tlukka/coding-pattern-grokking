package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimAlgoritham {


    public static void main(String[] args) {

        List<PVertex> vertexList = new ArrayList<>();

        PVertex vertex0 = new PVertex("A");
        PVertex vertex1 = new PVertex("B");
        PVertex vertex2 = new PVertex("C");
        PVertex vertex3 = new PVertex("D");
        PVertex vertex4 = new PVertex("E");
        PVertex vertex5 = new PVertex("F");
        PVertex vertex6 = new PVertex("G");

        vertexList.add(vertex0);
        vertexList.add(vertex1);
        vertexList.add(vertex2);
        vertexList.add(vertex3);
        vertexList.add(vertex4);
        vertexList.add(vertex5);
        vertexList.add(vertex6);

        vertex0.addEdge(new PEdge(vertex0, vertex1, 1));
        vertex0.addEdge(new PEdge(vertex0, vertex2, 2));
        vertex0.addEdge(new PEdge(vertex0, vertex3, 12));

        vertex1.addEdge(new PEdge(vertex1, vertex0, 1));
        vertex1.addEdge(new PEdge(vertex1, vertex3, 4));
        vertex1.addEdge(new PEdge(vertex1, vertex4, 7));
        vertex1.addEdge(new PEdge(vertex1, vertex6, 8));

        vertex2.addEdge(new PEdge(vertex2, vertex0, 2));
        vertex2.addEdge(new PEdge(vertex2, vertex3, 6));
        vertex2.addEdge(new PEdge(vertex2, vertex5, 3));

        vertex3.addEdge(new PEdge(vertex3, vertex0, 12));
        vertex3.addEdge(new PEdge(vertex3, vertex1, 4));
        vertex3.addEdge(new PEdge(vertex3, vertex2, 6));
        vertex3.addEdge(new PEdge(vertex3, vertex4, 2));
        vertex3.addEdge(new PEdge(vertex3, vertex5, 5));

        vertex4.addEdge(new PEdge(vertex4, vertex1, 7));
        vertex4.addEdge(new PEdge(vertex4, vertex3, 2));
        vertex4.addEdge(new PEdge(vertex4, vertex5, 4));
        vertex4.addEdge(new PEdge(vertex4, vertex6, 9));

        vertex5.addEdge(new PEdge(vertex5, vertex2, 3));
        vertex5.addEdge(new PEdge(vertex5, vertex3, 5));
        vertex5.addEdge(new PEdge(vertex5, vertex4, 4));
        vertex5.addEdge(new PEdge(vertex5, vertex6, 2));

        vertex6.addEdge(new PEdge(vertex6, vertex1, 8));
        vertex6.addEdge(new PEdge(vertex6, vertex4, 9));
        vertex6.addEdge(new PEdge(vertex6, vertex5, 2));

        PrimAlgoritham algoritham = new PrimAlgoritham(vertexList);
        algoritham.computeMST(vertex0);
        algoritham.printSolution();

    }

    // most of the operations have O(1) running time
    private Set<PVertex> unvisited;
    private List<PEdge> mst;
    private PriorityQueue<PEdge> heap;
    private double fullCost;

    public PrimAlgoritham(List<PVertex> vertexList) {
        mst = new ArrayList<>();
        unvisited = new HashSet<>(vertexList);
        heap = new PriorityQueue<>();
    }

    public void computeMST(PVertex vertex) {
        // remove visited
        unvisited.remove(vertex);
        while (!unvisited.isEmpty()) {
            //insert all the edges into the heap except for the edges leading to already visited vertices
            for (PEdge edge : vertex.getNegibhors()) {
                if (unvisited.contains(edge.getDestination())) {
                    heap.add(edge);
                }
            }
            PEdge minEdge = heap.remove();
            if (!unvisited.contains(minEdge.getDestination()))
                continue;
            mst.add(minEdge);
            fullCost += minEdge.getWeight();
            //next iteration we consider the next vertex
            vertex = minEdge.getDestination();
            unvisited.remove(vertex);

        }
    }

    public void printSolution() {
        System.out.println("Cost of MST: " + this.fullCost);
        for (PEdge edge : this.mst)
            System.out.println(edge);
    }

}

class PVertex {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PEdge> getNegibhors() {
        return adjancenyList;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addEdge(PEdge edge) {
        this.adjancenyList.add(edge);
    }

    String name;
    List<PEdge> adjancenyList;

    public PVertex(String name) {
        this.name = name;
        this.adjancenyList = new ArrayList<>();
    }


}

class PEdge implements Comparable<PEdge> {
    public PEdge(PVertex source, PVertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return source + "" + destination;
    }

    double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public PVertex getSource() {
        return source;
    }

    public void setSource(PVertex source) {
        this.source = source;
    }

    public PVertex getDestination() {
        return destination;
    }

    public void setDestination(PVertex destination) {
        this.destination = destination;
    }

    PVertex source;
    PVertex destination;

    @Override
    public int compareTo(PEdge o) {
        // Comparing to use in PriorityQueue implementation
        return Double.compare(this.weight, o.getWeight());
    }
}