package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TarjanAlgoritham {

    public static void main(String[] args) {
        TVertex vertex1 = new TVertex("1");
        TVertex vertex2 = new TVertex("2");
        TVertex vertex3 = new TVertex("3");
        TVertex vertex4 = new TVertex("4");
        TVertex vertex5 = new TVertex("5");
        TVertex vertex6 = new TVertex("6");
        TVertex vertex7 = new TVertex("7");
        TVertex vertex8 = new TVertex("8");

        vertex1.addNeighbor(vertex2);

        vertex2.addNeighbor(vertex3);
        vertex2.addNeighbor(vertex5);
        vertex2.addNeighbor(vertex6);

        vertex3.addNeighbor(vertex4);
        vertex3.addNeighbor(vertex7);

        vertex4.addNeighbor(vertex8);
        vertex4.addNeighbor(vertex3);

        vertex5.addNeighbor(vertex1);
        vertex5.addNeighbor(vertex6);

        vertex6.addNeighbor(vertex7);
        vertex7.addNeighbor(vertex6);
        vertex8.addNeighbor(vertex4);
        vertex8.addNeighbor(vertex7);

        List<TVertex> graph = new ArrayList<>();
        graph.add(vertex6);
        graph.add(vertex7);
        graph.add(vertex5);
        graph.add(vertex1);
        graph.add(vertex2);
        graph.add(vertex3);
        graph.add(vertex4);
        graph.add(vertex8);

        TarjanAlgoritham algoritham = new TarjanAlgoritham(graph);
        algoritham.computeAlgoritham();
        algoritham.showComponents();
    }
    private List<TVertex> graph;
    private Stack<TVertex> stack;

    private int index;
    private int counter;

    public TarjanAlgoritham(List<TVertex> graph) {
        this.graph = graph;
        this.stack = new Stack<>();
        this.index = 0;
        this.counter = 0;
    }

    public void computeAlgoritham() {
        for (TVertex vertex : graph) {
            if (!vertex.isVisited()) {
                dfs(vertex);
            }
        }
    }

    void dfs(TVertex vertex) {
        vertex.setIndex(index);
        vertex.setLowLink(index);
        index++;

        vertex.setVisited(true);
        stack.add(vertex);
        vertex.setOnStack(true);

        for (TVertex v : vertex.getAdjacenyList()) {
            if (!v.isVisited()) {
                dfs(v);
                vertex.setLowLink(Math.min(vertex.getIndex(), v.getLowLink()));
            } else if (v.isOnStack()) {
                vertex.setLowLink(Math.min(v.getIndex(), vertex.getLowLink()));
            }
        }

        if (vertex.getIndex() == vertex.getLowLink()) {
            while (true) {
                TVertex w = stack.pop();
                w.setOnStack(false);
                w.setComponentId(counter);
                if (w == vertex)
                    break;
            }
            counter++;
        }

    }

    public void showComponents() {
        graph.forEach(g -> System.out.println(g));
    }
}

class TVertex {
    String name;

    public int getLowLink() {
        return lowLink;
    }

    public void setLowLink(int lowLink) {
        this.lowLink = lowLink;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    int lowLink;
    int index;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isOnStack() {
        return onStack;
    }

    public void setOnStack(boolean onStack) {
        this.onStack = onStack;
    }

    boolean visited;
    boolean onStack;

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    int componentId;

    public List<TVertex> getAdjacenyList() {
        return adjacenyList;
    }

    public void addNeighbor(TVertex vertex) {
        this.adjacenyList.add(vertex);
    }

    List<TVertex> adjacenyList;

    public TVertex(String name) {
        this.name = name;
        this.adjacenyList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Vertex " + name + " belongs to component is " + componentId;
    }

}
