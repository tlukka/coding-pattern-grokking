package blindpatternsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearch {
    class Node {
        HashMap<Character, Node> child = new HashMap<>();
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Node root = buildTrie(words);
        List<String> list = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, list, root, r, c);
            }
        }
        return list;
    }

    void dfs(char[][] board, List<String> list, Node curr, int row, int col) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return;
        char ch = board[row][col];
        if (board[row][col] == '#' || curr.child.get(ch) == null)
            return;

        curr = curr.child.get(ch);
        if (curr.word != null) {
            list.add(curr.word);
            curr.word = null;
        }

        board[row][col] = '#';
        dfs(board, list, curr, row + 1, col); // Up
        dfs(board, list, curr, row - 1, col); // Down
        dfs(board, list, curr, row, col - 1); // Left
        dfs(board, list, curr, row, col + 1); // Right
        board[row][col] = ch;
    }

    public Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (char ch : word.toCharArray()) {
                if (!curr.child.containsKey(ch)) {
                    curr.child.put(ch, new Node());
                }
                curr = curr.child.get(ch);
            }
            curr.word = word;
        }
        return root;
    }
}
