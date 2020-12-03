package algorithms.kadaneboyer;

public class KadaneBoyerMoore {
    
    // Kadane's Algorithm: Maximum subarray sum
    public static int maxSubArraySum(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Start new subarray or extend current?
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    // Kadane's with subarray indices
    public static int[] maxSubArrayIndices(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        
        return new int[]{start, end, maxSum};
    }
    
    // Boyer-Moore Voting Algorithm: Majority element (> n/2 times)
    public static int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        
        // Phase 1: Find candidate
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Phase 2: Verify (optional for guaranteed majority)
        count = 0;
        for (int num : nums) {
            if (num == candidate) count++;
        }
        
        return count > nums.length / 2 ? candidate : -1;
    }
    
    public static void main(String[] args) {
        // Kadane's
        int[] arr1 = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Kadane max sum: " + maxSubArraySum(arr1));  // 6
        int[] indices = maxSubArrayIndices(arr1);
        System.out.println("Subarray [" + indices[0] + "-" + indices[1] + 
            "] sum: " + indices[2]);  // [3-6] sum 6
        
        // All negative case
        int[] negatives = {-1, -2, -3, -4};
        System.out.println("All negative: " + maxSubArraySum(negatives));  // -1
        
        // Boyer-Moore
        int[] majority = {3,2,3};
        System.out.println("Majority in [3,2,3]: " + majorityElement(majority));  // 3
        
        int[] majority2 = {2,2,1,1,1,2,2};
        System.out.println("Majority in [2,2,1,1,1,2,2]: " + majorityElement(majority2));  // 2
    }
}
