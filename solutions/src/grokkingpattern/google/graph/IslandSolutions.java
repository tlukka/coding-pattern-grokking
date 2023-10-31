package grokkingpattern.google.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IslandSolutions {
    // https://www.geeksforgeeks.org/find-length-largest-region-boolean-matrix
    //  matrix, where each cell contains either a ‘0’ or a ‘1’, and any cell containing a 1 is called a filled cell.
    //  Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally.
    //  If one or more filled cells are also connected, they form a region. find the size of the largest region.

    public static void main(String[] args) {
        IslandSolutions sl = new IslandSolutions();
        int[][] matrix = {{0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1}};
        //System.out.println(sl.largestRegion(matrix));

        int[][] screen = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1}};
        // Co-ordinate provided by the user
        int x = 4;
        int y = 4;
        int[][] updatedGrid = sl.bfsFill(screen, 4, 4, 3);
        for (int[] g : updatedGrid) {
            System.out.println(Arrays.toString(g));
        }
        char[][] grid = new char[][]{{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'X', 'X'}};
        System.out.println(sl.xShape(grid));
    }

    int size;

    int largestRegion(int[][] grid) {
        int ans = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                size = 0;
                if (grid[i][j] == 1 && !visited[i][j])
                    dfs(grid, i, j, visited);
                ans = Math.max(ans, size);
            }
        }
        return ans;
    }

    int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    void dfs(int[][] grid, int r, int c, boolean[][] visited) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !visited[r][c] && grid[r][c] == 1) {
            visited[r][c] = true;
            size++;
            for (int[] d : dirs) {
                dfs(grid, r + d[0], c + d[1], visited);
            }
        }
    }

    // Max Area of Island
    int maxAreaOfIsland(int[][] grid) {
        int area = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                size = 0;
                if (grid[r][c] == 1) {
                    dfs(grid, r, c);
                }
                area = Math.max(area, size);
            }
        }
        return area;
    }

    void dfs(int[][] grid, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
            size++;
            grid[r][c] = 0;  // shrink
            for (int[] d : dirs) {
                dfs(grid, r + d[0], c + d[1]);
            }
        }
    }

    int[][] canFillColor(int[][] grid, int sr, int sc, int color) {
        if (grid[sr][sc] == color)
            return grid;
        dfsFill(grid, sr, sc, color);
        return grid;
    }

    int[][] dir = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    void dfsFill(int[][] grid, int r, int c, int current) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
            return;
        if (grid[r][c] != current)
            return;
        grid[r][c] = current;
        for (int[] d : dir) {
            dfsFill(grid, r + d[0], c + d[1], current);
        }
    }

    int[][] bfsFill(int[][] grid, int sr, int sc, int color) {
        int n = grid.length, m = grid[0].length;
        int old = grid[sr][sc];
        if (color == old)
            return grid;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        while (!q.isEmpty()) {
            int[] top = q.poll();
            grid[top[0]][top[1]] = color;
            for (int[] d : dir) {
                int nr = top[0] + d[0], nc = top[1] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == old) {
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return grid;
    }

    // Given  a grid of n*m consisting of O's and X's. The task is to find the number of 'X' total shapes.
    //Note: 'X' shape consists of one or more adjacent X's (diagonals not included).
    int xShape(char[][] grid) {
        int ans = 0;
        int n = grid.length, m = grid[0].length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 'X') {
                    dfsCount(grid, r, c);
                    ans += 1;
                }
            }
        }
        return ans;
    }

    void dfsCount(char[][] grid, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 'X') {
            grid[r][c] = 'O';
            for (int[] d : dir) {
                dfsCount(grid, r + d[0], c + d[1]);
            }
        }
    }
}
