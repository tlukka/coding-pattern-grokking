package grokkingpattern.graph;

import java.util.Arrays;
import java.util.List;

public class MinCostCities {
    // Minimum cost to connect all cities
    // Standard algoritham of Prim and Kruskal
    // https://www.geeksforgeeks.org/minimum-cost-connect-cities/
    int minNode(int n, int[] keyValue, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] && keyValue[i] < min) {
                min = keyValue[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    int findMinCost(int n, int[][] graph) {
        int[] keyValue = new int[n];
        Arrays.fill(keyValue, Integer.MAX_VALUE);
        boolean[] mstset = new boolean[n];
        int[] parent = new int[n];
        keyValue[0] = -1;
        parent[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            // find out the minimum node  mong the nodes which are not yet  included in MST
            int u = minNode(i, keyValue, mstset);
            mstset[u] = true;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 && !mstset[v] && graph[u][v] < keyValue[v]) {
                    keyValue[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        //Find out the cost by adding the edge values of MST.
        int cost = 0;
        for (int i = 1; i < n; i++) {
            cost += graph[parent[i]][i];
        }
        return cost;
    }

    // Minimum Product Spanning Tree
    // https://www.geeksforgeeks.org/minimum-product-spanning-tree/


    void printMST(int parent[], int n, int graph[][]) {
        int minProduct = 1;
        for (int i = 1; i < n; i++) {
            System.out.printf("%d - %d %d \n", parent[i], i, graph[i][parent[i]]);
            minProduct *= graph[i][parent[i]];
        }
        System.out.println(minProduct);
    }

    int V = 5;

    void primMST(int[][] inputGraph, double[][] logGraph) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;
        for (int i = 0; i < V - 1; i++) {
            int u = minNode(i, key, mstSet);
            mstSet[u] = true;

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            // Consider only those vertices which are not yet included in MST
            for (int v = 0; v < V; v++) {
                if (logGraph[u][v] > 0 && !mstSet[v] && logGraph[u][v] < key[v]) {
                    key[v] = (int) logGraph[u][v];
                    parent[v] = u;
                }
            }
        }
        printMST(parent, V, inputGraph);
    }

    void minimumProductMST(int graph[][]) {
        double[][] logGraph = new double[V][V];
        // Constructing logGraph from original graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] > 0) {
                    logGraph[i][j] = Math.log(graph[i][j]);
                } else {
                    logGraph[i][j] = 0;
                }
            }
        }
        primMST(graph, logGraph);
    }

    // Kruskals alogritham...
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Subset {
        int parent, rank;

        Subset(int parent, int rank) {
            this.rank = rank;
            this.parent = parent;
        }
    }

    static void kruskals(int V, List<Edge> edges) {
        int j = 0, noOfEdges = 0;
        Subset[] subsets = new Subset[V];
        Edge[] results = new Edge[V];
        for (int i = 0; i < V; i++)
            subsets[i] = new Subset(i, 0);

        // Number of edges to be taken is equal to V-1
        while (noOfEdges < V - 1) {
            Edge next = edges.get(j);
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);
            if (x != y) {
                results[noOfEdges++] = next;
                union(x, y, subsets);
            }
            j++;
        }

        System.out.println("Following are the edges of the constructed MST:");
        int minCost = 0;
        for (int i = 0; i < noOfEdges; i++) {
            System.out.println(results[i].src + " -- " + results[i].dest + " == " + results[i].weight);
            minCost += results[i].weight;
        }
        System.out.println("Total cost of MST: " + minCost);
    }

    static void union(int x, int y, Subset[] subsets) {
        int nx = find(subsets, x);
        int ny = find(subsets, y);

        if (nx == ny)
            return;
        if (subsets[ny].rank < subsets[ny].rank)
            subsets[ny] = subsets[nx]; //
        else if (subsets[nx].rank > subsets[ny].rank)
            subsets[nx] = subsets[ny];
        else {
            subsets[nx] = subsets[ny];
            subsets[nx].rank++;
        }
    }

    static int find(Subset[] subsets, int i) {
        if (i != subsets[i].parent) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // Travelling Salesman Problem using Dynamic Programming
    //https://www.geeksforgeeks.org/travelling-salesman-problem-using-dynamic-programming/

}
