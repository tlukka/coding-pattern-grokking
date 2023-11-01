package grokkingpattern.combine.graphpatterns;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/number-of-islands/
//Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
// You may assume all four edges of the grid are all surrounded by water.

public class NumberIslandProblem {
    private int[] directions = new int[]{0, 1, 0, -1, 0};
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // right, up, down, left
    private int rows, columns;

    private Queue<int[]> queue = new LinkedList<>();
    private boolean[][] visited;

    public int countOfIslandByDfs(char[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        rows = grid.length;
        columns = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == '1') {
                    countIslandDfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void countIslandDfs(char[][] grid, int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns || grid[row][column] == '0')
            return;
        grid[row][column] = '0'; // making to visit.
        /* for (int i = 0; i < 4; i++) {
            countIslandDfs(grid, row + directions[i], column + directions[i + 1]);
        } */

        for (int[] dir : dirs) {
            countIslandDfs(grid, row + dir[0], column + dir[1]);
        }
    }

    public int countOfIslandByBfs(char[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        rows = grid.length;
        columns = grid[0].length;
        int count = 0;
        visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    countIslandBfs(grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void countIslandBfs(char[][] grid) {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : dirs) {
                int row = current[0] + dir[0];
                int colum = current[1] + dir[1];
                if (row < 0 || row >= rows || colum < 0 || colum >= columns || visited[row][colum] || grid[row][colum] == '0')
                    continue;
                visited[row][colum] = true;
                queue.offer(new int[]{row, colum});
            }
        }

    }

    public static void main(String[] ars) {
        char[][] grid = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        NumberIslandProblem np = new NumberIslandProblem();
        char[][] bfsGrid = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(np.countOfIslandByDfs(grid));
        System.out.println(np.countOfIslandByBfs(bfsGrid));
        char[][] grid1 = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] bfsGrid1 = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(np.countOfIslandByDfs(grid1));
        System.out.println(np.countOfIslandByBfs(bfsGrid1));

    }
}
