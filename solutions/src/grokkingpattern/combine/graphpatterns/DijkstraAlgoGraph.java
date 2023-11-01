package grokkingpattern.combine.graphpatterns;

import java.util.Arrays;

public class DijkstraAlgoGraph {
    static void dijkstraAlgo(int[][] G, int source) {
        // count of vertex in graph
        int count = G.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];

        // set distance infinite and visited false
        Arrays.fill(visitedVertex, false);
        Arrays.fill(distance, Integer.MAX_VALUE);

        // Distance of self loop is zero
        distance[source] = 0;

        for (int i = 0; i < count; i++) {
            //Update the distance between neighbouring vertex and source vertex
            int minDistVertex = findMinDistance(distance, visitedVertex);
            visitedVertex[minDistVertex] = true;

            //Update all the neighbouring vertex distances

            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && G[minDistVertex][v] != 0 && distance[minDistVertex] + G[minDistVertex][v] < distance[v]) {
                    distance[v] = distance[minDistVertex] + G[minDistVertex][v];
                }
            }
        }

        for (int i = 0; i < distance.length; i++) {
            System.out.println(String.format("Distance from %s to %s is %s", source, i, distance[i]));
        }


    }

    static int findMinDistance(int[] distance, boolean[] visited) {

        int minDist = Integer.MAX_VALUE;
        int minDistVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minDist) {
                minDist = distance[i];
                minDistVertex = i;
            }

        }

        return minDistVertex;
    }

    public static void main(String[] args) {
        //int graph[][] = new int[][]{{0, 0, 1, 2, 0, 0, 0}, {0, 0, 2, 0, 0, 3, 0}, {1, 2, 0, 1, 3, 0, 0},
        //       {2, 0, 1, 0, 0, 0, 1}, {0, 0, 3, 0, 0, 2, 0}, {0, 3, 0, 0, 2, 0, 1}, {0, 0, 0, 1, 0, 1, 0}};
        int graph[][]
                = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        DijkstraAlgoGraph dijkstraAlgoGraph = new DijkstraAlgoGraph();
        dijkstraAlgoGraph.dijkstraAlgo(graph, 0);
    }
}
