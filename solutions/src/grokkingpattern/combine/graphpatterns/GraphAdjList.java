package grokkingpattern.combine.graphpatterns;

import java.util.ArrayList;

// Adjascency List representation
public class GraphAdjList {

    // Add Edge
    static void addEdge(ArrayList<ArrayList<Integer>> arrayLists, int source, int destination) {
        arrayLists.get(source).add(destination);
        arrayLists.get(destination).add(source);
    }

    public static void main(String[] args) {
        // Create the graph
        int vertix = 5;
        ArrayList<ArrayList<Integer>> am = new ArrayList<ArrayList<Integer>>(vertix);
        for(int i=0; i<vertix; i++) {
          am.add(new ArrayList<>());
        }
        addEdge(am, 0, 1);
        addEdge(am, 0, 2);
        addEdge(am, 0, 3);
        addEdge(am, 1, 2);

        printGraph(am);
    }

    static void printGraph(ArrayList<ArrayList<Integer>> arrayLists) {
        for(int i = 0; i< arrayLists.size(); i++) {
            System.out.println("\nVertex " + i + ":");
            for (int j =0; j< arrayLists.get(i).size(); j ++) {
                System.out.print(" -> " + arrayLists.get(i).get(j));
            }
            System.out.println();
        }
    }
}
