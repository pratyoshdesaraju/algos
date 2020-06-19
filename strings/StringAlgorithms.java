package algorithms.strings;

import java.util.HashMap;
import java.util.Map;

public class StringAlgorithms {
    
    // Reverse string without StringBuilder.reverse()
    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
    
    // Anagram check using frequency array (ASCII)
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        int[] freq = new int[128];  // ASCII range
        
        for (int i = 0; i < s1.length(); i++) {
            freq[(int) s1.charAt(i)]++;
            freq[(int) s2.charAt(i)]--;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
    
    // Palindrome check (ignore case, non-alphanumeric)
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // Longest substring without repeating characters (Sliding Window)
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0, left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    
    // String compression "aaabbc" -> "a3b2c1"
    public static String compressString(String s) {
        if (s.isEmpty()) return "";
        
        StringBuilder result = new StringBuilder();
        char prev = s.charAt(0);
        int count = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                count++;
            } else {
                result.append(prev);
                result.append(count);
                prev = s.charAt(i);
                count = 1;
            }
        }
        
        // Add last group
        result.append(prev).append(count);
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Reverse
        System.out.println("Reverse 'hello': " + reverseString("hello"));
        
        // Anagram
        System.out.println("Anagram 'listen' 'silent': " + isAnagram("listen", "silent"));
        System.out.println("Anagram 'hello' 'world': " + isAnagram("hello", "world"));
        
        // Palindrome
        System.out.println("Palindrome 'A man, a plan, a canal: Panama': " + 
            isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("Palindrome 'race a car': " + isPalindrome("race a car"));
        
        // Longest substring
        System.out.println("Longest substring 'abcabcbb': " + 
            lengthOfLongestSubstring("abcabcbb"));  // 3
        System.out.println("Longest substring 'bbbbb': " + 
            lengthOfLongestSubstring("bbbbb"));     // 1
        System.out.println("Longest substring 'pwwkew': " + 
            lengthOfLongestSubstring("pwwkew"));    // 3
        
        // Compression
        System.out.println("Compress 'aaabbc': " + compressString("aaabbc"));  // a3b2c1
        System.out.println("Compress 'aabbcc': " + compressString("aabbcc"));  // a2b2c2
    }
}
