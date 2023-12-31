package blindpatternsolutions;

import java.util.HashMap;

public class WordDictionary {
    class TrieNode {
        HashMap<Character, TrieNode> child = new HashMap<>();
        boolean isEnd = true;
    }
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.child.putIfAbsent(ch, new TrieNode());
            current = current.child.get(ch);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    boolean search(String word, int index, TrieNode current) {
        if (word.length() == index) return current.isEnd;
        if (word.charAt(index) == '.') {
            for (char ch : current.child.keySet()) {
                if (search(word, index + 1, current.child.get(ch)))
                    return true;
            }
            return false;
        } else {
            if (current.child.get(word.charAt(index)) == null) return false;
            return search(word, index + 1, current.child.get(word.charAt(index)));
        }
    }
}
