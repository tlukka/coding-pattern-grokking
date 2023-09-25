package blindsolutions.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerPeers {

    //Design the ds which stores this relationship so that the following queries are supported.
    //
    //Who is manager of A.
    //Who are suboordinates of A.
    //Who are teammates of A.
    //Sample I/P:
    //C is teammate of D.
    //X is teammate of Y.
    //A is manager of C
    //A is manager of X.
    //
    //The expected o/p for the above i/p:
    //A is manager of C,D,X,Y and C,D,X,Y are all teammates.

    //  subordinate -> manager
    Map<Integer, Integer> parents = new HashMap<>();
    // a -> b and b -> a
    Map<Integer, Set<Integer>> danglingPeers = new HashMap<>();

    // union
    // setManager(A, B) sets A as a direct manager of B
    public void setManager(int manager, int subordinate) {
        parents.put(subordinate, manager);

        Set<Integer> peers = getDanglingPeersOf(subordinate);
        peers.forEach(peer -> danglingPeers.remove(peer));
        peers.forEach(peer -> setManager(manager, peer));
    }

    public void setPeer(int a, int b) {
        if (parents.containsKey(a)) {
            setManager(parents.get(a), b);
        } else if (parents.containsKey(b)) {
            setManager(parents.get(b), a);
        } else {
            addDanglingPeers(a, b);
        }
    }

    public boolean query(int manager, int subordinate) {
        while (parents.containsKey(subordinate) && parents.get(subordinate) != subordinate) {
            if (parents.get(subordinate) == manager) {
                return true;
            }

            subordinate = parents.get(subordinate);
        }

        return false;
    }

    private Set<Integer> getDanglingPeersOf(int peer) {
        Set<Integer> peers = new HashSet<>();
        if (danglingPeers.containsKey(peer)) {
            peers.addAll(danglingPeers.get(peer));

            // handle reversed connection between peers
            danglingPeers.forEach((p, neighbours) -> {
                if (neighbours.contains(peer)) peers.addAll(neighbours);
            });
        }

        return peers;
    }

    private void addDanglingPeers(int a, int b) {
        danglingPeers.computeIfAbsent(a, HashSet::new);
        danglingPeers.computeIfAbsent(b, HashSet::new);

        danglingPeers.get(a).add(b);
        danglingPeers.get(b).add(a);
    }

    public static void main(String[] args) {
        ManagerPeers s = new ManagerPeers();
        s.setManager(1, 2);
        s.setManager(2, 3);
        boolean res1 = s.query(1, 2);  //true
        boolean res11 = s.query(2, 1);  //false because other way around
        boolean res2 = s.query(1, 3);  //true
        boolean res3 = s.query(3, 4);  //false as there is no such relation yet
        s.setPeer(4, 5);
        s.setPeer(4, 8);
        s.setPeer(10, 5);
        s.setManager(3, 4);
        s.setManager(10, 11);
        s.setPeer(11, 12);
        boolean res4 = s.query(3, 4);  //true
        boolean res5 = s.query(4, 5) || s.query(5, 4); // false because they're peers
    }

}