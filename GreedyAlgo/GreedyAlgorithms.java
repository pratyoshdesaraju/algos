package algorithms.greedy;

import java.util.*;

public class GreedyAlgorithms {
    
    // Activity Selection: max non-overlapping intervals
    public static int activitySelection(int[][] activities) {
        // Sort by end time
        Arrays.sort(activities, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 1;  // select first activity
        int lastEnd = activities[0][1];
        
        for (int i = 1; i < activities.length; i++) {
            if (activities[i][0] >= lastEnd) {
                count++;
                lastEnd = activities[i][1];
            }
        }
        return count;
    }
    
    // Coin change greedy (works for canonical sets like US coins)
    public static int minCoinsGreedy(int[] coins, int amount) {
        Arrays.sort(coins);  // largest first
        int numCoins = 0;
        
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                numCoins++;
            }
        }
        
        return amount == 0 ? numCoins : -1;
    }
    
    // Huffman Coding basics (frequency → priority queue → tree)
    static class HuffmanNode {
        char data;
        int freq;
        HuffmanNode left, right;
        
        HuffmanNode(char data, int freq) {
            this.data = data;
            this.freq = freq;
        }
        
        HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void huffmanCodes(String text) {
        // Count frequencies
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        // Priority queue (min-heap by frequency)
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.freq, b.freq)
        );
        
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        // Build Huffman tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(
                left.freq + right.freq, left, right
            );
            pq.offer(parent);
        }
        
        System.out.println("Huffman tree root freq: " + pq.peek().freq);
    }
    
    public static void main(String[] args) {
        // Activity selection
        int[][] activities = {
            {1, 4}, {3, 5}, {0, 6}, {5, 7}, {3, 9}, {5, 9}, {6, 10}, {8, 11}, {8, 12}, {2, 14}, {12, 16}
        };
        System.out.println("Max activities: " + activitySelection(activities));  // 4
        
        // Coin change
        int[] coins = {1, 5, 10, 25};
        System.out.println("Min coins for 49: " + minCoinsGreedy(coins, 49));  // 7
        
        // Huffman
        huffmanCodes("geeksforgeeks");  // builds tree by char frequency
    }
}
