package grokkingpattern.google.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {
    //https://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
    // Using Backtracking..
    // Time Complexity:  O(N2 *M2) and Auxiliary Space:   O(N*M)
    // Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character.
    // Find all possible words that can be formed by a sequence of adjacent characters.
    // Note that we can move to any of 8 adjacent characters, but a word should not have multiple instances of same cell.
    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] boggle = {{'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};
        String[] words = ws.wordBoggle(boggle, new String[]{"GEEKS", "FOR", "QUIZ", "GUQ", "EE"});
        for (String str : words)
            System.out.println(str);

        String[] wordsII = ws.wordBoggleII(boggle, new String[]{"GEEKS", "FOR", "QUIZ", "GUQ", "EE"});
        for (String str : wordsII)
            System.out.println(str);


        char[][] boggle1 = {{'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};
        String[] words1 = ws.wordBoggleOptimized(boggle, new String[]{"GEEKS", "FOR", "QUIZ", "GUQ", "EE"});
        for (String str : words1)
            System.out.println(str);


        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GEE"};

        // root Node of trie
        TrieNode root = new TrieNode();

        // insert all words of dictionary into trie
        int n = dictionary.length;
        for (int i = 0; i < n; i++)
            TrieNode.insert(root, dictionary[i]);

        TrieNode.findWords(boggle, root);

        String[] dictionary1 = {"GEEKS", "ABCFIHGDE"};
        char[][] boggle2 = {{'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}};

        TrieNode root2 = new TrieNode();
        for (int i = 0; i < dictionary1.length; i++)
            TrieNode.insert(root2, dictionary1[i]);
        TrieNode.findWords(boggle2, root2);

        words1 = ws.wordBoggleOptimized(boggle2, dictionary1);
        for (String str : words1)
            System.out.println(str);

    }

    String[] dictionary;

    public String[] wordBoggle(char board[][], String[] dictionary) {
        this.dictionary = dictionary;
        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> result = new HashSet<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, visited, r, c, "", result);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    void dfs(char[][] board, boolean[][] visited, int r, int c, String str, Set<String> result) {
        visited[r][c] = true;
        str = str + board[r][c];
        if (isWordExists(str))
            result.add(str);

        // Loop
        for (int i = r - 1; i <= r + 1 && i < board.length; i++) {
            for (int j = c - 1; j <= c + 1 && j < board[0].length; j++) {
                if (i >= 0 && j >= 0 && !visited[i][j])
                    dfs(board, visited, i, j, str, result);
            }
        }

        // backtrack...
        str = "" + str.charAt(str.length() - 1);
        visited[r][c] = false;
    }

    boolean isWordExists(String str) {
        for (int i = 0; i < dictionary.length; i++) {
            if (str.equals(dictionary[i]))
                return true;
        }
        return false;
    }

    boolean dfsOptimized(char[][] board, String s, int i, int j, int n, int m, int idx) {
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] == 'x') {
            return false;
        }
        if (s.charAt(idx) != board[i][j])
            return false;
        if (idx == s.length() - 1)
            return true;
        char temp = board[i][j];
        board[i][j] = 'x';
        boolean a = dfsOptimized(board, s, i, j + 1, n, m, idx + 1);
        boolean b = dfsOptimized(board, s, i, j - 1, n, m, idx + 1);
        boolean c = dfsOptimized(board, s, i + 1, j, n, m, idx + 1);
        boolean d = dfsOptimized(board, s, i - 1, j, n, m, idx + 1);
        boolean e = dfsOptimized(board, s, i + 1, j + 1, n, m, idx + 1);
        boolean f = dfsOptimized(board, s, i - 1, j + 1, n, m, idx + 1);
        boolean g = dfsOptimized(board, s, i + 1, j - 1, n, m, idx + 1);
        boolean h = dfsOptimized(board, s, i - 1, j - 1, n, m, idx + 1);
        board[i][j] = temp;
        return a || b || c || d || e || f || g || h;
    }

    //Time Complexity: O(N*W + R*C^2) and Auxiliary Space: O(N*W + R*C)
    public String[] wordBoggleOptimized(char board[][], String[] dictionary) {
        Set<String> result = new HashSet<>();
        int n = board.length, m = board[0].length;
        for (String str : dictionary)
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (dfsOptimized(board, str, r, c, n, m, 0))
                        result.add(str);
                }
            }
        return result.toArray(new String[result.size()]);
    }

    // OPTIMIZED APPROACH WITHOUT USING TRIE
    //Time Complexity: O(N*W + R*C^2)
    //Space Complexity: O(N*W + R*C)
    String[] wordBoggleII(char[][] board, String[] dictionary) {
        int r = board.length;
        int c = board[0].length;

        List<String> temp = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(dictionary));
        int n = set.size();

        String[] dict = new String[n];
        int id = 0;
        for (String str : set) {
            dict[id++] = str;
        }
        for (int i = 0; i < dict.length; i++) {
            if (exist(board, dict[i], r, c))
                temp.add(dict[i]);
        }
        return temp.toArray(new String[temp.size()]);
    }

    boolean exist(char[][] board, String word, int r, int c) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == word.charAt(0) && search(board, word, 0, i, j, r, c))
                    return true;
            }
        }
        return false;
    }

    boolean search(char[][] board, String word, int len, int i, int j, int r, int c) {
        if (i < 0 || i >= r || j < 0 || j >= c)
            return false;

        if (board[i][j] != word.charAt(len))
            return false;

        if (len == word.length() - 1)
            return true;

        char ch = board[i][j];
        board[i][j] = '@';
        boolean ans
                = search(board, word, len + 1, i - 1, j, r, c)
                || search(board, word, len + 1, i + 1, j, r, c)
                || search(board, word, len + 1, i, j - 1, r, c)
                || search(board, word, len + 1, i, j + 1, r, c)
                || search(board, word, len + 1, i - 1, j + 1, r, c)
                || search(board, word, len + 1, i - 1, j - 1, r, c)
                || search(board, word, len + 1, i + 1, j - 1, r, c)
                || search(board, word, len + 1, i + 1, j + 1, r, c);

        board[i][j] = ch; // back tracking
        return ans;
    }

    // Boggle using Trie
    // Time complexity: O(4^(N^2)) and Auxiliary Space: O(N^2).
    // https://www.geeksforgeeks.org/boggle-using-trie/
    static final int SIZE = 26;

    static class TrieNode {
        TrieNode[] child = new TrieNode[SIZE];
        boolean isEnd;

        TrieNode() {
            isEnd = false;
            for (int i = 0; i < SIZE; i++) {
                child[i] = null;
            }
        }

        static void insert(TrieNode root, String key) {
            int n = key.length();
            TrieNode pChild = root;
            for (int i = 0; i < n; i++) {
                int idx = key.charAt(i) - 'A';
                if (pChild.child[idx] == null) {
                    pChild.child[idx] = new TrieNode();
                }
                pChild = pChild.child[idx];
            }
            pChild.isEnd = true;
        }

        static void searchWord(TrieNode root, char[][] board, int i, int j, boolean[][] visited, String str) {
            if (root.isEnd == true)
                System.out.println(str);
            // I and j in  range and we visited
            if (isValid(i, j, visited)) {
                visited[i][j] = true;
                // traverse all child of current root
                for (int k = 0; k < SIZE; k++) {
                    if (root.child[k] != null) {
                        char ch = (char) (k + 'A');
                        // Recursively search 8 adjacent cells of board..
                        if (isValid(i + 1, j + 1, visited) && board[i + 1][j + 1] == ch)
                            searchWord(root.child[k], board, i + 1, j + 1, visited, str + ch);
                        if (isValid(i, j + 1, visited) && board[i][j + 1] == ch)
                            searchWord(root.child[k], board, i, j + 1, visited, str + ch);
                        if (isValid(i - 1, j + 1, visited) && board[i - 1][j + 1] == ch)
                            searchWord(root.child[k], board, i - 1, j + 1, visited, str + ch);
                        if (isValid(i + 1, j, visited) && board[i + 1][j] == ch)
                            searchWord(root.child[k], board, i + 1, j, visited, str + ch);
                        if (isValid(i + 1, j - 1, visited) && board[i + 1][j - 1] == ch)
                            searchWord(root.child[k], board, i + 1, j - 1, visited, str + ch);
                        if (isValid(i, j - 1, visited) && board[i][j - 1] == ch)
                            searchWord(root.child[k], board, i, j - 1, visited, str + ch);
                        if (isValid(i - 1, j - 1, visited) && board[i - 1][j - 1] == ch)
                            searchWord(root.child[k], board, i - 1, j - 1, visited, str + ch);
                        if (isValid(i - 1, j, visited) && board[i - 1][j] == ch)
                            searchWord(root.child[k], board, i - 1, j, visited, str + ch);
                    }
                }
                visited[i][j] = false;
            }
        }

        static boolean isValid(int i, int j, boolean[][] visited) {
            return i >= 0 && i < visited.length && j >= 0 && j < visited[0].length && !visited[i][j];
        }

        static void findWords(char[][] board, TrieNode root) {
            int n = board.length, m = board[0].length;
            boolean[][] visited = new boolean[n][m];
            TrieNode pChild = root;
            String str = "";
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (pChild.child[board[r][c] - 'A'] != null) {
                        str += board[r][c];
                        searchWord(pChild.child[(board[r][c]) - 'A'], board, r, c, visited, str);
                        str = "";
                    }
                }
            }
        }

    }
}
