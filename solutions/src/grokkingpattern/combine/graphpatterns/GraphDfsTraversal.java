package grokkingpattern.combine.graphpatterns;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphDfsTraversal {
   private LinkedList<Integer> adjLists[];
   private boolean visited[];

   //Graph creation
   public GraphDfsTraversal(int vertices) {
     adjLists = new LinkedList[vertices];
     visited = new boolean[vertices];

     for(int i=0; i< vertices ; i++) {
         adjLists[i] = new LinkedList<Integer>();
     }
   }

    // Add Edge
    void addEdge(int src, int dest) {
       adjLists[src].add(dest);
    }

    // DFS Algorithm
    void DepthFirstSearch(int vertex) {
       visited[vertex] = true;
        System.out.print(vertex + " ");
        Iterator<Integer> ite = adjLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if(!visited[adj])
                DepthFirstSearch(adj);
        }
    }

    public static void main(String args[]) {
        GraphDfsTraversal g = new GraphDfsTraversal(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        System.out.println("Following is Depth First Traversal");

        g.DepthFirstSearch(2);
    }
}
