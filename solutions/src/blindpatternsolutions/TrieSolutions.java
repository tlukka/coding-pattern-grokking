package blindpatternsolutions;

import java.util.HashMap;

public class TrieSolutions {
    class TrieNode {
        HashMap<Character, TrieNode> child = new HashMap<>();
        boolean isEnd = true;
    }

    TrieNode root;

    public TrieSolutions() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            curr.child.putIfAbsent(ch, new TrieNode());
            curr = curr.child.get(ch);
        }
        curr.isEnd = true;
        root = curr;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.child.containsKey(ch)) {
                return false;
            }
            curr = curr.child.get(ch);
        }
        return curr.isEnd;
    }

    public boolean startWith(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            if (!curr.child.containsKey(ch)) {
                return false;
            }
            curr = curr.child.get(ch);
        }
        return true;
    }
}



