package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolution {
    public static void main(String[] args) {
        int[][] maze = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 1}
        };

        MazeSolution mazeSolution = new MazeSolution(maze, 0, 0);
        mazeSolution.findWay();
        System.out.println(Arrays.toString(mazeSolution.findPath().toArray()));
    }


    // Count number of ways to reach destination in a Maze
    //https://www.geeksforgeeks.org/rat-in-a-maze/
    //https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/

    private int startRow;
    private int startCol;
    private int[][] maze;
    private boolean[][] visited;

    public MazeSolution(int[][] maze, int row, int col) {
        this.maze = maze;
        this.startCol = col;
        this.startRow = row;
        this.visited = new boolean[maze.length][maze.length];
    }

    public void findWay() {
        boolean isPathExist = dfs(startRow, startCol);
        if (isPathExist) {
            System.out.println("Rat is able to get out from maze");
        } else
            System.out.println("Rat is unable to get out from maze");
    }

    boolean dfs(int row, int col) {
        // base condition
        if (row == maze.length - 1 && col == maze.length - 1) {
            return true;
        }
        if (isFeasiable(row, col)) {
            visited[row][col] = true;
            if (dfs(row + 1, col)) //Down
                return true;
            if (dfs(row - 1, col)) //Up
                return true;
            if (dfs(row, col - 1)) //Left
                return true;
            if (dfs(row, col + 1)) //right
                return true;
            visited[row][col] = false; // BackTrack
            return false;
        }
        return false;
    }

    boolean isFeasiable(int row, int col) {
        if (row < 0 || row > maze.length - 1)
            return false;
        if (col < 0 || col > maze.length - 1)
            return false;
        if (maze[row][col] == 0)
            return false;
        if (visited[row][col])
            return false;
        return true;
    }

    List<String> findPath() {
        visited = new boolean[maze.length][maze.length];
        List<String> paths = new ArrayList<>();
        if (maze[0][0] == 1)
            printPath(startRow, startCol, paths, "");
        return paths;
    }

    void printPath(int row, int col, List<String> ans, String move) {
        if (row >= 0 && col >= 0 && row < maze.length && col < maze.length && !visited[row][col] && maze[row][col] != 0) {
            if (row == maze.length - 1 && col == maze.length - 1) {
                ans.add(move);
                return;
            }
            visited[row][col] = true;
            printPath(row + 1, col, ans, move + 'D');
            printPath(row - 1, col, ans, move + 'U');
            printPath(row, col - 1, ans, move + 'L');
            printPath(row, col + 1, ans, move + 'R');
            visited[row][col] = false;
        } else {
            return;
        }
    }

}
