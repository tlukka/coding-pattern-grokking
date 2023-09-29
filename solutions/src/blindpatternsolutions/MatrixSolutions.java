package blindpatternsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixSolutions {

    public static void main(String[] args) {
        MatrixSolutions sl = new MatrixSolutions();
        sl.setZeroes(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        sl.setZeroesOptimized(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        sl.setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        sl.setZeroesOptimized(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        int[][] a1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] rotatedA1 = sl.rotate90(a1, 3);
        int[][] rotatedA2 = sl.rotateInPlace90(a1, 3);

        for (int[] rotate : rotatedA1) {
            System.out.println(Arrays.toString(rotate));
        }
        for (int[] rotate : rotatedA2) {
            System.out.println(Arrays.toString(rotate));
        }
    }

    boolean wordExist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfsWordExists(board, word, r, c, 0))
                    return true;
            }
        }
        return false;
    }

    boolean dfsWordExists(char[][] board, String word, int r, int c, int curr) {
        if (word.length() == curr)
            return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '#')
            return false;
        char currChar = board[r][c];
        if (currChar != word.charAt(curr))
            return false;

        board[r][c] = '#';  // visited
        // top, left, right, bottom
        boolean top = dfsWordExists(board, word, r - 1, c, curr + 1); // top
        boolean left = dfsWordExists(board, word, r, c - 1, curr + 1); // left
        boolean bottom = dfsWordExists(board, word, r + 1, c, curr + 1); // bottom
        boolean right = dfsWordExists(board, word, r, c + 1, curr + 1); // right

        board[r][c] = currChar;

        return top || left || bottom || right;

    }

    // Rotating a 2D Array by 90 Degrees
    // I/p: [[1, 2, 3], [4, 5, 6], [7, 8, 9]] => o/p: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    // Spaced Needed
    int[][] rotate90(int[][] a, int n) {
        int[][] aCopy = Arrays.copyOf(a, n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                aCopy[j][n - 1 - i] = a[i][j];
            }
        return aCopy;
    }

    int[][] rotateInPlace90(int[][] a, int n) {
        for (int i = 0; i <= Math.ceil(n / 2) - 1; i++)
            for (int j = 0; j <= Math.floor(n / 2) - 1; j++) {
                int[] temp = new int[]{-1, -1, -1, -1};
                int currentI = i, currentJ = j;
                for (int k = 0; k <= 3; k++) {
                    temp[k] = a[currentI][currentJ];
                    currentI = currentJ;
                    currentJ = n - i - 1;
                }
                for (int k = 0; k <= 3; k++) {
                    a[currentI][currentJ] = temp[(k + 3) % 4];
                    currentI = currentJ;
                    currentJ = n - i - 1;
                }
            }
        return a;
    }

    void rotateInPlace(int[][] matrix) {
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            for (int i = 0; i < right - left; i++) {
                int top = left, bottom = right;
                int topLeft = matrix[top][left + i];
                // move bottom left to top left
                matrix[top][left + i] = matrix[bottom - i][left];
                // move bottom right to bottom left
                matrix[bottom - i][left] = matrix[bottom][right - i];
                // move bottom right to top right
                matrix[bottom][right - i] = matrix[top + i][right];
                // move top right to top left
                matrix[top + i][right] = topLeft;
            }
            left++;
            right--;
        }
        printMatrix(matrix);
    }

    void rotate(int[][] matrix) {
        int n = matrix.length;
        // transpose matrix

        /*
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j]= tmp;
            }
        }

        // reverse each row
        for(int i=0; i<n; i++) {
            for(int j=0; j<n/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = tmp;
            }
        } */

        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }


    // Spiral Matrix printing
    List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> travesalMatrix = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        // travelling first row
        while (r1 <= r2 && c1 <= c2) {
            // travelling first row
            for (int c = c1; c <= c2; c++) {
                travesalMatrix.add(matrix[r1][c]);
            }
            // travelling on last colum
            for (int r = r1 + 1; r <= r2; r++) {
                travesalMatrix.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {
                // travelling last row
                for (int c = c2 - 1; c >= c1; c--) {
                    travesalMatrix.add(matrix[r2][c]);
                }

                // travelling first column
                for (int r = r2 - 1; r >= r1 + 1; r--) {
                    travesalMatrix.add(matrix[r][c1]);
                }

            }
            c1++;
            c2--;
            r1++;
            r2--;
        }
        return travesalMatrix;
    }


    // set matrix with rows and columns to zero if a cell is zero
    void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        boolean[] rows = new boolean[row];
        boolean[] columns = new boolean[column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (rows[i] || columns[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        printMatrix(matrix);
    }

    void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    void setZeroesOptimized(int[][] matrix) {
        boolean hasFirstRowZero = false;
        boolean hasFirstColZero = false;
        for (int c = 0; c < matrix[0].length; c++) { // first row has zero
            if (matrix[0][c] == 0) {
                hasFirstRowZero = true;
                break;
            }
        }

        for (int r = 0; r < matrix.length; r++) { // first column has zero
            if (matrix[r][0] == 0) {
                hasFirstColZero = true;
                break;
            }
        }

        for (int r = 1; r < matrix.length; r++) {
            for (int c = 1; c < matrix[0].length; c++) {
                // pre-processing
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;  // setting first column to zero
                    matrix[0][c] = 0; // setting row zero
                }
            }
        }

        for (int r = 1; r < matrix.length; r++) {
            // first column
            if (matrix[r][0] == 0) {
                setZeroToRow(matrix, r);
            }
        }

        for (int c = 1; c < matrix[0].length; c++) {
            // first row
            if (matrix[0][c] == 0) {
                setZeroToColumn(matrix, c);
            }
        }

        if (hasFirstRowZero)
            setZeroToRow(matrix, 0);
        if (hasFirstColZero)
            setZeroToColumn(matrix, 0);

        printMatrix(matrix);

    }

    void setZeroToColumn(int[][] matrix, int col) {
        for (int r = 0; r < matrix.length; r++) {
            matrix[r][col] = 0;
        }
    }

    void setZeroToRow(int[][] matrix, int row) {
        for (int c = 0; c < matrix[0].length; c++) {
            matrix[row][c] = 0;
        }
    }
}
