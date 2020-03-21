package algorithms.search;

public class BinarySearchOperations {
    
    // Iterative binary search (standard)
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    
    // Recursive binary search
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;
        
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
        return binarySearchRecursive(arr, target, left, mid - 1);
    }
    
    // Find first occurrence (leftmost duplicate)
    public static int firstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result = mid;  // potential answer
                right = mid - 1;  // continue searching left
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    // Find last occurrence (rightmost duplicate)
    public static int lastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result = mid;  // potential answer
                left = mid + 1;  // continue searching right
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    // Search in rotated sorted array
    public static int searchRotated(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) return mid;
            
            // Left half is sorted
            if (arr[left] <= arr[mid]) {
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        // Standard sorted array
        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Standard array: " + java.util.Arrays.toString(sorted));
        
        System.out.println("Iterative search 7: " + binarySearchIterative(sorted, 7));
        System.out.println("Recursive search 7: " + 
            binarySearchRecursive(sorted, 7, 0, sorted.length - 1));
        
        // Duplicates
        int[] duplicates = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\nDuplicates: " + java.util.Arrays.toString(duplicates));
        System.out.println("First 2: " + firstOccurrence(duplicates, 2));  // 1
        System.out.println("Last 2: " + lastOccurrence(duplicates, 2));    // 3
        
        // Rotated sorted array
        int[] rotated = {7, 9, 11, 13, 1, 3, 5};
        System.out.println("\nRotated: " + java.util.Arrays.toString(rotated));
        System.out.println("Search 13: " + searchRotated(rotated, 13));  // 3
        System.out.println("Search 1: " + searchRotated(rotated, 1));    // 4
        System.out.println("Search 10: " + searchRotated(rotated, 10));  // -1
    }
}
