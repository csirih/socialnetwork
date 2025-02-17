package com.socialnetwork.starter;
import java.util.*;

class SocialNetwork {
    private Map<Integer, List<Integer>> graph;

    public SocialNetwork() {
        graph = new HashMap<>();
    }

    // Add a connection (bidirectional friendship)
    public void addFriendship(int user1, int user2) {
        graph.putIfAbsent(user1, new ArrayList<>());
        graph.putIfAbsent(user2, new ArrayList<>());
        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
    }

    // BFS to find shortest distance between two users
    public int findShortestDistance(int startUser, int targetUser) {
        if (!graph.containsKey(startUser) || !graph.containsKey(targetUser)) {
            return -1; // User(s) not found in the network
        }

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();

        queue.add(startUser);
        distance.put(startUser, 0);

        while (!queue.isEmpty()) {
            int user = queue.poll();
            int currentDistance = distance.get(user);

            if (user == targetUser) {
                return currentDistance;
            }

            for (int friend : graph.get(user)) {
                if (!distance.containsKey(friend)) { // Not visited
                    queue.add(friend);
                    distance.put(friend, currentDistance + 1);
                }
            }
        }
        return -1; // No connection found
    }

    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        // Example connections
        network.addFriendship(1, 2);
        network.addFriendship(2, 3);
        network.addFriendship(3, 4);
        network.addFriendship(1, 5);
        network.addFriendship(5, 6);

        int start = 1, target = 4;
        int distance = network.findShortestDistance(start, target);

        if (distance != -1) {
            System.out.println("Shortest distance between user " + start + " and user " + target + " is: " + distance);
        } else {
            System.out.println("No connection between user " + start + " and user " + target);
        }
    }
}
