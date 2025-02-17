package com.socialnetwork.handler;

import com.socialnetwork.model.SocialNetwork;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class SocialNetworkHelper {
    final Map<String, String> nodeCommunity = new HashMap<>();
    final Map<String, Integer> communitySize = new HashMap<>();
    Map<String, Set<String>> connections = new HashMap<>();
    Map<String, Integer> communityConnections = new HashMap<>();

    public int findDistance(Map<String, Set<String>> connections, String userId1, String userId2) {
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        if (!connections.containsKey(userId1) || !connections.containsKey(userId2))
            return -1;
        queue.add(userId1);
        dist.put(userId1, 0);
        while (!queue.isEmpty()) {
            String node = queue.poll();
            int currentDistance = dist.get(node);
            if (StringUtils.equals(node, userId2)) {
                return currentDistance;
            }
            for (String neighbor : connections.get(node)) {
                if (!dist.containsKey(neighbor)) {
                    dist.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
                }
            }
        }
        return -1;
    }

    public void populateConnections(String userId1, String userId2) {
        connections.putIfAbsent(userId1, new HashSet<>());
        connections.putIfAbsent(userId2, new HashSet<>());
        connections.get(userId1).add(userId2);
        connections.get(userId2).add(userId1);
    }

    public int findShortestPath(List<SocialNetwork> friendships, String userId1, String userId2) {
        friendships.stream().forEach(f -> populateConnections(f.getUserId1(), f.getUserId2()));
        return findDistance(connections, userId1, userId2);
    }

    private void addCommunity(String node) {
        nodeCommunity.put(node, node);
        communitySize.put(node, 1);
    }

    private void initCommunities() {
        connections.keySet().stream().forEach(f -> addCommunity(f));
    }

    private void countConnections() {
        boolean improvement = false;
        for (String node : connections.keySet()) {
            // Count neighboring community connections
            for (String neighbor : connections.get(node)) {
                String neighborCommunity = nodeCommunity.get(neighbor);
                communityConnections.put(neighborCommunity,
                        communityConnections.getOrDefault(neighborCommunity, 0) + 1);
            }
        }
    }

    private double findModularAgain(Map.Entry<String, Integer> connection, int totalEdges) {
        //Q = 1/(2m) * Σᵢⱼ ([ Aᵢⱼ — kᵢkⱼ / (2m)] * δ(cᵢ, cⱼ))
        // Un weighted network
        String candidateCommunity = connection.getKey();
        int sharedEdges = connection.getValue();

        return (sharedEdges / (double) totalEdges) -
                (Math.pow(communitySize.getOrDefault(candidateCommunity, 0), 2) / (4.0 * totalEdges * totalEdges));
    }

    private String getBestCommunity() {
        double bestGain = 0.0;
        String bestCommunity = null;
        for (Map.Entry<String, Integer> community : communityConnections.entrySet()) {
            double currentGain = findModularAgain(community, connections.size());
            if (currentGain > bestGain) {
                bestGain = currentGain;
                bestCommunity = community.getKey();
            }
        }
        return bestCommunity;
    }

    private boolean needOptimization() {
        boolean isOptimized = true;
        for (String connection : connections.keySet()) {
            String bestCommunity = getBestCommunity();
            if (!StringUtils.equals(connection, getBestCommunity())) {
                communitySize.put(connection, communitySize.get(connection) - 1);
                communitySize.put(bestCommunity, communitySize.getOrDefault(bestCommunity, 0) + 1);
                nodeCommunity.put(connection, bestCommunity);
                isOptimized = false;
            }
        }
        return isOptimized;
    }

    public Map<String, List<String>> getCommunities() {
        Map<String, List<String>> communities = new HashMap<>();
        for (Map.Entry<String, String> entry : nodeCommunity.entrySet()) {
            communities.putIfAbsent(entry.getValue(), new ArrayList<>());
            communities.get(entry.getValue()).add(entry.getKey());
        }
        return communities;
    }


    public Map<String, List<String>> findConnections(List<SocialNetwork> friendships) {
        friendships.stream().forEach(f -> populateConnections(f.getUserId1(), f.getUserId2()));
        initCommunities();
        countConnections();
        boolean isOptimized = true;
        while (isOptimized && connections.size() > 1) {
            isOptimized = needOptimization();
        }
        return getCommunities();
    }

    public int calculateDegreeCentrality(List<SocialNetwork> friendships, String userId) {
        friendships.stream().forEach(f -> populateConnections(f.getUserId1(), f.getUserId2()));
        List friends = new ArrayList();
        friends = connections.entrySet().stream().filter(e -> StringUtils.equals(e.getKey(), userId)).map(e -> e.getValue()).flatMap(Set::stream).collect(Collectors.toList());
        return CollectionUtils.size(friends);
    }

}
