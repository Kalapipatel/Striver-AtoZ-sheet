// Minimum Insertion/Deletion to convert String A to B
// this both question is same only

/*
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.

ans = n + m - (2 * LCS(word1, word2))
*/

class Solution {
    public int LCS(String s, String t){
        int n = s.length();
        int m = t.length();
        int prev[] = new int[m+1];

        for(int i=1; i<=n; i++){
            int curr[] = new int[m+1];
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
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
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        return n + m - (2 * LCS(word1, word2));
    }
}
