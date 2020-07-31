package algorithms.slidingwindow;

public class SlidingWindowOperations {
    
    // Fixed window: max sum subarray of size k
    public static int maxSumWindow(int[] arr, int k) {
        if (arr.length < k) return -1;
        
        // Initial window sum
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        
        int maxSum = windowSum;
        
        // Slide window
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    // Variable window: smallest subarray with sum > target
    public static int smallestSubarraySum(int[] arr, int target) {
        int left = 0, minLen = Integer.MAX_VALUE;
        int currentSum = 0;
        
        for (int right = 0; right < arr.length; right++) {
            currentSum += arr[right];
            
            // Shrink from left while sum >= target
            while (currentSum >= target && left <= right) {
                minLen = Math.min(minLen, right - left + 1);
                currentSum -= arr[left];
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
    
    // Fixed window: longest substring with at most k distinct characters
    public static int longestSubstringKDistinct(String s, int k) {
        if (s.length() == 0 || k == 0) return 0;
        
        int[] charCount = new int[128];
        int numDistinct = 0;
        int left = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            charCount[s.charAt(right)]++;
            if (charCount[s.charAt(right)] == 1) {
                numDistinct++;
            }
            
            // Shrink window if too many distinct chars
            while (numDistinct > k) {
                charCount[s.charAt(left)]--;
                if (charCount[s.charAt(left)] == 0) {
                    numDistinct--;
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        // Fixed window max sum
        int[] arr1 = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        System.out.println("Max sum window k=" + k + ": " + 
            maxSumWindow(arr1, k));  // 24 (4+2+10+2)
        
        // Variable window smallest subarray
        int[] arr2 = {2, 1, 5, 1, 3, 2};
        int target = 8;
        System.out.println("Smallest subarray sum > " + target + ": " + 
            smallestSubarraySum(arr2, target));  // 3 (5+1+3)
        
        // k distinct characters
        System.out.println("Longest substring 2 distinct 'eceba': " + 
            longestSubstringKDistinct("eceba", 2));  // 3 ("ece")
        System.out.println("Longest substring 3 distinct 'aa': " + 
            longestSubstringKDistinct("aa", 3));     // 2
    }
}
