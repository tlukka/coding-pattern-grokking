package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShortestPathTopologicalOrdering {
    public static void main(String[] args) {
        List<SVertex> graph = new ArrayList<>();
        SVertex v0 = new SVertex("S");
        SVertex v1 = new SVertex("A");
        SVertex v2 = new SVertex("B");
        SVertex v3 = new SVertex("C");
        SVertex v4 = new SVertex("D");
        SVertex v5 = new SVertex("E");

        v0.addNegibor(new Edge(1, v1));
        v0.addNegibor(new Edge(2, v3));

        v1.addNegibor(new Edge(2, v2));

        v2.addNegibor(new Edge(1, v4));
        v2.addNegibor(new Edge(2, v5));

        v0.addNegibor(new Edge(1, v1));

        v3.addNegibor(new Edge(1, v4));
        v3.addNegibor(new Edge(2, v1));

        v4.addNegibor(new Edge(1, v5));
        graph.add(v0);
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(v5);

        ShortestPathTopologicalOrdering sh = new ShortestPathTopologicalOrdering(graph);
        sh.comptureShortPath();

        for (SVertex v : graph) {
            System.out.println("Distance from source: " + v.getMinDistance() + v.getPredecessor());
        }


    }

    TopologicalOrder topOrder;

    public ShortestPathTopologicalOrdering(List<SVertex> graph) {
        this.topOrder = new TopologicalOrder(graph);
        graph.get(0).setMinDistance(0);
    }

    public void comptureShortPath() {
        Stack<SVertex> stack = topOrder.getStack();
        while (!stack.isEmpty()) {
            SVertex u = stack.pop();
            for (Edge e : u.getNeighbors()) {
                SVertex v = e.getTarget();
                if (u.getMinDistance() + e.getWeight() < v.getMinDistance()) {
                    v.setMinDistance(u.getMinDistance() + e.getWeight());
                    v.setPredecessor(u);
                }
            }
        }
    }


}

class TopologicalOrder {
    private Stack<SVertex> stack;

    public TopologicalOrder(List<SVertex> graph) {
        stack = new Stack<>();
        for (int i = 0; i < graph.size(); i++) {
            if (!graph.get(i).isVisited()) {
                dfs(graph.get(i));
            }
        }
    }

    public Stack<SVertex> getStack() {
        return this.stack;
    }

    public void dfs(SVertex vertex) {
        vertex.setVisited(true);
        for (Edge e : vertex.getNeighbors()) {
            if (!e.getTarget().isVisited()) {
                dfs(e.getTarget());
            }
        }
        stack.push(vertex);
    }
}

class Edge {
    private int weight;
    private SVertex target;

    public Edge(int weight, SVertex target) {
        this.weight = weight;
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public SVertex getTarget() {
        return target;
    }

}

class SVertex {
    String name;
    private boolean visited;
    // shortest path
    private int minDistance;
    // previous node
    private SVertex predecessor;
    private List<Edge> adjacencyList;

    public SVertex(String name) {
        this.name = name;
        setMinDistance(Integer.MAX_VALUE);
        this.adjacencyList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "-" + name + "-" + getPredecessor();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Edge> getNeighbors() {
        return adjacencyList;
    }

    public void addNegibor(Edge edge) {
        this.adjacencyList.add(edge);
    }

    public int getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }

    public SVertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(SVertex predecessor) {
        this.predecessor = predecessor;
    }
}
