package grokkingpattern.google.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TreeProblems {

    public static void main(String[] args) {
        BottomViewNode root = new BottomViewNode(20);
        root.left = new BottomViewNode(8);
        root.right = new BottomViewNode(22);
        root.left.left = new BottomViewNode(5);
        root.left.right = new BottomViewNode(3);
        root.right.left = new BottomViewNode(4);
        root.right.right = new BottomViewNode(25);
        root.left.right.left = new BottomViewNode(10);
        root.left.right.right = new BottomViewNode(14);
        TreeProblems rl = new TreeProblems();
        System.out.println("Bottom view of the given binary tree:");
        rl.bottomView(root);
        rl.printBottomViewDfs(root);
    }

    // https://www.geeksforgeeks.org/bottom-view-binary-tree/
    // Given a Binary Tree, The task is to print the bottom view from left to right.
    // A node x is there in output if x is the bottommost node at its horizontal distance.
    // The horizontal distance of the left child of a node x is equal to a horizontal distance of x minus 1,
    // and that of a right child is the horizontal distance of x plus 1.
    void bottomView(BottomViewNode root) {
        if (root == null)
            return;
        int hd = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<BottomViewNode> q = new LinkedList<>();
        q.add(root);
        root.hd = hd;

        // BFS
        while (!q.isEmpty()) {
            BottomViewNode temp = q.poll();
            hd = temp.hd;
            map.put(hd, temp.data);
            if (temp.left != null) {
                temp.left.hd = hd - 1;
                q.add(temp.left);
            }
            if (temp.right != null) {
                temp.right.hd = hd + 1;
                q.add(temp.right);
            }
        }

        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            System.out.print(set.getValue() +" ");
        }
        System.out.println("");
    }

    void printBottomViewUtilDfs(BottomViewNode root, int curr, int hd, TreeMap<Integer, int[]> treeMap) {
        if (root == null)
            return;
        if (!treeMap.containsKey(hd)) {
            treeMap.put(hd, new int[]{root.data, curr});
        } else {
            int[] temp = treeMap.get(hd);
            if (temp[1] <= curr) {
                temp[1] = curr;
                temp[0] = root.data;
            }
            treeMap.put(hd, temp);
        }
        printBottomViewUtilDfs(root.left, curr + 1, hd - 1, treeMap);
        printBottomViewUtilDfs(root.right, curr + 1, hd + 1, treeMap);
    }

    void printBottomViewDfs(BottomViewNode root) {
        TreeMap<Integer, int[]> m = new TreeMap<>();
        printBottomViewUtilDfs(root, 0, 0, m);

        // Prints the values stored by printBottomViewUtil()
        for (int val[] : m.values()) {
            System.out.print(val[0] + " ");
        }
    }
}
