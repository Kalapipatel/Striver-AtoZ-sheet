/*
Geek is going for a training program for n days. He can perform any of these activities: Running, Fighting, and Learning Practice. Each activity has some point on each day. As Geek wants to improve all his skills, he can't do the same activity on two consecutive days. Given a 2D array arr[][] of size n where arr[i][0], arr[i][1], and arr[i][2] represent the merit points for Running, Fighting, and Learning on the i-th day, determine the maximum total merit points Geek can achieve .

Example:

Input: arr[]= [[1, 2, 5], [3, 1, 1], [3, 3, 3]]
Output: 11
Explanation: Geek will learn a new move and earn 5 point then on second day he will do running and earn 3 point and on third day he will do fighting and earn 3 points so, maximum merit point will be 11.
Input: arr[]= [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
Output: 6
Explanation: Geek can perform any activity each day while adhering to the constraints, in order to maximize his total merit points as 6.
Input: arr[]= [[4, 2, 6]]
Output: 6
Explanation: Geek will learn a new move to make his merit points as 6.
Constraint:
1 <=  n <= 105   
1 <=  arr[i][j] <= 100
*/


// Memoization
class Solution {
    
    public int fun(int days, int last, int arr[][], int dp[][]){
        if(days == 0){
            int maxi = 0;
            for(int i=0; i<=2; i++){
                if(i != last){
                    maxi = Math.max(maxi, arr[0][i]);
                }
            }
            
            return maxi;
        }
        
        int maxi = 0;
        
        if(dp[days][last] != -1) return dp[days][last];
        
        for(int i=0; i<3; i++){
            if(i != last){
                int point = arr[days][i] + fun(days-1, i, arr, dp);
                maxi = Math.max(maxi, point);
                dp[days][last] = maxi;
            }
        }
        
        return dp[days][last] = maxi;
    }
    
    public int maximumPoints(int arr[][]) {
        int n = arr.length;
        int dp[][] = new int[n][4];
        
        for(int x[] : dp){
            Arrays.fill(x, -1);
        }
        
        return fun(arr.length-1, 3, arr, dp);
    }
}


// tabulation
class Solution {
    public int maximumPoints(int arr[][]) {
        int n = arr.length;
        int dp[][] = new int[n][4];
        
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));
        
        for(int day=1; day<n; day++){
            for(int last=0; last<4; last++){
                dp[day][last] = 0;
                
                for(int i=0; i<=2; i++){
                    if(i != last){
                        int point = arr[day][i] + dp[day-1][i];
                        dp[day][last] = Math.max(dp[day][last], point);
                    }
                }
            }
        }
        
        return dp[n-1][3];
    }
}
