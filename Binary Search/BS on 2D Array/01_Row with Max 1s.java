/*
You are given a 2D binary array arr[][] consisting of only 1s and 0s. Each row of the array is sorted in non-decreasing order. Your task is to find and return the index of the first row that contains the maximum number of 1s. If no such row exists, return -1.

Note:

The array follows 0-based indexing.
The number of rows and columns in the array are denoted by n.
Examples:

Input: arr[][] = [[0,1,1,1],
               [0,0,1,1],
               [1,1,1,1],
               [0,0,0,0]]
Output: 2
Explanation: Row 2 contains the most number of 1s (4 1s). Hence, the output is 2.
Input: arr[][] = [[0,0],
               [1,1]]
Output: 1
Explanation: Row 1 contains the most number of 1s (2 1s). Hence, the output is 1.
Input: arr[][] = [[0,0], 
               [0,0]]
Output: -1
Explanation: No row contains any 1s, so the output is -1.
Constraints:
1 ≤ arr.size(), arr[i].size() ≤ 103
*/

// you can also solve it by lower bound of 1, upper bound of 0, first occurence of 1

class Solution {
    
    public int fun(int arr[], int n){
        int l = 0, h = n;
        if(arr[n-1] == 0) return -1;
        
        while(l <= h){
            int mid = l + (h-l)/2;
            
            if(arr[mid] == 0){
                l = mid + 1;
            }
            else{
                h = mid - 1;
            }
        }
        
        return n - h;
    }
    
    public int rowWithMax1s(int[][] arr) {
        int n = arr.length;
        
        int ans = -1;
        int max = 0;
        
        for(int i=0; i<n; i++){
            int temp = fun(arr[i], n);
            if(temp > max){
                max = temp;
                ans = i;
            }
        }
        
        return ans;
    }
}
