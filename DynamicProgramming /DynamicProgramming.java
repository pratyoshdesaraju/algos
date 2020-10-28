package algorithms.dp;

import java.util.Arrays;

public class DynamicProgramming {
    
    // Memoization (Top-Down): 0/1 Knapsack
    private static int[][] memo;
    
    public static int knapsackMemo(int[] weights, int[] values, int W, int n) {
        if (n == 0 || W == 0) return 0;
        
        if (memo[n][W] != -1) return memo[n][W];
        
        if (weights[n-1] <= W) {
            memo[n][W] = Math.max(
                values[n-1] + knapsackMemo(weights, values, W - weights[n-1], n-1),
                knapsackMemo(weights, values, W, n-1)
            );
        } else {
            memo[n][W] = knapsackMemo(weights, values, W, n-1);
        }
        return memo[n][W];
    }
    
    // Tabulation (Bottom-Up): 0/1 Knapsack
    public static int knapsackTabulation(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i-1] <= w) {
                    dp[i][w] = Math.max(
                        values[i-1] + dp[i-1][w - weights[i-1]],
                        dp[i-1][w]
                    );
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        return dp[n][W];
    }
    
    // Climbing Stairs (Memoization)
    private static int[] climbMemo;
    
    public static int climbStairsMemo(int n) {
        climbMemo = new int[n + 1];
        Arrays.fill(climbMemo, -1);
        return climbStairsHelper(n);
    }
    
    private static int climbStairsHelper(int n) {
        if (n <= 2) return n;
        if (climbMemo[n] != -1) return climbMemo[n];
        
        climbMemo[n] = climbStairsHelper(n-1) + climbStairsHelper(n-2);
        return climbMemo[n];
    }
    
    // Climbing Stairs (Tabulation)
    public static int climbStairsTabulation(int n) {
        if (n <= 2) return n;
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    
    // Longest Common Subsequence (Tabulation)
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
    
    // Coin Change (minimum coins, Tabulation)
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        
        for (int coin : coins) {
            for (int x = coin; x <= amount; x++) {
                dp[x] = Math.min(dp[x], dp[x - coin] + 1);
            }
        }
        
        return dp[amount] >= Integer.MAX_VALUE / 2 ? -1 : dp[amount];
    }
    
    public static void main(String[] args) {
        // Knapsack
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int W = 7;
        memo = new int[weights.length + 1][W + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        
        System.out.println("Knapsack Memo(W=7): " + knapsackMemo(weights, values, W, weights.length));
        System.out.println("Knapsack Tab(W=7): " + knapsackTabulation(weights, values, W));
        
        // Climbing stairs
        System.out.println("Climb stairs 5 (memo): " + climbStairsMemo(5));
        System.out.println("Climb stairs 5 (tab): " + climbStairsTabulation(5));
        
        // LCS
        System.out.println("LCS 'abcde' 'ace': " + longestCommonSubsequence("abcde", "ace"));
        
        // Coin change
        int[] coins = {1, 2, 5};
        System.out.println("Coin change 11: " + coinChange(coins, 11));
    }
}
