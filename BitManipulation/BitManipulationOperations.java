package algorithms.bitmanipulation;

public class BitManipulationOperations {
    
    // Count set bits (1s) using n & (n-1) trick
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);  // clear lowest set bit
            count++;
        }
        return count;
    }
    
    // Brian Kernighan's algorithm (same as above, more explicit)
    public static int countSetBitsBrian(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
    
    // Power of two check
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    // Single number (XOR all elements)
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;  // XOR is its own inverse
        }
        return result;
    }
    
    // Single number III (two single numbers)
    public static int[] singleNumberIII(int[] nums) {
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        
        // Find rightmost set bit (difference between two singles)
        int rightmostBit = xorAll & -xorAll;
        
        int first = 0, second = 0;
        for (int num : nums) {
            if ((num & rightmostBit) == 0) {
                first ^= num;
            } else {
                second ^= num;
            }
        }
        
        return new int[]{first, second};
    }
    
    // Reverse bits
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) {
                result ^= 1;
            }
            n >>>= 1;
        }
        return result;
    }
    
    public static void main(String[] args) {
        // Count set bits
        System.out.println("Set bits in 13 (1101): " + countSetBits(13));  // 3
        
        // Power of two
        System.out.println("16 is power of 2: " + isPowerOfTwo(16));
        System.out.println("15 is power of 2: " + isPowerOfTwo(15));
        
        // Single number
        int[] nums1 = {2,2,1};
        System.out.println("Single number: " + singleNumber(nums1));  // 1
        
        // Single number III
        int[] nums2 = {1,2,1,3,2,5};
        int[] singles = singleNumberIII(nums2);
        System.out.println("Two singles: " + singles[0] + ", " + singles[1]);  // 3, 5
        
        // Reverse bits
        System.out.println("Reverse bits of 13: " + Integer.toBinaryString(reverseBits(13)));
    }
}
