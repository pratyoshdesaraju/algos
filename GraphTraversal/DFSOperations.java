package algorithms.dfs;

import java.util.*;

public class DFSOperations {
    
    // Grid cell coordinates
    static class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    // Count islands (connected '1's in 2D grid)
    public static int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsIsland(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private static void dfsIsland(char[][] grid, int row, int col) {
        int rows = grid.length, cols = grid[0].length;
        
        // Base cases
        if (row < 0 || row >= rows || col < 0 || col >= cols || 
            grid[row][col] != '1') {
            return;
        }
        
        grid[row][col] = '0';  // mark as visited
        
        // Recur for 4 neighbors
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for (int[] dir : directions) {
            dfsIsland(grid, row + dir[0], col + dir[1]);
        }
    }
    
    // Graph representation and cycle detection
    static class Graph {
        int vertices;
        List<Integer>[] adjList;
        
        @SuppressWarnings("unchecked")
        Graph(int v) {
            vertices = v;
            adjList = new List[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }
        
        void addEdge(int u, int v) {
            adjList[u].add(v);
            adjList[v].add(u);  // undirected
        }
    }
    
    // Cycle detection using DFS (undirected graph)
    private static Map<Integer, String> visited;
    
    public static boolean hasCycle(Graph graph) {
        visited = new HashMap<>();
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited.containsKey(i)) {
                if (dfsCycle(graph, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean dfsCycle(Graph graph, int node, int parent) {
        visited.put(node, "VISITING");
        
        for (int neighbor : graph.adjList[node]) {
            if (!visited.containsKey(neighbor)) {
                if (dfsCycle(graph, neighbor, node)) {
                    return true;
                }
            } else if (neighbor != parent && "VISITING".equals(visited.get(neighbor))) {
                return true;  // back edge found
            }
        }
        
        visited.put(node, "VISITED");
        return false;
    }
    
    public static void main(String[] args) {
        // Islands
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        
        System.out.println("Number of islands: " + numIslands(grid));  // 3
        
        // Graph cycle detection
        Graph graph = new Graph(5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        
        System.out.println("Graph has cycle: " + hasCycle(graph));  // true (1-0-2 cycle)
        
        // Tree (no cycle)
        Graph tree = new Graph(4);
        tree.addEdge(0, 1);
        tree.addEdge(0, 2);
        tree.addEdge(0, 3);
        System.out.println("Tree has cycle: " + hasCycle(tree));  // false
    }
}
