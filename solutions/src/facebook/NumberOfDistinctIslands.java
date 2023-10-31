package facebook;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class NumberOfDistinctIslands {
    // Find the number of distinct Islands OR connected components.
    // Given a 2d grid map of '1's (land) and '0's (water), count the number of distinct or unique islands.
    // How to determine if two islands are identical: If coordinates in two islands form an identical shape or structure
    // . The coordinates of both the islands can be at a different location.

    public static void main(String[] args) {

        int[][] grid = {{1, 1, 0, 1, 1},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1}};

        NumberOfDistinctIslands n = new NumberOfDistinctIslands();
        n.findDistinctIslands(grid);
    }

    void findDistinctIslands(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<ArrayList<Pair<Integer, Integer>>> islands = new HashSet<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c] && grid[r][c] == 1) {
                    // fix start points
                    int start_x = r, start_y = c;
                    ArrayList<Pair<Integer, Integer>> island = new ArrayList<>();
                    dfsIsalnd(grid, r, c, start_x, start_y, visited, island);
                    islands.add(island);
                }
            }
        }

        if (islands.size() > 0) {
            System.out.println("No of unique distinct islands are: " + islands.size());
        } else {
            System.out.println("No island in the given grid");
        }
    }


    void dfsIsalnd(int[][] grid, int r, int c, int start_x, int start_y, boolean[][] visited, ArrayList<Pair<Integer, Integer>> list) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] || grid[r][c] == 0) {
            return;
        }
        list.add(new Pair<>(r - start_x, c - start_y));
        visited[r][c] = true;
        dfsIsalnd(grid, r + 1, c, start_x, start_y, visited, list); // up
        dfsIsalnd(grid, r - 1, c, start_x, start_y, visited, list); // down
        dfsIsalnd(grid, r, c - 1, start_x, start_y, visited, list); // left
        dfsIsalnd(grid, r, c + 1, start_x, start_y, visited, list); // right
    }

    // Check if Graph is Bipartite - Adjacency List using Depth-First Search
    //Given a graph represented by the adjacency List, write a Depth-First Search(DFS) algorithm
    // to check whether the graph is bipartite or not.

    enum Color {
        WHITE, RED, GREEN
    }

    static class Graph {
        int vertices;
        LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        void addEdge(int source, int destination) {
            //add forward edge
            adjList[source].addFirst(destination);

            //add back edge
            adjList[destination].addFirst(source);
        }

        boolean isBipartite(Graph graph) {
            if (graph.vertices == 0)
                return true;
            Color[] colors = new Color[vertices];
            //color all the vertices with white color
            for (int i = 0; i < colors.length; i++) {
                colors[i] = Color.WHITE;
            }
            //start coloring vertices , this code will handle the disconnected graph as well
            //color the first vertex with RED
            for (int i = 0; i < vertices; i++) {
                if (colors[i] == Color.WHITE) {
                    boolean result = isBipartiteUtil(i, colors);
                    if (result == false)
                        return false;
                }
            }
            return true;
        }

        boolean isBipartiteUtil(int source, Color[] colors) {
            if (source == 0)
                colors[source] = Color.RED;
            for (int i = 0; i < adjList[source].size(); i++) {
                int vertex = adjList[source].get(i);
                if (colors[vertex] == Color.WHITE) {
                    //color is with the alternate color of
                    if (colors[source] == Color.RED) {
                        //if source is in red, make vertex green
                        colors[vertex] = Color.GREEN;
                    } else if (colors[source] == Color.GREEN) {
                        //if source is in red, make vertex green
                        colors[vertex] = Color.RED;
                    }

                    //make recursive call for DFS
                    return isBipartiteUtil(vertex, colors);

                } else if (colors[source] == colors[vertex]) {
                    return false;
                }
            }
            return true;
        }
    }

    //  Determine the order of Tests when tests have dependencies on each other
    // You are working on a project where QA team has automated set of test cases for the project,
    // but these test cases have to be executed in a specific order since test cases has dependencies on each other,
    // for example if test case A and dependency on test case B then test case B has to be executed before test case A.
    // Given a set of test cases, Write an algorithm to print the execution sequence for the test cases.

    // Test Cases: A B C D E F G
    //E depends on B, D, G
    //D depends on B, C
    //C depends on A
    //B depends on A
    //F no dependency
    //G depends on A
    //
    //Output: Test Case sequence: F A B C D E
    static class Node {
        char destination;
        char source;

        Node(char source, char destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    static class TestGraph {
        int vertices;
        LinkedList<Node>[] adjList;

        public TestGraph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        void addEdge(char source, char dest, ArrayList<Character> testCases) {
            Node node = new Node(source, dest);
            adjList[testCases.indexOf(source)].addFirst(node);
        }

        void topologicalSorting(ArrayList<Character> testCases) {
            boolean[] visited = new boolean[vertices];
            Stack<Character> stack = new Stack<Character>();
            //visit from each node if not already visited
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(testCases.get(i), visited, stack, testCases);
                }
            }
            System.out.println("Test Case Sequence: ");
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                System.out.print(stack.pop() + " ");
            }
        }

        void topologicalSortUtil(char sftwr, boolean[] visited, Stack<Character> stack, ArrayList<Character> testCases) {
            int index = testCases.indexOf(sftwr);
            visited[index] = true;
            for (int i = 0; i < adjList[testCases.indexOf(sftwr)].size(); i++) {
                Node node = adjList[index].get(i);
                if (!visited[testCases.indexOf(node.destination)])
                    topologicalSortUtil(node.destination, visited, stack, testCases);
            }
            stack.push(sftwr);
        }
    }

    // Snake and Ladder Problem
    // Given a snake and ladder game, write a function that returns the minimum number of
    // jumps to take top or destination position.
    static class Vertex {
        int cell;
        int moves;
    }

    int findMinMoves(int[] board) {
        int size = board.length;
        //create visited array for each cell
        boolean[] visited = new boolean[size];

        Queue<Vertex> queue = new LinkedList<>();
        //start from position 1 (index 0) and it is already visited
        Vertex vertex = new Vertex();
        vertex.cell = 0;
        vertex.moves = 0;
        queue.add(vertex);
        visited[0] = true;

        //BFS from cell number 0
        while (queue.isEmpty() == false) {
            vertex = queue.poll();
            int cellNum = vertex.cell;
            if (cellNum == size - 1)
                break;

            //Since dice can be controlled and max value can be achieved 6
            //so from the current cell, you can reach to next 6 cells so add next 6 cells
            for (int i = cellNum + 1; i < cellNum + 6 && i < size; i++) {
                if (!visited[i]) {
                    Vertex current = new Vertex();
                    current.moves = vertex.moves + 1;
                    visited[i] = true;
                    if (board[i] == -1)
                        current.cell = i;
                    else
                        current.cell = board[i];
                    queue.add(current);
                }
            }
        }
        return vertex.moves;

    }

    // Rat In A Maze Puzzle
    // Given a maze, NxN matrix. A rat has to find a path from source to destination.
    // maze[0][0] (left top corner)is the source and maze[N-1][N-1](right bottom corner) is destination.
    // There are a few cells that are blocked, which means the rat cannot enter those cells. The rat can move in
    // any direction ( left, right, up, and down).

    boolean findPath(int[][] maze, int x, int y, int N, boolean[][] visited) {
        if (x == N - 1 && y == N - 1) {
            return true;
        }
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 0 && visited[x][y]) {
            visited[x][y] = true;
            if (findPath(maze, x + 1, y, N, visited)) {
                return true;
            }
            if (findPath(maze, x - 1, y, N, visited)) {
                return true;
            }
            if (findPath(maze, x, y + 1, N, visited)) {
                return true;
            }
            if (findPath(maze, x, y - 1, N, visited)) {
                return true;
            }
            visited[x][y] = false;
            return false;
        }
        return false;
    }

    // Knight's Tour Problem
    // A knight's tour is a sequence of moves of a knight on a chessboard such that the knight visits every
    // square only once. If the knight ends on a square that is one knight's move from the beginning square
    boolean findKnightPath(int N) {
        boolean[][] visited = new boolean[N][N];
        return findPathKnight(0, 0, 0, N, visited);
    }

    boolean findPathKnight(int row, int column, int index, int N, boolean[][] visited) {
        // if current is not used already
        if (!visited[row][column])
            return false;
        visited[row][column] = true;
        if (index == N * N - 1)
            return true;
        // go down adn right
        if (canMove(row + 2, column + 1, N) && findPathKnight(row + 2, column + 1, index + 1, N, visited))
            return true;

        // go down adn right
        if (canMove(row + 1, column + 2, N) && findPathKnight(row + 1, column + 2, index + 1, N, visited))
            return true;
// go right and up
        if (canMove(row - 1, column + 2, N) && findPathKnight(row - 1, column + 2, index + 1, N, visited)) {
            return true;
        }
        // go up and right
        if (canMove(row - 2, column + 1, N) && findPathKnight(row - 2, column + 1, index + 1, N, visited)) {
            return true;
        }
        // go up and left
        if (canMove(row - 2, column - 1, N) && findPathKnight(row - 2, column - 1, index + 1, N, visited)) {
            return true;
        }
        // go left and up
        if (canMove(row - 1, column - 2, N) && findPathKnight(row - 1, column - 2, index + 1, N, visited)) {
            return true;
        }
        // go left and down
        if (canMove(row + 1, column - 2, N) && findPathKnight(row + 1, column - 2, index + 1, N, visited)) {
            return true;
        }
        // go down and left
        if (canMove(row + 2, column - 1, N) && findPathKnight(row + 2, column - 1, index + 1, N, visited)) {
            return true;
        }
        visited[row][column] = false;
        return false;
    }

    boolean canMove(int row, int col, int N) {
        if (row >= 0 && col >= 0 && row < N && col < N) {
            return true;
        }
        return false;
    }
}
