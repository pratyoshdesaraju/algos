package algorithms.graph;

import java.util.*;

public class TopologicalSort {
    
    // Kahn's Algorithm using indegree and queue
    public static List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
        // Build adjacency list and indegree
        List<Integer>[] adjList = new List[numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int[] pre : prerequisites) {
            int course = pre[0];     // course that has prerequisite
            int prereq = pre[1];    // prerequisite course
            
            adjList[prereq].add(course);
            indegree[course]++;
        }
        
        // Kahn's algorithm
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        
        // Add all nodes with indegree 0
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result.add(course);
            
            // Reduce indegree of neighbors
            for (int neighbor : adjList[course]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Check for cycle (not all courses processed)
        return result.size() == numCourses ? result : new ArrayList<>();
    }
    
    // CanFinish helper (course schedule problem)
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> order = topologicalSort(numCourses, prerequisites);
        return order.size() == numCourses;
    }
    
    // Print graph state for debugging
    private static void printGraph(int numCourses, int[][] prerequisites) {
        System.out.println("Prerequisites:");
        for (int[] pre : prerequisites) {
            System.out.println("Course " + pre[0] + " requires " + pre[1]);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Example 1: Course schedule
        int[][] prereqs1 = {{1,0}, {2,0}, {3,1}, {3,2}};
        printGraph(4, prereqs1);
        List<Integer> order1 = topologicalSort(4, prereqs1);
        System.out.println("Topological order: " + order1);  // [0,1,2,3] or similar
        System.out.println("Can finish: " + canFinish(4, prereqs1));  // true
        
        // Example 2: Cycle (cannot finish)
        int[][] prereqs2 = {{1,0}, {0,1}};  // mutual dependency
        printGraph(2, prereqs2);
        List<Integer> order2 = topologicalSort(2, prereqs2);
        System.out.println("Topological order: " + order2);  // empty
        System.out.println("Can finish: " + canFinish(2, prereqs2));  // false
        
        // Example 3: Build order
        int[][] buildOrder = {{5,0}, {5,2}, {4,0}, {4,1}, {2,3}, {3,1}};
        System.out.println("\nBuild order: " + topologicalSort(6, buildOrder));
    }
}
