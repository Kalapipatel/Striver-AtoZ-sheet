/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 
Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.

*/

// Memoization
class Solution {
    public int fun(int i, int j, String s, String t, int dp[][]){
        if(j < 0) return 1;
        if(i < 0) return 0;
    
        if(dp[i][j] != -1) return dp[i][j];
        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] = fun(i-1, j-1, s, t, dp) + fun(i-1, j, s, t, dp);
        }
        else{
            return dp[i][j] = fun(i-1, j, s, t, dp);
        }
    }

    public int numDistinct(String s, String t) {
        int n = s.length();    
        int m = t.length();  
        int dp[][] = new int[n][m];

        for(int x[] : dp){
            Arrays.fill(x, -1);
        }

        return fun(n-1, m-1, s, t, dp);  
    }
}


// Tabulation   ------------------------------------------------------
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();    
        int m = t.length();  
        int dp[][] = new int[n+1][m+1];

        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][m];  
    }
}


// Space Optimization  -------------------------
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();    
        int m = t.length();  
        int prev[] = new int[m+1];

        prev[0] = 1;

        for(int i=1; i<=n; i++){
            int curr[] = new int[m+1];
            curr[0] = 1;
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    curr[j] = prev[j-1] + prev[j];
                }
                else{
                    curr[j] = prev[j];
                }
            }

            prev = curr;
        }

        return prev[m];  
    }
}


// By using only 1 array
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();    
        int m = t.length();  
        int prev[] = new int[m+1];

        prev[0] = 1;

        for(int i=1; i<=n; i++){
            for(int j=m; j>=1; j--){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    prev[j] = prev[j-1] + prev[j];
                }
            }

        }

        return prev[m];  
    }
}
