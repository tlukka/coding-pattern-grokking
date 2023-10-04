package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;

public class CycleDetection {

    public static void main(String[] args) {
        CycleDetection detection = new CycleDetection();
        CVertex a = new CVertex("A");
        CVertex b = new CVertex("B");
        CVertex c = new CVertex("C");
        CVertex d = new CVertex("D");
        CVertex e = new CVertex("E");
        CVertex f = new CVertex("F");
        f.addNeighbor(a);
        a.addNeighbor(e);
        a.addNeighbor(c);
        e.addNeighbor(f);
        c.addNeighbor(b);
        c.addNeighbor(d);

        detection.detectCycle(f);
    }

    public void detectCycle(CVertex vertex) {
        dfs(vertex);
    }

    void dfs(CVertex vertex) {
        vertex.setBeingVisited(true);
        for (CVertex v : vertex.getNeighbors()) {
            if (v.isBeingVisited())
                System.out.println("Cycle detected");

            if (!v.isVisited()) {
                v.setVisited(true);
                dfs(v);
            }
        }
        vertex.setBeingVisited(false);
        vertex.setVisited(true);
    }
}

class CVertex {
    private String name;
    private boolean visited;
    private boolean beingVisited;

    @Override
    public String toString() {
        return name;
    }

    private List<CVertex> adjuencyList;

    public CVertex(String name) {
        this.name = name;
        this.adjuencyList = new ArrayList<>();

    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isBeingVisited() {
        return beingVisited;
    }

    public void setBeingVisited(boolean beingVisited) {
        this.beingVisited = beingVisited;
    }

    public List<CVertex> getNeighbors() {
        return adjuencyList;
    }

    public void addNeighbor(CVertex vertex) {
        this.adjuencyList.add(vertex);
    }
}
