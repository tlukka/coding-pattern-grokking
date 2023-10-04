package grokkingpattern.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFSImplementation {

    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");

        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");

        a.addNeighbor(b);
        a.addNeighbor(c);

        b.addNeighbor(e);
        b.addNeighbor(a);

        c.addNeighbor(d);
        c.addNeighbor(a);

        d.addNeighbor(c);

        e.addNeighbor(b);

        h.addNeighbor(i);
        i.addNeighbor(h);

        DFSImplementation dfsImp = new DFSImplementation();
        //dfsImp.dfs(Arrays.asList(a, h));
        dfsImp.dfsRecursion(Arrays.asList(a, h));
    }

    Stack<Vertex> stack;

    public DFSImplementation() {
        this.stack = new Stack<>();
    }

    public void dfs(List<Vertex> vertexList) {
        for (Vertex vertex : vertexList) {
            dfsHelper(vertex);
        }
    }

    void dfsHelper(Vertex vertex) {
        stack.push(vertex);
        vertex.setVisited(true);
        while (!stack.isEmpty()) {
            Vertex currentVertex = stack.pop();
            System.out.println("Vertex visited is " + currentVertex);
            for (Vertex v : currentVertex.getAdjuestancyList())
                if (!v.isVisited()) {
                    v.setVisited(true);
                    stack.push(v);
                }
        }
    }

    public void dfsRecursion(List<Vertex> vertexList) {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(true);
            dfsRecursionHelper(vertex);
        }
    }

    void dfsRecursionHelper(Vertex vertex) {
        System.out.println("Vertex is " + vertex);
        for (Vertex v : vertex.getAdjuestancyList()) {
            if (!v.isVisited()) {
                v.setVisited(true);
                dfsRecursionHelper(v);
            }
        }
    }
}
