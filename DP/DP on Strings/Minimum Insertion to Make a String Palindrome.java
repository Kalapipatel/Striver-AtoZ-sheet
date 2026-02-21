/*
Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
*/

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

    public int longestPalindromeSubseq(String s) {
        String rev = new StringBuilder(s).reverse().toString();

        return longestCommonSubsequence(s, rev);
    }

    public int minInsertions(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }
}
