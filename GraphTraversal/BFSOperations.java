package algorithms.bfs;

import java.util.*;

public class BFSOperations {
    
    // TreeNode for level-order traversal
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { 
            this.val = val; 
            left = right = null; 
        }
    }
    
    // Level-order traversal of tree (BFS)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }
    
    // Grid shortest path (BFS with visited tracking)
    public static int shortestPath(int[][] grid, int[] start, int[] end) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});  // row, col, distance
        visited[start[0]][start[1]] = true;
        
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};  // right, down, left, up
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], dist = current[2];
            
            // Found target
            if (r == end[0] && c == end[1]) {
                return dist;
            }
            
            // Explore neighbors
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && 
                    grid[nr][nc] == 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        return -1;  // no path
    }
    
    // Build sample tree for demo
    public static TreeNode buildTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static void main(String[] args) {
        // Level-order traversal
        TreeNode root = buildTree();
        List<List<Integer>> levels = levelOrder(root);
        System.out.println("Level Order Traversal:");
        for (List<Integer> level : levels) {
            System.out.println(level);
        }
        // [[3], [9,20], [15,7]]
        
        // Grid shortest path
        int[][] grid = {
            {0, 0, 1, 0},
            {1, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 1, 0, 0}
        };
        
        int[] start = {0, 0};
        int[] end = {3, 3};
        
        int distance = shortestPath(grid, start, end);
        System.out.println("Shortest path from (" + start[0] + "," + start[1] + 
            ") to (" + end[0] + "," + end[1] + "): " + distance);
        // Output: 7
    }
}
