package algorithms.recursion;

import java.util.*;

public class RecursionBacktracking {
    
    // Base cases
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
    
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static double power(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) return 1 / power(x, -n);
        double half = power(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }
    
    // Permutations of string (backtracking)
    public static List<String> permutations(String s) {
        List<String> result = new ArrayList<>();
        permuteHelper(s.toCharArray(), 0, result);
        return result;
    }
    
    private static void permuteHelper(char[] chars, int start, List<String> result) {
        if (start == chars.length) {
            result.add(new String(chars));
            return;
        }
        
        Set<Character> used = new HashSet<>();
        for (int i = start; i < chars.length; i++) {
            if (used.add(chars[i])) {  // skip duplicates
                swap(chars, start, i);
                permuteHelper(chars, start + 1, result);
                swap(chars, start, i);  // backtrack
            }
        }
    }
    
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    // Subsets (power set)
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());  // empty subset
        
        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> subset : result) {
                List<Integer> newSubset = new ArrayList<>(subset);
                newSubset.add(num);
                newSubsets.add(newSubset);
            }
            result.addAll(newSubsets);
        }
        return result;
    }
    
    // Grid path (top-left to bottom-right, avoid obstacles)
    public static int uniquePathsWithObstacles(int[][] grid) {
        if (grid.length == 0 || grid[0][0] == 1) return 0;
        
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // Fill first row
        for (int j = 1; j < n; j++) {
            if (grid[0][j] == 0) {
                dp[0][j] = dp[0][j-1];
            }
        }
        
        // Fill first column
        for (int i = 1; i < m; i++) {
            if (grid[i][0] == 0) {
                dp[i][0] = dp[i-1][0];
            }
        }
        
        // Fill rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1];
    }
    
    public static void main(String[] args) {
        // Base cases
        System.out.println("factorial(5): " + factorial(5));
        System.out.println("fibonacci(7): " + fibonacci(7));
        System.out.println("power(2, 10): " + power(2, 10));
        
        // Permutations
        System.out.println("\nPermutations of 'aab': " + permutations("aab"));
        
        // Subsets
        int[] nums = {1, 2, 3};
        System.out.println("Subsets of [1,2,3]: " + subsets(nums).size() + " total");
        
        // Grid path
        int[][] grid = {
            {0,0,0},
            {0,1,0},
            {0,0,0}
        };
        System.out.println("Grid paths: " + uniquePathsWithObstacles(grid));  // 2 paths
    }
}
