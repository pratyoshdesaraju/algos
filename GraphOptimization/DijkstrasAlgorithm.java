package algorithms.graph;

import java.util.*;

public class DijkstrasAlgorithm {
    
    // Graph Node with distance for priority queue
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            // Skip if we've found a shorter path already
            if (current.distance > distances[current.vertex]) {
                continue;
            }
            
            // Relax neighbors
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[current.vertex][neighbor] != 0) {  // edge exists
                    int newDist = current.distance + graph[current.vertex][neighbor];
                    
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        pq.offer(new Node(neighbor, newDist));
                    }
                }
            }
        }
        
        return distances;
    }
    
    // Print shortest paths
    public static void printShortestPaths(int[] distances, int start) {
        System.out.println("Shortest distances from node " + start + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To " + i + ": " + 
                (distances[i] == Integer.MAX_VALUE ? "INF" : distances[i]));
        }
    }
    
    public static void main(String[] args) {
        // Weighted graph (adjacency matrix)
        // 0 --2--> 1
        // |       /|
        // 1     /  3
        // |   4 /   |
        // 3 --5--> 2
        int[][] graph = {
            {0, 2, Integer.MAX_VALUE, 1, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
            {Integer.MAX_VALUE, 4, Integer.MAX_VALUE, 0, 2},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        
        // Dijkstra from node 0
        int[] distances = dijkstra(graph, 0);
        printShortestPaths(distances, 0);
        // Expected: [0, 2, 4, 1, 6]
        
        // Dijkstra from node 3
        int[] distances3 = dijkstra(graph, 3);
        printShortestPaths(distances3, 3);
    }
}
