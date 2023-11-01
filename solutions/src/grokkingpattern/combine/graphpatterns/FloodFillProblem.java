package grokkingpattern.combine.graphpatterns;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/flood-fill/
public class FloodFillProblem {

    int[] DIR = new int[]{0, 1, 0, -1, 0}; // Directions center, top, left, right, bottom
    int rows, columns;

    public int[][] floodFillByDfs(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image; // same color no need to replace
        rows = image.length;
        columns = image[0].length;
        dfsHelper(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    void dfsHelper(int[][] image, int nr, int nc, int oldColor, int newColor) {
        if (nr < 0 || nr == rows || nc < 0 || nc == columns || oldColor != image[nr][nc])
            return;
        image[nr][nc] = newColor; // also mean we marked it as visited!
        for (int i = 0; i < 4; i++) {
            dfsHelper(image, nr + DIR[i], nc + DIR[i + 1], oldColor, newColor);
        }
    }

    public int[][] floodFillByBfs(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image; // same color so no change
        rows = image.length;
        columns = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc}); // inserting row and columns in queue
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = top[0] + i;
                int nc = top[1] + i;
                if (nr < 0 || nr >= rows || nc < 0 || nc >= columns || image[nr][nc] != newColor)
                    continue;
                image[nr][nc] = newColor;
                queue.add(new int[]{nr, nc});
            }
        }
        return image;
    }

    int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // Avoid infinite loop if the new and old colors are the same...
        if (image[sr][sc] == color) return image;
        // Run the fill function starting at the position given...
        fill(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public void fill(int[][] image, int sr, int sc, int color, int cur) {
        // If sr is less than 0 or greater equals to the length of image...
        // Or, If sc is less than 0 or greater equals to the length of image[0]...
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) return;
        // If image[sr][sc] is not equal to previous color...
        if (cur != image[sr][sc]) return;
        // Update the image[sr][sc] as a color...
        image[sr][sc] = color;
        // Make four recursive calls to the function with (sr-1, sc), (sr+1, sc), (sr, sc-1) and (sr, sc+1)...
        fill(image, sr - 1, sc, color, cur);
        fill(image, sr + 1, sc, color, cur);
        fill(image, sr, sc - 1, color, cur);
        fill(image, sr, sc + 1, color, cur);
    }

    public static void main(String[] args) {
        int[][] image = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        FloodFillProblem ffp = new FloodFillProblem();
        int[][] newImage = ffp.floodFillByDfs(image, 1, 1, 2);
        for (int[] i : newImage) {
            System.out.println(Arrays.toString(i));
        }

        int[][] newImageByBfs = ffp.floodFillByBfs(image, 1, 1, 2);
        for (int[] ib : newImageByBfs) {
            System.out.println(Arrays.toString(ib));
        }
    }
}
