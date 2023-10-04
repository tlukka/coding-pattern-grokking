package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalOrdering {

    public static void main(String[] args) {
        TopologicalOrdering ordering = new TopologicalOrdering();
        List<Vertex> graph = new ArrayList<>();
        graph.add(new Vertex("0"));
        graph.add(new Vertex("1"));
        graph.add(new Vertex("2"));
        graph.add(new Vertex("3"));
        graph.add(new Vertex("4"));
        graph.add(new Vertex("5"));

        graph.get(2).addNeighbor(graph.get(3));
        graph.get(3).addNeighbor(graph.get(1));
        graph.get(4).addNeighbor(graph.get(0));
        graph.get(4).addNeighbor(graph.get(1));
        graph.get(5).addNeighbor(graph.get(0));
        graph.get(5).addNeighbor(graph.get(2));

        for(int i=0; i<graph.size(); i++) {
            if(!graph.get(i).isVisited())
                ordering.dfs(graph.get(i));
        }

        Stack<Vertex> stack = ordering.getStack();

        // Printing Topological Ordering
        for(int i=0; i<graph.size(); i++)
            System.out.print(stack.pop() + "-");
        System.out.println("");

    }

    Stack<Vertex> stack;

    public TopologicalOrdering() {
        this.stack = new Stack<>();
    }

    public Stack<Vertex> getStack() {
        return stack;
    }

    public void dfs(Vertex vertex) {
        vertex.setVisited(true);
        for (Vertex v : vertex.getAdjuestancyList()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }
        stack.push(vertex);
    }
}
