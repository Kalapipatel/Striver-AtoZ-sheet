/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
*/


// Memoization
class Solution {
    public boolean fun(int i, int j, String s, String p, int dp[][]){
        if(i < 0 && j < 0) return true;
        if(j < 0 && i >= 0) return false;
        if(i < 0 && j >= 0){
            for(int k=0; k<=j; k++){
                if(p.charAt(k) != '*') return false;
            }
            return true;
        }

        if(dp[i][j] != -1){
            if(dp[i][j] == 1) return true;
            else return false;
        }

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            boolean match = fun(i-1, j-1, s, p, dp);
            dp[i][j] = match ? 1 : 0;
            return match;
        }
        if(p.charAt(j) == '*'){
            boolean star = fun(i, j-1, s, p, dp) || fun(i-1, j, s, p, dp);
            dp[i][j] = star ? 1 : 0;
            return star;
        }

        dp[i][j] = 0;
        return  false;
    }
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        int dp[][] = new int[n+1][m+1];

        for(int x[] : dp){
            Arrays.fill(x, -1);
        }

        return fun(n-1, m-1, s, p, dp);
    }
}


// Tabulation
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n+1][m+1];

        dp[0][0] = true;
        for(int i=1; i<=n; i++){
            dp[i][0] = false;
        }

        for(int j=1; j<=m; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = true;
            }
            else{
                break;
            }
        }

        // main code
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else{
                    dp[i][j] = false;
                }
            }
        }

        return dp[n][m];
    }
}
