/*
You are given a boolean expression s containing
    'T' ---> true
    'F' ---> false 
and following operators between symbols
   &   ---> boolean AND
    |   ---> boolean OR
   ^   ---> boolean XOR
Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.

Note: The answer is guaranteed to fit within a 32-bit integer.

Examples:

Input: s = "T|T&F^T"
Output: 4
Explaination: The expression evaluates to true in 4 ways: ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T)).
Input: s = "T^F|F"
Output: 2
Explaination: The expression evaluates to true in 2 ways: ((T^F)|F) and (T^(F|F)).
Constraints:
1 ≤ |s| ≤ 100 
*/

// User function Template for Java
class Solution {
    static int fun(int i, int j, int isTrue, String s, int dp[][][]){
        if(i > j) return 0;
        if(i == j){
            if(isTrue == 1) return s.charAt(i) == 'T' ? 1 : 0;
            else return s.charAt(i) == 'F' ? 1 : 0;
        }
        
        if(dp[i][j][isTrue] != -1) return dp[i][j][isTrue];
        int ways = 0;
        for(int idx = i+1; idx<=j-1; idx = idx+2){
            int lt = fun(i, idx-1, 1, s, dp);
            int lf = fun(i, idx-1, 0, s, dp);
            int rt = fun(idx+1, j, 1, s, dp);
            int rf = fun(idx+1, j, 0, s, dp);
            
            if(s.charAt(idx) == '&'){
                if(isTrue == 1) ways += (lt * rt);
                else ways += (lt * rf) + (lf * rt) + (lf * rf);
            }
            else if(s.charAt(idx) == '|'){
                if(isTrue == 1) ways += (lt * rt) + (lt * rf) + (lf * rt);
                else ways += (lf * rf);
            }
            else{
                if(isTrue == 1) ways += (lt * rf) + (lf * rt);
                else ways += (lt * rt) + (lf * rf);
            }
            
        }
        
        return dp[i][j][isTrue] = ways;
    }
    
    static int countWays(String s) {
        int n = s.length();
        int dp[][][] = new int[n][n][2];
        
        
        for(int y[][] : dp){
            for(int x[] : y){
                Arrays.fill(x, -1);
            }
        }
            
        return fun(0, n-1, 1, s, dp);
    }
}


// Tabulation
// User function Template for Java
class Solution {
    static int countWays(String s) {
        int n = s.length();
        int dp[][][] = new int[n][n][2];
        
        
        for(int i=n-1; i>=0; i--){
            for(int j=0; j<n; j++){
                if(i > j) continue;
                for(int isTrue=0; isTrue<=1; isTrue++){
                    if(i == j){
                        if(isTrue == 1) dp[i][j][isTrue] = s.charAt(i) == 'T' ? 1 : 0;
                        else dp[i][j][isTrue] = s.charAt(i) == 'F' ? 1 : 0;
                        continue;
                    }
                    
                    int ways = 0;
                    for(int idx = i+1; idx<=j-1; idx = idx+2){
                        int lt = dp[i][idx-1][1];
                        int lf = dp[i][idx-1][0];
                        int rt = dp[idx+1][j][1];
                        int rf = dp[idx+1][j][0];
                        
                        if(s.charAt(idx) == '&'){
                            if(isTrue == 1) ways += (lt * rt);
                            else ways += (lt * rf) + (lf * rt) + (lf * rf);
                        }
                        else if(s.charAt(idx) == '|'){
                            if(isTrue == 1) ways += (lt * rt) + (lt * rf) + (lf * rt);
                            else ways += (lf * rf);
                        }
                        else{
                            if(isTrue == 1) ways += (lt * rf) + (lf * rt);
                            else ways += (lt * rt) + (lf * rf);
                        }
                        
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
    
        
        return dp[0][n-1][1];
    }
}
