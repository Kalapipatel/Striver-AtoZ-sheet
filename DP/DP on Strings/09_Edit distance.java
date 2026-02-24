/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/


// Memoizatino
class Solution {
    public int fun(int i, int j, String s, String t, int dp[][]){
       if(j == 0) return i;
       if(i == 0) return j;

        if(dp[i][j] != -1) return dp[i][j];
        if(s.charAt(i-1) == t.charAt(j-1)){
            dp[i][j] = 0 + fun(i-1, j-1, s, t, dp);
        }
        else{
            int insert = 1 + fun(i, j-1, s, t, dp);
            int delete = 1 + fun(i-1, j, s, t, dp);
            int replace = 1 + fun(i-1, j-1, s, t, dp);

            dp[i][j] = Math.min(insert, Math.min(delete,  replace));
        }

        return dp[i][j];
    }

    public int minDistance(String s, String t) {
        int n = s.length();
        int m = t.length();
        int dp[][] = new int[n+1][m+1];

        for(int x[] : dp){
            Arrays.fill(x, -1);
        }

        return fun(n, m, s, t, dp);
    }
}


// Tabulation  --------------------------------------------
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                if (j == 0) {
                    dp[i][j] = i;
                }
            }
        }

        // bottom up
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {// case 1 -> same
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // case 2 -> diff
                    int add = dp[i][j - 1] + 1;
                    int del = dp[i - 1][j] + 1;
                    int rep = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(add, Math.min(del, rep));
                }
            }
        }

        return dp[n][m];
    }
}
