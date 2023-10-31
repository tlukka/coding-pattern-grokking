package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathAlgorithams {
    public static void main(String[] args) {
        /*DVertex v0 = new DVertex("A");
        DVertex v1 = new DVertex("B");
        DVertex v2 = new DVertex("C");
        DVertex v3 = new DVertex("D");
        DVertex v4 = new DVertex("E");
        DVertex v5 = new DVertex("F");
        DVertex v6 = new DVertex("G");
        DVertex v7 = new DVertex("H");

        DEdge d0 = new DEdge(v0, v1, 5);
        DEdge d1 = new DEdge(v0, v4, 9);
        DEdge d2 = new DEdge(v0, v7, 8);
        DEdge d3 = new DEdge(v1, v2, 12);
        DEdge d4 = new DEdge(v1, v3, 15);
        DEdge d5 = new DEdge(v1, v7, 4);
        DEdge d6 = new DEdge(v2, v3, 3);
        DEdge d7 = new DEdge(v2, v6, 11);

        DEdge d8 = new DEdge(v3, v6, 9);
        DEdge d9 = new DEdge(v4, v5, 4);
        DEdge d10 = new DEdge(v4, v6, 20);
        DEdge d11 = new DEdge(v4, v7, 5);
        DEdge d12 = new DEdge(v5, v2, 1);
        DEdge d13 = new DEdge(v5, v7, 13);
        DEdge d14 = new DEdge(v7, v2, 7);
        DEdge d15 = new DEdge(v7, v5, 6);

        v0.addNegihbor(d0);
        v0.addNegihbor(d1);
        v0.addNegihbor(d2);

        v1.addNegihbor(d3);
        v1.addNegihbor(d4);
        v1.addNegihbor(d5);

        v2.addNegihbor(d6);
        v2.addNegihbor(d7);

        v3.addNegihbor(d8);

        v4.addNegihbor(d9);
        v4.addNegihbor(d10);
        v4.addNegihbor(d11);

        v5.addNegihbor(d12);
        v5.addNegihbor(d13);

        v7.addNegihbor(d14);
        v7.addNegihbor(d15);

        DijkstraAlogritham algo = new DijkstraAlogritham();
        long startTime = System.nanoTime();
        algo.computePath(v0);

        System.out.println(algo.shortestPath(v6));
        System.out.println("Time taken by Dijkstra algoritham is " + (System.nanoTime() - startTime));


        List<DVertex> vertexList = new ArrayList<>();
        vertexList.add(v0);
        vertexList.add(v1);
        vertexList.add(v2);
        vertexList.add(v3);
        vertexList.add(v4);
        vertexList.add(v5);
        vertexList.add(v6);
        vertexList.add(v7);

        BellmanAlogritham algo1 = new BellmanAlogritham(vertexList, Arrays.asList(d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15));
        long startTime1 = System.nanoTime();
        algo1.computePath(v0);

        System.out.println(algo1.shortestPath(v6));
        System.out.println("Time taken by Bellman Ford algoritham is " + (System.nanoTime() - startTime1));


        List<DVertex> arbitrageList = new ArrayList<>();

        arbitrageList.add(new DVertex("USD"));
        arbitrageList.add(new DVertex("EUR"));
        arbitrageList.add(new DVertex("GBP"));
        arbitrageList.add(new DVertex("CHF"));
        arbitrageList.add(new DVertex("CAD"));

        List<DEdge> edgeList = new ArrayList<>();

        edgeList.add(new DEdge(arbitrageList.get(0), arbitrageList.get(1), -1 * Math.log(0.741)));
        edgeList.add(new DEdge(arbitrageList.get(0), arbitrageList.get(2), -1 * Math.log(0.657)));
        edgeList.add(new DEdge(arbitrageList.get(0), arbitrageList.get(3), -1 * Math.log(1.061)));
        edgeList.add(new DEdge(arbitrageList.get(0), arbitrageList.get(4), -1 * Math.log(1.005)));

        edgeList.add(new DEdge(arbitrageList.get(1), arbitrageList.get(0), -1 * Math.log(1.349)));
        edgeList.add(new DEdge(arbitrageList.get(1), arbitrageList.get(2), -1 * Math.log(0.888)));
        edgeList.add(new DEdge(arbitrageList.get(1), arbitrageList.get(3), -1 * Math.log(1.433)));
        edgeList.add(new DEdge(arbitrageList.get(1), arbitrageList.get(4), -1 * Math.log(1.366)));

        edgeList.add(new DEdge(arbitrageList.get(2), arbitrageList.get(0), -1 * Math.log(1.521)));
        edgeList.add(new DEdge(arbitrageList.get(2), arbitrageList.get(1), -1 * Math.log(1.126)));
        edgeList.add(new DEdge(arbitrageList.get(2), arbitrageList.get(3), -1 * Math.log(1.614)));
        edgeList.add(new DEdge(arbitrageList.get(2), arbitrageList.get(4), -1 * Math.log(1.538)));

        edgeList.add(new DEdge(arbitrageList.get(3), arbitrageList.get(0), -1 * Math.log(0.942)));
        edgeList.add(new DEdge(arbitrageList.get(3), arbitrageList.get(1), -1 * Math.log(0.698)));
        edgeList.add(new DEdge(arbitrageList.get(3), arbitrageList.get(2), -1 * Math.log(0.619)));
        edgeList.add(new DEdge(arbitrageList.get(3), arbitrageList.get(4), -1 * Math.log(0.953)));

        edgeList.add(new DEdge(arbitrageList.get(4), arbitrageList.get(0), -1 * Math.log(0.995)));
        edgeList.add(new DEdge(arbitrageList.get(4), arbitrageList.get(1), -1 * Math.log(0.732)));
        edgeList.add(new DEdge(arbitrageList.get(4), arbitrageList.get(2), -1 * Math.log(0.650)));
        edgeList.add(new DEdge(arbitrageList.get(4), arbitrageList.get(3), -1 * Math.log(1.049)));

        ForexImplementation fx = new ForexImplementation(arbitrageList, edgeList);
        fx.computePath(arbitrageList.get(0));
        fx.printCycle();

         */


        int V = 4;
        int[][] graph = {{0, 3, Integer.MAX_VALUE, 7},
                {8, 0, 2, Integer.MAX_VALUE},
                {5, Integer.MAX_VALUE, 0, 1},
                {2, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}};
        FloydWarshallAlgoritham fly = new FloydWarshallAlgoritham(graph, V);
        fly.floydWarshall(V);
        System.out.print("Shortest path from 1 to 3: ");
        List<Integer> path = fly.constructPath(1, 3);
        fly.printPath(path);
        System.out.print("Shortest path from 0 to 2: ");
        path = fly.constructPath(0, 2);
        fly.printPath(path);

        // Path from node 3 to 2
        System.out.print("Shortest path from 3 to 2: ");
        path = fly.constructPath(3, 2);
        fly.printPath(path);

    }
}


class FloydWarshallAlgoritham {
    int maxNode = 100;
    int[][] distance = new int[maxNode][maxNode];
    int[][] nextElement = new int[maxNode][maxNode];

    public FloydWarshallAlgoritham(int[][] graph, int V) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // No edge between node i and j
                if (graph[i][j] == Integer.MAX_VALUE)
                    nextElement[i][j] = -1;
                else
                    nextElement[i][j] = j;

                distance[i][j] = graph[i][j];
            }
        }
    }

    List<Integer> constructPath(int u, int v) {
        // If there's no path between node u and v, simply return an empty array
        if (nextElement[u][v] == -1)
            return null;
        List<Integer> path = new ArrayList<>();
        path.add(u);
        while (u != v) {
            u = nextElement[u][v];
            path.add(u);
        }

        return path;
    }

    // Standard Floyd Warshall Algorithm with little modification Now if we find
    //that dis[i][j] > dis[i][k] + dis[k][j]  then we modify next[i][j] = next[i][k]

    void floydWarshall(int V) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++) {
                    if (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE)
                        continue;

                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        nextElement[i][j] = nextElement[i][k];
                    }

                }
        }

    }

    void printPath(List<Integer> path) {
        int n = path.size();
        for (int i = 0; i < n - 1; i++)
            System.out.print(path.get(i) + " -> ");
        System.out.print(path.get(n - 1) + "\n");
    }

}

class ForexImplementation {
    private List<DVertex> vertexList;
    private List<DEdge> edgeList;
    private List<DVertex> cycleList;

    public ForexImplementation(List<DVertex> vertexList, List<DEdge> edgeList) {
        this.edgeList = edgeList;
        this.vertexList = vertexList;
        this.cycleList = new ArrayList<>();
    }

    public void computePath(DVertex source) {
        source.setDistance(0);
        // V-1 interations
        for (int i = 0; i < vertexList.size() - 1; i++) {
            for (DEdge edge : edgeList) {
                DVertex u = edge.getStart();
                DVertex v = edge.getDestination();
                double dist = u.getDistance() + edge.getWeight();
                if (dist < v.getDistance()) {
                    v.setDistance(dist);
                    v.setPredecessor(u);
                }
            }
        }

        // Another iteration to get negative cycle....
        for (DEdge edge : edgeList) {
            if (edge.getStart().getDistance() != Double.MAX_VALUE) {
                if (edge.getStart().getDistance() + edge.getWeight() < edge.getDestination().getDistance()) {
                    System.out.println("Negative cycle found");
                    DVertex vertex = edge.getStart();
                    while (!vertex.equals(edge.getDestination())) {
                        this.cycleList.add(vertex);
                        vertex = vertex.getPredecessor();
                    }
                    this.cycleList.add(edge.getDestination());
                    return;
                }
            }
        }
    }

    public void printCycle() {
        if (this.cycleList != null) {
            for (DVertex vertex : this.cycleList) {
                System.out.println(vertex);
            }
        } else {
            System.out.println("No arbitrage opportunity...");
        }
    }
}

class BellmanAlogritham {
    private List<DVertex> vertexList;
    private List<DEdge> edgeList;

    public BellmanAlogritham(List<DVertex> vertexList, List<DEdge> edgeList) {
        this.edgeList = edgeList;
        this.vertexList = vertexList;
    }

    public void computePath(DVertex source) {
        source.setDistance(0);
        // V-1 interations
        for (int i = 0; i < vertexList.size() - 1; i++) {
            for (DEdge edge : edgeList) {
                DVertex u = edge.getStart();
                DVertex v = edge.getDestination();
                double dist = u.getDistance() + edge.getWeight();
                if (dist < v.getDistance()) {
                    v.setDistance(dist);
                    v.setPredecessor(u);
                }
            }
        }

        // Another iteration to get negative cycle....
        for (DEdge edge : edgeList) {
            if (edge.getStart().getDistance() != Double.MAX_VALUE) {
                if (edge.getStart().getDistance() + edge.getWeight() < edge.getDestination().getDistance()) {
                    System.out.println("Negative cycle found");
                    return;
                }
            }
        }
    }

    public List<DVertex> shortestPath(DVertex target) {
        List<DVertex> resultPath = new ArrayList<>();
        for (DVertex vertex = target; vertex != null; vertex = vertex.getPredecessor()) {
            resultPath.add(0, vertex);
        }
        return resultPath;
    }
}

class DijkstraAlogritham {

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
    public DEdge(DVertex start, DVertex destination, double weight) {
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
