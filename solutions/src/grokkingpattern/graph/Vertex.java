package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;

class Vertex {
    String name;
    private List<Vertex> adjuestancyList;
    private boolean visited;

    @Override
    public String toString() {
        return this.name;
    }

    public List<Vertex> getAdjuestancyList() {
        return adjuestancyList;
    }

    public void addNeighbor(Vertex vertex) {
        this.adjuestancyList.add(vertex);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex(String name) {
        this.name = name;
        this.adjuestancyList = new ArrayList<>();
    }
}
