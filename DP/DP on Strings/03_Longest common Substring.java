/*
You are given two strings s1 and s2. Your task is to find the length of the longest common substring among the given strings.

Examples:

Input: s1 = "ABCDGH", s2 = "ACDGHR"
Output: 4
Explanation: The longest common substring is "CDGH" with a length of 4.
Input: s1 = "abc", s2 = "acb"
Output: 1
Explanation: The longest common substrings are "a", "b", "c" all having length 1.
Input: s1 = "YZ", s2 = "yz"
Output: 0
Constraints:
1 <= s1.size(), s2.size() <= 103
Both strings may contain upper and lower case alphabets.
*/

// Tabulation
class Solution {

    public int longestCommonSubsequence(String a, String b) {
        int n = a.length();
        int m = b.length();
        int dp[][] = new int[n+1][m+1];

        int max = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max, dp[i][j]);
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }
}

// Space Optmized
class Solution {
    public int longCommSubstr(String a, String b) {
        // code here
        int n = a.length();
        int m = b.length();
        int prev[] = new int[m+1];

        int max = 0;
        for(int i=1; i<=n; i++){
            int curr[] = new int[m+1];
            for(int j=1; j<=m; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                    max = Math.max(max, curr[j]);
                }
                else{
                    curr[j] = 0; // just by changing this line we can convert LCS code to this solution
                }
            }
            prev = curr;
        }
        return max;
    }
}
