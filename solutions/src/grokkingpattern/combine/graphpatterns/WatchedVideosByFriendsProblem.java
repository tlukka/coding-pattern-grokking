package grokkingpattern.combine.graphpatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


//https://leetcode.com/problems/get-watched-videos-by-your-friends/
//https://leetcode.com/problems/get-watched-videos-by-your-friends/discuss/2008904/Java-BFS-%2B-Minheap-solution-with-explanation
//There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends,
// where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively
// for the person with id = i.

//Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos
// by the friends of your friends and so on. In general, the level k of videos are all watched videos
// by people with the shortest path exactly equal to k with you. Given your id and the level of videos,
// return the list of videos ordered by their frequencies (increasing). For videos with the same frequency
// order them alphabetically from least to greatest.
public class WatchedVideosByFriendsProblem {

    public static void main(String[] args) {

        List<List<String>> watchedVideos = new ArrayList<>();
        List<String> watchedVideo1Level1 = Arrays.asList(new String[]{"A", "B"});
        List<String> watchedVideo1Level2 = Arrays.asList(new String[]{"C"});
        List<String> watchedVideo1Level3 = Arrays.asList(new String[]{"B", "C"});
        List<String> watchedVideo1Level4 = Arrays.asList(new String[]{"D"});
        watchedVideos.add(watchedVideo1Level1);
        watchedVideos.add(watchedVideo1Level2);
        watchedVideos.add(watchedVideo1Level3);
        watchedVideos.add(watchedVideo1Level4);
        WatchedVideosByFriendsProblem wp = new WatchedVideosByFriendsProblem();
        List<String> watchedVideosByFrds = wp.watchedVideosByFriends(watchedVideos, new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}}, 0, 2);
        watchedVideosByFrds.forEach(System.out::println);

    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        // friends is actually an undirected graph of nodes, represented as an adjacency matrix
        // Perform a BFS starting with my "id"
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);

        Set<Integer> visited = new HashSet<>();
        Map<String, Integer> watchedVideosToFrequency = new HashMap<>();
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int v = queue.poll();
                if (visited.contains(v))
                    continue;
                visited.add(v);

                if (v != id && currentLevel == level) {
                    // Get all videos watched by current vertex (current friend)
                    List<String> watchedVideosByV = watchedVideos.get(v);
                    for (String video : watchedVideosByV) {
                        watchedVideosToFrequency.put(video, 1 + watchedVideosToFrequency.getOrDefault(video, 0));
                    }
                }

                // Add the unvisited neighbors

                for (int nbr : friends[v]) {
                    if (!visited.contains(nbr))
                        queue.offer(nbr);
                }
            }
            currentLevel++;
            if (currentLevel > level) {
                break;
            }
        }

        // Define a minheap

        PriorityQueue<Map.Entry<String, Integer>> miniHeap = new PriorityQueue<>((e1, e2) -> {
            if (e1.getValue() == e2.getValue()) {
                // Frequencies are same => Sort lexicographically
                return e1.getKey().compareTo(e2.getKey());
            } else {
                return Integer.compare(e1.getValue(), e2.getValue());
            }
        });

        // Add all elements from watchedVideosToFrequency to minheap
        miniHeap.addAll(watchedVideosToFrequency.entrySet());

        List<String> result = new ArrayList<>();
        while (!miniHeap.isEmpty()) {
            result.add(miniHeap.poll().getKey());
        }

        return result;

    }


}
