package grokkingpattern.google.tree;

public class BottomViewNode {
    int data;
    int hd; // horizontal distance.
    BottomViewNode left, right;

    public BottomViewNode(int key) {
        data = key;
        hd = Integer.MAX_VALUE;
        left = right = null;
    }
}
