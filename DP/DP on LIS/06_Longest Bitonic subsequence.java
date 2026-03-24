/*
Given an array of positive integers. Find the maximum length of Bitonic subsequence. 
A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing. Return the maximum length of bitonic subsequence.
 
Note : A strictly increasing or a strictly decreasing sequence should not be considered as a bitonic sequence

Examples :

Input: n = 5, nums[] = [1, 2, 5, 3, 2]
Output: 5
Explanation: The sequence [1, 2, 5] is increasing and the sequence [3, 2] is decreasing so merging both we will get length 5.
Input: n = 8, nums[] = [1, 11, 2, 10, 4, 5, 2, 1]
Output: 6
Explanation: The bitonic sequence [1, 2, 10, 4, 2, 1] has length 6.
Input: n = 3, nums[] = [10, 20, 30]
Output: 0
Explanation: The decreasing or increasing part cannot be empty.
Input: n = 3, nums[] = [10, 10, 10]
Output: 0
Explanation: No strictly increasing or decreasing sequence exists.
Constraints:
1 ≤ length of array ≤ 103
1 ≤ arr[i] ≤ 104
*/


class Solution {
    public static int[] lis(int arr[]){
        int n = arr.length;
        int dp[] = new int[n];
        Arrays.fill(dp, 1);
        
        int maxi = 1;
        
        for(int i=0; i<n; i++){
            for(int prev = 0; prev<i; prev++){
                if(arr[prev] < arr[i] && 1 + dp[prev] > dp[i]){
                    dp[i] = 1 + dp[prev];
                }
            }
            
            if(dp[i] > maxi){
                maxi = dp[i];
            }
        }
        
        return dp;
    } 
    public static int longestBitonicSequence(int n, int[] arr) {
        int dp1[] = lis(arr);
        
        // reverse the array
        for (int i = 0; i < arr.length / 2; i++) {
            int t = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = t;
        }
        
        int dp2[] = lis(arr);
        
        int lbs = 0;
        for(int i=0; i<n; i++){
            lbs = Math.max(lbs, dp1[i] + dp2[i] - 1);
        }
        
        return lbs;
        
    }
}
