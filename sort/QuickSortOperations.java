package algorithms.sorting;

import java.util.Random;

public class QuickSortOperations {
    
    // Lomuto partition scheme (right pivot)
    private static int lomutoPartition(int[] arr, int low, int high) {
        int pivot = arr[high];  // choose last element as pivot
        int i = low - 1;        // index of smaller element
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    // Hoare partition scheme (two pointers)
    private static int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2];  // middle pivot
        int left = low - 1;
        int right = high + 1;
        
        while (true) {
            do { left++; } while (arr[left] < pivot);
            do { right--; } while (arr[right] > pivot);
            
            if (left >= right) return right;
            swap(arr, left, right);
        }
    }
    
    // Quick sort with Lomuto partition
    public static void quickSortLomuto(int[] arr, int low, int high) {
        if (low < high) {
            int pi = lomutoPartition(arr, low, high);
            quickSortLomuto(arr, low, pi - 1);
            quickSortLomuto(arr, pi + 1, high);
        }
    }
    
    // Quick sort with Hoare partition
    public static void quickSortHoare(int[] arr, int low, int high) {
        if (low < high) {
            int pi = hoarePartition(arr, low, high);
            quickSortHoare(arr, low, pi);
            quickSortHoare(arr, pi + 1, high);
        }
    }
    
    // Random pivot selection
    private static int randomPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);  // move random to end
        return lomutoPartition(arr, low, high);
    }
    
    public static void quickSortRandomPivot(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomPartition(arr, low, high);
            quickSortRandomPivot(arr, low, pi - 1);
            quickSortRandomPivot(arr, pi + 1, high);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90, 5};
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        
        System.out.println("Original: " + java.util.Arrays.toString(arr1));
        
        // Lomuto partition
        quickSortLomuto(arr1, 0, arr1.length - 1);
        System.out.println("Lomuto: " + java.util.Arrays.toString(arr1));
        
        // Hoare partition
        quickSortHoare(arr2, 0, arr2.length - 1);
        System.out.println("Hoare: " + java.util.Arrays.toString(arr2));
        
        // Random pivot (run multiple times to see effect)
        System.out.println("\nRandom pivot (3 runs):");
        for (int i = 0; i < 3; i++) {
            int[] test = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            quickSortRandomPivot(test, 0, test.length - 1);
            System.out.println("Run " + (i+1) + ": " + java.util.Arrays.toString(test));
        }
        
        // Worst case demo (already sorted - shows random pivot helps)
        int[] worstCase = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nWorst case mitigation:");
        System.out.println("Before random: " + java.util.Arrays.toString(worstCase));
        quickSortRandomPivot(worstCase, 0, worstCase.length - 1);
        System.out.println("After random: " + java.util.Arrays.toString(worstCase));
    }
}
