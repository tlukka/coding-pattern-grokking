package grokkingpattern.combine.graphpatterns;

import java.util.Arrays;

public class PrimAlgoGraph {
    // Input graph and vertices
    public void findMinSpinningGraph(int G[][], int V) {

        int INF = Integer.MAX_VALUE;
        // create a array to track selected vertex selected will become true otherwise false
        boolean[] selected = new boolean[V];
        Arrays.fill(selected, false);
        int no_edge; // number of edge

        no_edge = 0; //set number of edge to 0

        // the number of egde in minimum spanning tree will be always less than (V -1),
        // where V is number of vertices in graph

        // choose 0th vertex and make it true
        selected[0] = true;

        // print for edge and weight
        System.out.println("Edge : Weight");

        while (no_edge < V) {
            // For every vertex in the set S, find the all adjacent vertices
            // , calculate the distance from the vertex selected at step 1.
            // if the vertex is already in the set S, discard it otherwise
            // choose another vertex nearest to selected vertex at step 1.

            int min = INF;
            int x = 0; // row_number
            int y = 0; // col_number

            for (int i = 0; i < V; i++) {
                if (selected[i] == true) {
                    for (int j = 0; j < V; j++) {
                        // not in selected and there is an edge
                        if (!selected[j] && G[i][j] != 0 && min > G[i][j]) {
                            min = G[i][j];
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            if (x > 0 || y > 0) {
                System.out.println(x + " - " + y + " :  " + G[x][y]);
                selected[y] = true;
                no_edge++;
            }

        }
    }

    public static void main(String[] args) {
        PrimAlgoGraph g = new PrimAlgoGraph();

        // number of vertices in graph
        int V = 5;

        // create a 2d array of size 5x5 for adjacency matrix to represent graph
        //int[][] G = {{0, 9, 75, 0, 0}, {9, 0, 95, 19, 42}, {75, 95, 0, 51, 66}, {0, 19, 51, 0, 31},
        //{0, 42, 66, 31, 0}};

        int[][] G = {{0, 2, 0, 6, 0}, {2, 0, 3, 8, 5}, {0, 3, 0, 0, 7}, {6, 8, 0, 0, 9}, {0, 5, 7, 9, 0}};
        g.findMinSpinningGraph(G, V);
    }
}
