package grokkingpattern.combine.graphpatterns;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphBfsTraversal {
    private LinkedList<Integer> adjLists[];
    private int vertex;

    //Graph creation
    public GraphBfsTraversal(int vertices) {
        this.vertex = vertices;
        adjLists = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjLists[i] = new LinkedList<Integer>();
        }
    }

    // Add Edge
    void addEdge(int v, int w) {

        adjLists[v].add(w);
    }

    // DFS Algorithm
    void BreadthFirstSearch(int s) {
        boolean visited[] = new boolean[vertex];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[s] = true;

        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");
            Iterator<Integer> ite = adjLists[s].listIterator();
            while (ite.hasNext()) {
                int n = ite.next();
                if (!visited[n]) {
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }

    }

    public static void main(String args[]) {
        GraphBfsTraversal g = new GraphBfsTraversal(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal");

        g.BreadthFirstSearch(2);
    }
}
