package algorithms.sorting;

import java.util.Random;

// LinkedList Node for merge sort
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeSortOperations {
    
    // Array-based merge sort
    public static void mergeSortArray(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        
        // Split
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);
        
        // Sort halves
        mergeSortArray(left);
        mergeSortArray(right);
        
        // Merge
        merge(arr, left, right);
    }
    
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }
    
    // LinkedList merge sort (bottom-up split, top-down merge)
    public static ListNode mergeSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        // Split into two halves
        ListNode slow = head, fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;  // split point
        
        // Recursively sort halves
        ListNode left = mergeSortList(head);
        ListNode right = mergeSortList(slow);
        
        // Merge sorted halves
        return mergeLists(left, right);
    }
    
    private static ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
    
    // Helper: print linked list
    static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        // Array merge sort
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array before: " + java.util.Arrays.toString(arr));
        mergeSortArray(arr);
        System.out.println("Array after: " + java.util.Arrays.toString(arr));
        
        // LinkedList merge sort
        ListNode list = new ListNode(4, new ListNode(2, 
            new ListNode(1, new ListNode(3))));
        
        System.out.println("\n");
        printList(list);  // 4 -> 2 -> 1 -> 3 -> null
        ListNode sortedList = mergeSortList(list);
        printList(sortedList);  // 1 -> 2 -> 3 -> 4 -> null
        
        // Stability test (equal elements preserve order)
        ListNode stableList = new ListNode(3, new ListNode(1, 
            new ListNode(3, new ListNode(2))));
        printList(stableList);
        ListNode stableSorted = mergeSortList(stableList);
        printList(stableSorted);  // 1 -> 2 -> 3 -> 3 (stable)
    }
}
