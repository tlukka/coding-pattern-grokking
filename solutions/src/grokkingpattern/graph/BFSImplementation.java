package grokkingpattern.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFSImplementation {

    public static void main(String[] args) {
        BFSImplementation bfs = new BFSImplementation();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        a.addNeighbor(b);
        a.addNeighbor(c);

        b.addNeighbor(e);
        b.addNeighbor(a);

        c.addNeighbor(d);
        c.addNeighbor(a);

        d.addNeighbor(c);

        e.addNeighbor(b);

        bfs.traversal(a);

    }

    Queue<Vertex> queue;

    public BFSImplementation() {
        queue = new LinkedList<>();
    }

    public void traversal(Vertex root) {
        queue.add(root);
        root.setVisited(true);


        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.remove();
            System.out.println("Visting vertex is " + currentVertex);
            for (Vertex v : currentVertex.getAdjuestancyList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    queue.add(v);
                }
            }
        }
    }
}

