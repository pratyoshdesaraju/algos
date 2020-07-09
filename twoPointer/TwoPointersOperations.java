package algorithms.twopointers;

public class TwoPointersOperations {
    
    // Two sum in sorted array (O(n) two pointers)
    public static int[] twoSumSorted(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};  // not found
    }
    
    // Remove duplicates from sorted array in-place (return new length)
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        
        int writeIndex = 1;  // next position to write unique element
        
        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[writeIndex - 1]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        return writeIndex;  // first 'writeIndex' elements are unique
    }
    
    // Remove duplicates allowing up to 2 occurrences
    public static int removeDuplicatesK(int[] nums, int k) {
        if (nums.length == 0) return 0;
        
        int writeIndex = 1;
        int count = 1;
        
        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] == nums[readIndex - 1]) {
                count++;
            } else {
                count = 1;
            }
            
            if (count <= k) {
                nums[writeIndex++] = nums[readIndex];
            }
        }
        return writeIndex;
    }
    
    // Container with most water (two pointers optimization)
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxWater = Math.max(maxWater, currentArea);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
    
    public static void main(String[] args) {
        // Two Sum
        int[] nums1 = {-1, 0, 1, 2, 3, 4, 7, 11};
        int target = 11;
        int[] indices = twoSumSorted(nums1, target);
        System.out.println("Two Sum indices for " + target + ": [" + 
            indices[0] + "," + indices[1] + "]");  // [6,7]
        
        // Remove duplicates
        int[] duplicates = {1, 1, 2, 2, 2, 3, 3, 4};
        System.out.println("Duplicates: " + java.util.Arrays.toString(duplicates));
        int newLen = removeDuplicates(duplicates);
        System.out.println("After remove dup: " + 
            java.util.Arrays.toString(java.util.Arrays.copyOf(duplicates, newLen)));
        
        // Remove duplicates k=2
        int[] dupK2 = {1,1,1,2,2,3};
        int newLenK2 = removeDuplicatesK(dupK2, 2);
        System.out.println("k=2 dup remove: " + 
            java.util.Arrays.toString(java.util.Arrays.copyOf(dupK2, newLenK2)));
        
        // Container with most water
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max water area: " + maxArea(heights));  // 49
    }
}
