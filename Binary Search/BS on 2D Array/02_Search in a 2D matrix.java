/*
You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
*/

// O(log(n * m)) soluion, which is better
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int row = matrix.length;
        int col = matrix[0].length;

        int start = 0;
        int end = row * col - 1;

        while(start <= end){

            int mid = start + (end - start) / 2;

            int r = mid / col;
            int c = mid % col;

            int val = matrix[r][c];

            if(val == target){
                return true;
            }
            else if(val < target){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }

        return false;
    }
}

// O(log(n) * log(m)) which is my solution (BS in BS)
class Solution {
    public boolean fun(int arr[], int n, int k){
        int l = 0, h = n;
        
        while(l <= h){
            int mid = l + (h-l)/2;
            
            if(arr[mid] == k) return true;
            if(arr[mid] < k){
                l = mid + 1;
            }
            else{
                h = mid - 1;
            }
        }
        
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int l = 0, h = n-1;
        while(l <= h){
            int mid = (l+h)/2;

            if(matrix[mid][0] <= target && matrix[mid][m-1] >= target){
                return fun(matrix[mid], m, target);
            }
            else if(target < matrix[mid][0]){
                h = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return false;

    }
}
