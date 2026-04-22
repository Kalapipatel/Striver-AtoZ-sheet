/*
You are given two strings s1 and s2. Your task is to print all distinct longest common subsequences in lexicographical order.

Note: A subsequence is derived from another string by deleting some or none of the elements without changing the order of the remaining elements.

Examples:

Input: s1 = "abaaa", s2 = "baabaca"
Output: ["aaaa", "abaa", "baaa"]
Explanation: Length of lcs is 4, in lexicographical order they are "aaaa", "abaa", "baaa".
Input: s1 = "aaa", s2 = "a"
Output: ["a"]
Explanation: Length of lcs is 1 and it is "a".
Constraints:
1 ≤ s1.size(), s2.size() ≤ 50
*/

// will Give TLE but eady to understand
// User function Template for Java
import java.util.*;

class Solution {
    public int lcsfun(String s1, String s2, int dp[][]) {
        int n = s1.length();
        int m = s2.length();
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }
        
        return dp[n][m];
    }
    
    public void printfun(int i, int j, String s1, String s2, int dp[][], Set<String> ans, StringBuilder sb){
        if(i == 0 || j == 0){
            ans.add(sb.reverse().toString());
            sb.reverse(); 
            return;
        }
        
        if(s1.charAt(i-1) == s2.charAt(j-1)){
            sb.append(s1.charAt(i-1));
            printfun(i-1, j-1, s1, s2, dp, ans, sb);
            sb.deleteCharAt(sb.length() - 1); 
        }
        else if(dp[i-1][j] > dp[i][j-1]){
            printfun(i-1, j, s1, s2, dp, ans, sb);
        }
        else if(dp[i-1][j] < dp[i][j-1]){
            printfun(i, j-1, s1, s2, dp, ans, sb);
        }
        else{
            printfun(i-1, j, s1, s2, dp, ans, sb);
            printfun(i, j-1, s1, s2, dp, ans, sb);
        }
    }
    
    public List<String> allLCS(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n+1][m+1];
        
        lcsfun(s1, s2, dp);
        
        Set<String> set = new HashSet<>();
        printfun(n, m, s1, s2, dp, set, new StringBuilder());
        
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        
        return ans;
    }
}


// optmized
import java.util.*;

class Solution {
    public List<String> allLCS(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        
        int[][] next1 = new int[n + 1][26];
        int[][] next2 = new int[m + 1][26];
        
        for (int c = 0; c < 26; c++) {
            next1[n][c] = -1;
            next2[m][c] = -1;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c < 26; c++) {
                next1[i][c] = next1[i + 1][c];
            }
            next1[i][s1.charAt(i) - 'a'] = i;
        }
        
        for (int j = m - 1; j >= 0; j--) {
            for (int c = 0; c < 26; c++) {
                next2[j][c] = next2[j + 1][c];
            }
            next2[j][s2.charAt(j) - 'a'] = j;
        }
        
        List<String> ans = new ArrayList<>();
        backtrack(0, 0, dp[0][0], new StringBuilder(), ans, s1, s2, dp, next1, next2);
        return ans;
    }
    
    private void backtrack(int i, int j, int len, StringBuilder temp, List<String> ans, String s1, String s2, int[][] dp, int[][] next1, int[][] next2) {
        if (len == 0) {
            ans.add(temp.toString());
            return;
        }
        
        for (int c = 0; c < 26; c++) {
            int id1 = next1[i][c];
            int id2 = next2[j][c];
            
            if (id1 != -1 && id2 != -1 && dp[id1][id2] == len) {
                temp.append((char) ('a' + c));
                backtrack(id1 + 1, id2 + 1, len - 1, temp, ans, s1, s2, dp, next1, next2);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
}

/*
The Approach: Avoiding TLE
Your initial code resulted in a Time Limit Exceeded (TLE) error because it traversed 
every possible valid path through the DP table one by one. This generated many 
duplicate strings, which you then had to filter out using a HashSet and sort at the end. 
In strings with many repeated characters, the number of overlapping paths grows exponentially.
The optimal approach uses Jump Arrays combined with Bottom-Up DP to generate the 
unique subsequences directly in lexicographical order, completely bypassing duplicates.
Here is the step-by-step breakdown of the strategy:
1. Bottom-Up DP for Suffixes: Instead of generating the DP table from the beginning 
of the strings to the end, we generate it backward. dp[i][j] stores the length 
of the Longest Common Subsequence for the suffixes s1[i...] and s2[j...].
2. Next Occurrence Arrays (next1 & next2): We precompute the index of the very next 
occurrence of every letter ('a' through 'z') starting from any index i.
- next1[i]['a' - 'a'] tells us instantly where the next 'a' is in s1 starting from index i.
3. Smart Backtracking (Lexicographical by Default): Starting from dp[0][0] (the max 
LCS length), we loop through the alphabet from 'a' to 'z'.
- We use the next arrays to instantly jump to the next matching character in both strings.
- If that character exists in both strings, AND moving to those indices maintains 
the required remaining LCS length (dp[id1][id2] == current_length), we know it 
is a valid part of the answer.
- Because we check 'a' before 'b', the results naturally form in alphabetical order. 
No HashSet or Collections.sort() is needed.
Dry Run
Inputs:
s1 = "abaaa" (Length 5)
s2 = "baabaca" (Length 7)
Max LCS Length at dp[0][0] = 4
1. Next Arrays Precomputation (Conceptual)
Instead of searching character by character, our next1 and next2 arrays act as 
lookup tables. If we are at index 0 of s1 ("abaaa"), looking for the next 'a' 
instantly returns index 0, and looking for 'b' instantly returns index 1.
2. Backtracking Execution
Start: backtrack(i=0, j=0, len=4, temp="")
We loop c from 'a' to 'z' to pick our 1st character.
Try c = 'a':
Next 'a' in s1[0...] is at id1 = 0.
Next 'a' in s2[0...] is at id2 = 1.
Check dp[0][1]. The LCS length for suffixes "abaaa" and "aabaca" is 4. 
This matches our required len = 4.
Action: Append 'a'. temp = "a". Recurse to find the remaining 3 characters 
starting from the next indices.
Recurse -> backtrack(id1+1=1, id2+1=2, len=3, temp="a")
Try c = 'a':
Next 'a' in s1[1...] is at id1 = 2.
Next 'a' in s2[2...] is at id2 = 2.
dp[2][2] is 3. Matches len=3!
Action: Append 'a'. temp = "aa".
Recurse -> backtrack(3, 3, len=2, temp="aa")
Jumping forward following 'a' matches...
Recurse -> backtrack(4, 5, len=1, temp="aaa")
Recurse -> backtrack(5, 7, len=0, temp="aaaa")
len == 0. Result Found: Add "aaaa" to answers.
Try c = 'b': (Still inside backtrack(1, 2, len=3))
Next 'b' in s1[1...] is at id1 = 1.
Next 'b' in s2[2...] is at id2 = 3.
dp[1][3] is 3. Matches len=3!
Action: Append 'b'. temp = "ab".
Recurse -> backtrack(2, 4, len=2, temp="ab")
Following 'a' matches for the remainder...
Recurse -> backtrack(3, 5, len=1, temp="aba")
Recurse -> backtrack(4, 7, len=0, temp="abaa")
len == 0. Result Found: Add "abaa" to answers.
Try c = 'b': (Back at the very beginning backtrack(0, 0, len=4))
Next 'b' in s1[0...] is at id1 = 1.
Next 'b' in s2[0...] is at id2 = 0.
Check dp[1][0]. The LCS length for suffixes "baaa" and "baabaca" is 4. Matches len = 4.
Action: Append 'b'. temp = "b". Recurse to find the remaining 3 characters.
Recurse -> backtrack(2, 1, len=3, temp="b")
Try c = 'a':
Next 'a' in s1[2...] is at id1 = 2.
Next 'a' in s2[1...] is at id2 = 1.
dp[2][1] is 3. Matches len=3!
Action: Append 'a'. temp = "ba".
Recurse -> backtrack(3, 2, len=2, temp="ba")
Following 'a' matches for the remainder...
Recurse -> backtrack(4, 3, len=1, temp="baa")
Recurse -> backtrack(5, 5, len=0, temp="baaa")
len == 0. Result Found: Add "baaa" to answers.
Final Output Array: ["aaaa", "abaa", "baaa"].
Notice how we completely skipped generating overlapping dead ends by leaping 
directly to valid letters in alphabetical order.
*/
