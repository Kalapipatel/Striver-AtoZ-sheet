/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
*/

// Memoization
class Solution {
    public int fun(int i, String a, int j, String b, int dp[][]){
        if(i == 0 || j == 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        if(a.charAt(i-1) == b.charAt(j-1)){
            return dp[i][j] = 1 + fun(i-1, a, j-1, b, dp);
        }

        return dp[i][j] = Math.max(fun(i-1, a, j, b, dp), fun(i, a, j-1, b, dp));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int dp[][] = new int[n+1][m+1];

        for(int x[] : dp){
            Arrays.fill(x, -1);
        }

        fun(n, text1, m, text2, dp);
        return dp[n][m];
    }
}


// Tabulation
class Solution {

    public int longestCommonSubsequence(String a, String b) {
        int n = a.length();
        int m = b.length();
        int dp[][] = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
}

// Space Optmized
class Solution {

    public int longestCommonSubsequence(String a, String b) {
        int n = a.length();
        int m = b.length();
        int prev[] = new int[m+1];

        for(int i=1; i<=n; i++){
            int curr[] = new int[m+1];
            for(int j=1; j<=m; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                }
                else{
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }
            }
            prev = curr;
        }
        return prev[m];
    }
}
