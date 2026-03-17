/*
You are given an array of integers arr[], return the Longest Increasing Subsequence (LIS) of the given array.
LIS is the longest subsequence where each element is strictly greater than the previous one.
Note: If multiple LIS of the same maximum length exist, return the one that appears first based on the lexicographical order of their indices (i.e., the earliest combination of positions from the original sequence).

Examples:

Input: arr[] = [10, 20, 3, 40]
Output: [10, 20, 40]
Explanation: [10, 20, 40] is the longest subsequence where each number is greater than the previous one, maintaining the original order.
Input: arr[] = [10, 22, 9, 33, 21, 50, 41, 60, 80]
Output: [10, 22, 33, 50, 60, 80]
Explanation: There are multiple longest Increasing subsequence of length 6, that is [10, 22, 33, 50, 60, 80] and [10 22 33 41 60 80]. The first one has lexicographic smallest order of indices.
Constraint:
1 ≤ n ≤ 5*103
0 ≤ arr[i] ≤ 109
*/

class Solution {
    public ArrayList<Integer> fun(int arr[], int n){
        int dp[] = new int[n];
        int hash[] = new int[n];
        
        Arrays.fill(dp, 1);
        
        int maxi = 1;
        int lastIndex = 0;
        
        for(int i=0; i<n; i++){
            hash[i] = i;
            
            for(int prev = 0; prev<i; prev++){
                if(arr[prev] < arr[i] && 1 + dp[prev] > dp[i]){
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            
            if(dp[i] > maxi){
                maxi = dp[i];
                lastIndex = i;
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(arr[lastIndex]);
        
        while(hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            ans.add(arr[lastIndex]);
        }
        
        Collections.sort(ans);
        return ans;
    }
    
    public ArrayList<Integer> getLIS(int arr[]) {
        int n = arr.length;
        
        return fun(arr, n);
        
    }
}
