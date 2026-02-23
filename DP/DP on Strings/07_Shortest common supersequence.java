/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"
 
 Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
*/

class Solution {
    public String LCS(String s, String t){
        int n = s.length();
        int m = t.length();
        int dp[][] = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int i=n, j=m;

        while(i>0 && j>0){
            if(s.charAt(i-1) == t.charAt(j-1)){
                sb.append(s.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]) i--;
            else j--;
        }

        return sb.reverse().toString();
    }

    public String shortestCommonSupersequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        String lcs = LCS(s, t);

        StringBuilder ans = new StringBuilder();
        int len = lcs.length();
        if(len == 0){
            ans.append(s).append(t);
            return ans.toString();
        }

        int i=0, j=0;
        for(int k=0; k<len; k++){
            char ch = lcs.charAt(k);

            // adding chars of s
            while(s.charAt(i) != ch && i<n){
                ans.append(s.charAt(i));
                i++;
            }

            // adding chars of t
            while(t.charAt(j) != ch && j<m){
                ans.append(t.charAt(j));
                j++;
            }

            // adding char of lcs
            ans.append(ch);
            i++;
            j++;
        }

        // adding remaning chars of s
        while(i<n){
            ans.append(s.charAt(i));
            i++;
        }        

        // adding remaning chars of t
        while(j<m){
            ans.append(t.charAt(j));
            j++;
        }

        return ans.toString();
    }
}
