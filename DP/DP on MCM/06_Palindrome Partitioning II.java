/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters only.
*/


class Solution {
    private boolean isPalindrome(int i, int j, String s){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }

    private int fun(int i, String s, int n, int dp[]){
        if(i >= n) return 0;

        if(dp[i] != -1) return dp[i];
        int mini = Integer.MAX_VALUE;
        for(int j=i; j<n; j++){
            if(isPalindrome(i, j, s)){
                int cost = 1 + fun(j+1, s, n, dp);
                mini = Math.min(mini, cost);
            }
        }

        return dp[i] = mini;
    }
    public int minCut(String s) {
        int n = s.length();
        int dp [] = new int[n];

        Arrays.fill(dp, -1);

        return fun(0, s, s.length(), dp) - 1;
    }
}


// Tabulation
class Solution {
    private boolean isPalindrome(int i, int j, String s){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }

    public int minCut(String s) {
        int n = s.length();
        int dp [] = new int[n+1];

        for(int i=n-1; i>=0; i--){
            int mini = Integer.MAX_VALUE;
            for(int j=i; j<n; j++){
                    if(isPalindrome(i, j, s)){
                        int cost = 1 + dp[j+1];
                        mini = Math.min(mini, cost);
                    }
                dp[i] = mini;
            }
        }
        return dp[0] - 1;
    }
}
