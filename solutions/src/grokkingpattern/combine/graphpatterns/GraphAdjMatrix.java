package grokkingpattern.combine.graphpatterns;

// Adjacency Matrix representation
public class GraphAdjMatrix {
    private boolean adjMatrix[][];
    private int vertices;

    //Initialize Graph
    public GraphAdjMatrix(int numberVerices) {
        adjMatrix = new boolean[numberVerices][numberVerices];
        this.vertices = numberVerices;
    }

    // Add Edge

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    // Remove Edge
    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    // Print the matrix
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertices; i++) {
            sb.append(i + ":");
            for (boolean j : adjMatrix[i]) {
                sb.append((j ? 1 : 0) + ":");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String args[]) {

        GraphAdjMatrix g = new GraphAdjMatrix(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g.toString());
    }
}
