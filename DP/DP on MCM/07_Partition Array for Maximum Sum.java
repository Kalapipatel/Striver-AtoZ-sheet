/*
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
*/


class Solution {
    // Recursive helper function to find max sum from 'start' index
    private int helper(int[] arr, int k, int start, int[] memo) {
        int n = arr.length;

        // Base case: if start reached end, no elements left to partition
        if (start == n) return 0;

        // Return cached result if already computed for this start index
        if (memo[start] != -1) return memo[start];

        int maxSum = 0;    
        int maxElem = 0;   

        // Try partitions of length 1 to k starting at 'start'
        for (int length = 1; length <= k && start + length <= n; length++) {
            // Update max element for this partition length
            maxElem = Math.max(maxElem, arr[start + length - 1]);

            // Calculate current sum: maxElem * length + recursive result for remaining array
            int currentSum = maxElem * length + helper(arr, k, start + length, memo);

            // Update maxSum if current partition leads to higher sum
            maxSum = Math.max(maxSum, currentSum);
        }

        // Memoize and return the best max sum for this start index
        return memo[start] = maxSum;
    }

    // Public method to start the recursion with memoization
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        // Initialize memo array with -1 to indicate unvisited states
        int[] memo = new int[n];
        java.util.Arrays.fill(memo, -1);

        // Start recursion from index 0
        return helper(arr, k, 0, memo);
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        Solution sol = new Solution();
        System.out.println("Maximum sum after partitioning: " + sol.maxSumAfterPartitioning(arr, k));
    }
}

// Tabulation
class Solution {
    // Function to find maximum sum after partitioning
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        // DP array to store max sum from index i to end
        int[] dp = new int[n + 1]; 

        // Iterate from right to left over the array
        for (int i = n - 1; i >= 0; i--) {
            int maxElem = 0;
            int maxSum = 0;

            // Check all partitions of length 1 to k starting at i
            for (int length = 1; length <= k && i + length <= n; length++) {
                // Update maximum element for current partition
                maxElem = Math.max(maxElem, arr[i + length - 1]);

                // Calculate sum if partition ends here
                int currentSum = maxElem * length + dp[i + length];

                // Update maxSum if currentSum is greater
                maxSum = Math.max(maxSum, currentSum);
            }

            // Store maximum sum for subarray starting at i
            dp[i] = maxSum;
        }

        // Return max sum achievable starting at index 0
        return dp[0];
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        Solution sol = new Solution();
        System.out.println("Maximum sum after partitioning: " + sol.maxSumAfterPartitioning(arr, k));
    }
}
